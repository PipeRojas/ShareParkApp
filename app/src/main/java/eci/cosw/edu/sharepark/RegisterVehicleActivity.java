package eci.cosw.edu.sharepark;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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
    private String tvehiculo;
    private Button guar;
    private Boolean type;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_vehicle);
        placa = (TextView) findViewById(R.id.placa);
        marca = (TextView) findViewById(R.id.marca);
        modelo = (TextView) findViewById(R.id.modelo);
        color = (TextView) findViewById(R.id.color);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String[] valores = {"Automovil","Campero", "Camioneta o Pickup","Van", "Mini Van"};
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valores));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {
                tvehiculo=(String) adapterView.getItemAtPosition(position);
                type=true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                type=false;
            }
        });
        guar = (Button) findViewById(R.id.guar);
        guar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (placa.getText().toString().length()==0){
                    placa.setError("No olvides tu placa!");
                }else if(placa.getText().toString().length()!=6){
                    placa.setError("Tu placa no es correcta!");
                }else if(marca.getText().length()==0){
                    marca.setError("No olvides la marca de tu vehículo!");
                }else if(color.getText().length()==0){
                    color.setError("No olvides el color de tu vehículo!");
                }else if (type==false){
                    marca.setError("No olvides el tipo de tu vehículo!");
                }else{
                    save();
                    goAhead();
                }
            }
        });
        back=(Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });

    }

    private void goAhead() {
        Intent intent=new Intent(this, FindParkingActivity.class);
        Bundle bundle = new Bundle();
        startActivity(intent);
    }
    public void back(){
        Intent intent=new Intent(this, HomeActivity.class);
        Bundle bundle = new Bundle();
        startActivity(intent);
    }

    public void save(){
        RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "https://shareparkservices.herokuapp.com/vehiculos";
        HashMap<String, String> vehicleParam = new HashMap<String, String>();
        vehicleParam.put("plate", placa.getText().toString());
        vehicleParam.put("brand", marca.getText().toString());
        vehicleParam.put("model", modelo.getText().toString());
        vehicleParam.put("color", color.getText().toString());
        vehicleParam.put("vehicleType", tvehiculo.toString());
        vehicleParam.put("owner_id", "1014281682");
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
