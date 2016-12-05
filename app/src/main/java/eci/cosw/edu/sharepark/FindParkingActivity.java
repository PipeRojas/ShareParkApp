package eci.cosw.edu.sharepark;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import eci.cosw.edu.sharepark.entities.Parking;
import eci.cosw.edu.sharepark.entities.User;

public class FindParkingActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final int ACCESS_LOCATION_PERMISSION_CODE = 0;
    private GoogleMap googleMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest = new LocationRequest();
    private TextView description;
    private Marker myMarker;
    private Location myLocation;
    private List<Parking> parkings;
    private Map<Marker, Parking> parkMarkers=new HashMap<>();
    private User owner;
    private Parking selectedParking;
    Button request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        description =(TextView)findViewById(R.id.textView2);
        request=(Button) findViewById(R.id.button3);
        request.setEnabled(false);
        ExecutorService ex=Executors.newFixedThreadPool(1);
        ex.execute(new Runnable() {
            @Override
            public synchronized void run() {
                getParkings();
            }
        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        buildGoogleApiClient();
        googleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        googleApiClient.connect();
        Button fAddButton = (Button) findViewById(R.id.button2);
        fAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadMap();
            }
        });
        Button addRequestButton=(Button) findViewById(R.id.button3);
        addRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerRequest();
            }
        });

        Button menu=(Button) findViewById(R.id.button4);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu();
            }
        });
    }

    public synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.setOnMarkerClickListener(                                                                                              new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if(parkMarkers.containsKey(marker)){
                    selectedParking=parkMarkers.get(marker);
                    String address=selectedParking.getAddress();
                    String dimensions= selectedParking.getHeight()+"X"+selectedParking.getLength()+"X"+selectedParking.getWidth();
                    String home=(selectedParking.isHome())?"Casa":"Conjunto";
                    String covered=(selectedParking.isCovert())?"Si":"No";
                    String available=(selectedParking.isAvailable())?"Si":"No";
                    String costMinute=selectedParking.getCostMinute().toString();
                    String stratum=selectedParking.getStratum().toString();
                    String data="Dirección: "+address+"\n"+
                            "Dimensiones en Metros (AltoXLargoXAncho): "+dimensions+"\n"+
                            "Tipo de Propiedad: "+home+" Estrato Residencia: "+stratum+"\n"+
                            "Estacionamiento Cubierto: "+covered+"\n"+
                            "Disponible: "+available+" Valor/Minuto(Pesos): "+costMinute+"\n"+
                            "Costo por Minuto: $"+costMinute;
                    description.setText(data);
                    request.setEnabled(true);
                }
                return false;
            }
        });
        showMyLocation();
    }


    public void showMyLocation() {
        if (googleMap != null) {
            String[] permissions = {android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION};
            if (hasPermissions(this, permissions)) {
                googleMap.setMyLocationEnabled(true);


                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }

                Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                if (lastLocation != null) {
                    myLocation=lastLocation;
                    BitmapDescriptor posIcon= BitmapDescriptorFactory.fromResource(R.mipmap.pos_icon);
                    MarkerOptions markerOptions= new MarkerOptions().position(new LatLng(myLocation.getLatitude(), myLocation.getLongitude())).title("Usted está aqui!!").icon(posIcon);
                    myMarker=googleMap.addMarker(markerOptions);
                }
            } else {
                ActivityCompat.requestPermissions(this, permissions, ACCESS_LOCATION_PERMISSION_CODE);
            }
        }
    }


    public static boolean hasPermissions(Context context, String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }


    public void zoom(Location location, int zoom) {
        LatLng myLocation = new LatLng(location.getLatitude(), location.getLongitude());
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, zoom));
    }

    

    public void getParkings(){
        RequestQueue queue= Volley.newRequestQueue(this);
        String url="http://sharepark.herokuapp.com/parkings/";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parkings=new Gson().fromJson(response, new TypeToken<List<Parking>>(){}.getType());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error");
            }
        });
        queue.add(stringRequest);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case ACCESS_LOCATION_PERMISSION_CODE:
                showMyLocation();
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest,
                new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        showMyLocation();
                        stopLocationUpdates();
                    }
                });
    }

    @Override
    public void onConnectionSuspended(int i) {
        stopLocationUpdates();

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void stopLocationUpdates()
    {
        LocationServices.FusedLocationApi.removeLocationUpdates( googleApiClient, new LocationListener()
        {
            @Override
            public void onLocationChanged( Location location )
            {

            }
        } );
    }

    public void setParkingMarkers(){
        for (Marker m:parkMarkers.keySet()) {
            m.remove();
        }
        parkMarkers=new HashMap<>();
        BitmapDescriptor posIcon= BitmapDescriptorFactory.fromResource(R.mipmap.parking_icon);
        for (Parking p:parkings) {
            MarkerOptions markerOptions= new MarkerOptions().position(new LatLng(p.getX(), p.getY())).title(p.getAddress()).icon(posIcon);
            myMarker=googleMap.addMarker(markerOptions);
            parkMarkers.put(myMarker, p);
        }
    }

    public void loadMap() {
        zoom(myLocation, 15);
        setParkingMarkers();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (lastLocation != null) {
            AddressResultReceiver addressResultReceiver = new AddressResultReceiver(new Handler());
            addressResultReceiver.setAddressResultListener(new AddressResultListener() {
                @Override
                public void onAddressFound(final String address) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            FindParkingActivity.this.description.setText(address);
                            FindParkingActivity.this.description.setVisibility(View.VISIBLE);
                        }
                    });


                }
            });
            Intent intent = new Intent(this, FetchAddressIntentService.class);
            intent.putExtra(FetchAddressIntentService.RECEIVER, addressResultReceiver);
            intent.putExtra(FetchAddressIntentService.LOCATION_DATA_EXTRA, lastLocation);
            startService(intent);
        }
    }

    public void registerRequest(){
        Intent intent=new Intent(this, ParkingRequestActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Parking", selectedParking);
        intent.putExtra("Parking", bundle);
        startActivity(intent);
    }

    public void menu(){
        Intent intent=new Intent(this, HomeActivity.class);
        Bundle bundle = new Bundle();
        startActivity(intent);
    }
}