package eci.cosw.edu.sharepark;

import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import eci.cosw.edu.sharepark.entities.Request;

/**
 * Created by alejandra on 4/12/16.
 */

public class RegisterActivity extends AppCompatActivity{
    EditText name;
    EditText id;
    EditText phone;
    EditText address;
    EditText email;
    EditText password;
    Button registerUser;

    String var=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        name = (EditText) findViewById(R.id.name);
        id=(EditText) findViewById(R.id.id);
        phone=(EditText) findViewById(R.id.phone);
        address=(EditText) findViewById(R.id.address);
        email=(EditText) findViewById(R.id.email);
        password= (EditText) findViewById(R.id.password);
        final String[] n=name.getText().toString().split(" ");
        registerUser =(Button) findViewById(R.id.registrarUsuario);
        registerUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(name.getText().length()==0){
                    name.setError("No olvides tu nombre!");
                }else if(id.getText().length()==0) {
                    id.setError("No olvides tu cedula!");
                }else if(phone.getText().length()==0){
                    phone.setError("No olvides tu telefono!");
                }else if(address.getText().length()==0){
                    address.setError("No olvides tu direccion!");
                }else if(email.getText().length()==0){
                    email.setError("No olvides tu correo electronico!");
                }else if(password.getText().length()==0){
                    password.setError("No olvides tu contraseña!");
                }else if(!email.getText().toString().contains("@")||!email.getText().toString().contains(".")) {
                    email.setError("Tu correo esta mal escrito!");
                }else if(phone.getText().length()>=12){
                    phone.setError("Tu telefono es demasiado largo");
                }else if(!name.getText().toString().contains(" ")||n.length>2){
                    name.setError("Debes escribir un nombre y un apellido!");
                }else {

                    postUser();
                }
            }
        });
    }
    private void postUser() {
        RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "https://shareparkservices.herokuapp.com/usuarios/";
        HashMap<String, String> userParam = new HashMap<String, String>();
        String[] name1 = name.getText().toString().split(" ");
        userParam.put("firstName", name1[0]);
        userParam.put("lastName", name1[1]);
        userParam.put("id", id.getText().toString());
        userParam.put("phone", phone.getText().toString());
        userParam.put("address", address.getText().toString());
        userParam.put("email", email.getText().toString());
        userParam.put("password", password.getText().toString());

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
}
