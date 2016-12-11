package eci.cosw.edu.sharepark;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import eci.cosw.edu.sharepark.entities.Parking;
import eci.cosw.edu.sharepark.entities.User;

public class LoginActivity extends AppCompatActivity {
    static  User user=null;
    private EditText id;
    private EditText password;
    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        id=(EditText) findViewById(R.id.idcedula);
        password=(EditText) findViewById(R.id.logpassword);
        login=(Button) findViewById(R.id.iniciarSesion);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id.getText().toString().length()==0){
                    id.setError("No olvides tu cedula!");
                }else if (password.getText().toString().length()==0){
                    password.setError("No olvides tu contraseña!");
                }else{
                    getUser();
                    if(user==null){
                        id.setError("Tu usuario no se encuentra registrado!");
                    }else if(user!=null && user.getPassword().equals(password.getText().toString())){
                        password.setError("Tu contraseña es incorrecta!");
                    }else{
                        mapa();
                    }
                }

            }
        });
        register=(Button) findViewById(R.id.logRegistrar);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrar();
            }
        });

    }
    public void getUser(){
        RequestQueue queue= Volley.newRequestQueue(this);
        String url="https://shareparkservices.herokuapp.com/usuarios/"+id.getText().toString()+"/";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                user=new Gson().fromJson(response, User.class);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error");
            }
        });
        queue.add(stringRequest);
    }
    public void mapa(){
        Intent intent=new Intent(this, FindParkingActivity.class);
        Bundle bundle = new Bundle();
        startActivity(intent);
    }
    public void registrar(){
        Intent intent=new Intent(this, RegisterActivity.class);
        Bundle bundle = new Bundle();
        startActivity(intent);
    }
}
