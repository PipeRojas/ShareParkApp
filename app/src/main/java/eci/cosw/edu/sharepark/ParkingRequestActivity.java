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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
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
        System.out.println(LoginActivity.user.getId());
    }

    public void getOwner(){
        RequestQueue queue= Volley.newRequestQueue(this);
        String url="http://sharepark.herokuapp.com/usuarios/"+selectedParking.getOwner_id();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
                owner=new Gson().fromJson(response, User.class);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error");
            }
        });
        queue.add(stringRequest);
    }

    public void back(){
        Intent intent=new Intent(this, FindParkingActivity.class);
        Bundle bundle = new Bundle();
        startActivity(intent);
    }
}
