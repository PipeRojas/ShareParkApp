package eci.cosw.edu.sharepark;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

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
 * Created by david
 */

public class RegisterParkingActivity extends AppCompatActivity {

    private TextView address;
    private CheckBox hood;
    private CheckBox house;
    private CheckBox roof;
    private CheckBox unroof;
    private TextView costo;
    private TextView est;
    private TextView owner_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_parking);
        address = (TextView) findViewById(R.id.adress);
        address.setError("Dirección invalida");
        hood = (CheckBox)findViewById(R.id.checkBox2);
        house = (CheckBox)findViewById(R.id.checkBox);
        roof = (CheckBox)findViewById(R.id.checkBox3);
        unroof = (CheckBox)findViewById(R.id.checkBox2);
        costo = (TextView) findViewById(R.id.costo);
        est = (TextView) findViewById(R.id.estrato);
        owner_id = (TextView) findViewById(R.id.owner_id);

    }

    public void guardar(View view) {

        RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "https://shareparkservices.herokuapp.com/usuarios/";
        HashMap<String, String> parksParam = new HashMap<String, String>();
        /*String[] name1 = name.getText().toString().split(" ");
        userParam.put("firstName", name1[0]);
        userParam.put("lastName", name1[1]);
        userParam.put("id", id.getText().toString());
        userParam.put("phone", phone.getText().toString());
        userParam.put("address", address.getText().toString());
        userParam.put("email", email.getText().toString());
        userParam.put("password", password.getText().toString());*/
        parksParam.put("owner_id",owner_id.toString() );
        parksParam.put("covert",roof.toString() );
        parksParam.put("home",house.toString() );
        parksParam.put("height", "3.0" );
        parksParam.put("width", "3.0" );
        parksParam.put("length", "3.0" );
        parksParam.put("available", "true" );
        parksParam.put("serviceStart", "true" );
        parksParam.put("availableFinish", "true" );
        parksParam.put("costMinute", costo.toString() );
        parksParam.put("x", "0" );
        parksParam.put("y", "0" );
        parksParam.put("address", address.toString() );
        parksParam.put("stratum", est.toString() );
        parksParam.put("picture", "null" );




        JsonObjectRequest request = new JsonObjectRequest(url, new JSONObject(parksParam),
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





