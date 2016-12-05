package eci.cosw.edu.sharepark;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by alejandra on 5/12/16.
 */

public class HomeActivity extends AppCompatActivity{

    Button registerVehicle;
    Button registerParking;
    Button serviceOnCurse;
    Button volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        registerVehicle=(Button) findViewById(R.id.rVehicle);
        registerVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        registerParking=(Button) findViewById(R.id.rParking);
        registerParking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        serviceOnCurse=(Button) findViewById(R.id.servicioCurso);
        serviceOnCurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        volver=(Button) findViewById(R.id.volverHome);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
    public void back(){
        Intent intent=new Intent(this, FindParkingActivity.class);
        Bundle bundle = new Bundle();
        startActivity(intent);
    }
    public void goAheadV(){
        Intent intent=new Intent(this, FindParkingActivity.class);
        Bundle bundle = new Bundle();
        startActivity(intent);
    }public void goAheadP(){
        Intent intent=new Intent(this, FindParkingActivity.class);
        Bundle bundle = new Bundle();
        startActivity(intent);
    }public void goAheadS(){
        Intent intent=new Intent(this, ServiceTimerActivity.class);
        Bundle bundle = new Bundle();
        startActivity(intent);
    }
}

