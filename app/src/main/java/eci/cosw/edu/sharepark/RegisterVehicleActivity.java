package eci.cosw.edu.sharepark;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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
 * Created by david on 12/7/16.
 */

public class RegisterVehicleActivity extends AppCompatActivity {

    private TextView placa;
    private TextView marca;
    private TextView modelo;
    private TextView color;
    private TextView tvehiculo;
    private Button guar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        placa = (TextView) findViewById(R.id.placa);
        marca = (TextView) findViewById(R.id.marca);
        modelo = (TextView) findViewById(R.id.modelo);
        color = (TextView) findViewById(R.id.color);
        tvehiculo = (TextView) findViewById(R.id.tvehiculo);
        guar = (Button) findViewById(R.id.guar);
        guar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                guar();
            }
        });

    }

    public void guar(){
        RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "https://shareparkservices.herokuapp.com/usuarios/";
        HashMap<String, String> vehicleParam = new HashMap<String, String>();
        vehicleParam.put("placa", placa.toString());
        vehicleParam.put("brand", marca.toString());
        vehicleParam.put("model", modelo.toString());
        vehicleParam.put("color", color.toString());
        vehicleParam.put("vehicleType", tvehiculo.toString());

        JsonObjectRequest request = new JsonObjectRequest(url, new JSONObject(vehicleParam),
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
