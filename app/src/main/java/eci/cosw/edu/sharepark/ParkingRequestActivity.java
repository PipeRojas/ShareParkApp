package eci.cosw.edu.sharepark;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import eci.cosw.edu.sharepark.entities.Parking;
import eci.cosw.edu.sharepark.entities.User;

public class ParkingRequestActivity extends AppCompatActivity {
    private Parking selectedParking;
    private User owner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_request);
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("Parking");
        selectedParking = (Parking) bundle.getSerializable("Parking");
        String address=selectedParking.getAddress();
        String covered=(selectedParking.isCovert())?"Si":"No";
        String home=(selectedParking.isHome())?"Casa":"Conjunto";
        String available=(selectedParking.isAvailable())?"Si":"No";
        String costMinute=selectedParking.getCostMinute().toString();
        String stratum=selectedParking.getStratum().toString();
        TextView addressTV=(TextView) findViewById(R.id.textView6);
        addressTV.setText("Dirección Estacionamiento: "+address);
        TextView coveredTV=(TextView) findViewById(R.id.textView7);
        coveredTV.setText("Estacionamiento  Cubierto: "+covered);
        TextView homeTV=(TextView) findViewById(R.id.textView8);
        homeTV.setText("Tipo de Propiedad: "+home);
        TextView availableTV=(TextView) findViewById(R.id.textView9);
        availableTV.setText("Está Disponible: "+available);
        TextView costMinuteTV=(TextView) findViewById(R.id.textView10);
        costMinuteTV.setText("Costo Por Minuto "+costMinute);
        TextView stratumTV=(TextView) findViewById(R.id.textView11);
        stratumTV.setText("Estrato: "+stratum);
        final Button back=(Button) findViewById(R.id.button3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });
        Button request=(Button) findViewById(R.id.button6);
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createRequest();
                back();
            }
        });
    }

    public void createRequest(){
        RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "https://shareparkservices.herokuapp.com/usuarios/"+LoginActivity.user.getId().toString()+"/solicitud";
        Random rn=new Random();
        Integer id= rn.nextInt();
        HashMap<String, String> userParam = new HashMap<String, String>();
        userParam.put("state", "false");
        userParam.put("service_paid", "0");
        userParam.put("id", id.toString());
        userParam.put("giver_id", LoginActivity.user.getId().toString());
        userParam.put("taker_id", selectedParking.getOwner_id().toString());

        JsonObjectRequest request = new JsonObjectRequest(url, new JSONObject(userParam),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            VolleyLog.v("Response:%n %s", response.toString(4));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.e("Error: ", error.getMessage());
                    }
                });
        queue.add(request);
    }

    public void back(){
        Intent intent=new Intent(this, FindParkingActivity.class);
        Bundle bundle = new Bundle();
        startActivity(intent);
    }
}
