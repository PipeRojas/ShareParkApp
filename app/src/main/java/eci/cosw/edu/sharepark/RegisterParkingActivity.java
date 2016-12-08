package eci.cosw.edu.sharepark;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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
import java.util.Random;

/**
 * Created by david
 */

public class RegisterParkingActivity extends AppCompatActivity {

    private EditText address;
    private EditText cost;
    private EditText stratum;
    private Boolean covert;
    private Boolean home;
    private EditText height;
    private Boolean covertSelected;
    private Boolean homeSelected;
    private EditText width;
    private EditText length;
    private Button save;
    private Button back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_parking);
        address = (EditText) findViewById(R.id.addressParkRegister);
        cost=(EditText) findViewById(R.id.cost);
        stratum=(EditText) findViewById(R.id.stratum);
        height=(EditText) findViewById(R.id.heightParkRegister);
        width=(EditText) findViewById(R.id.widthParkRegister);
        length=(EditText) findViewById(R.id.lengthParkRegister);
        final Spinner spinnerCovert = (Spinner) findViewById(R.id.spinnerCovert);
        String[] valoresCovert = {"Si","No"};
        spinnerCovert.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valoresCovert));
        spinnerCovert.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {
                String c=(String) adapterView.getItemAtPosition(position);
                if(c.equals("Si")){covert=true;}else{covert=false;}
                covertSelected=true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                covertSelected=false;
            }
        });
        final Spinner spinnerHome = (Spinner) findViewById(R.id.spinnerHome);
        String[] valoresHome = {"Casa","Conjunto cerrado"};
        spinnerHome.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valoresHome));
        spinnerHome.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {
                String h=(String) adapterView.getItemAtPosition(position);
                if(h.equals("Casa")){home=true;}else{home=false;}
                homeSelected=true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                homeSelected=false;
            }
        });
        save=(Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(address.getText().toString().length()==0){
                    address.setError(getString(R.string.no_olvides) +getString(R.string.la_direccion)+getString(R.string.estacionamiento));
                }else if(cost.getText().toString().length()==0){
                    cost.setError(getString(R.string.no_olvides) +getString(R.string.cos_minute)+getString(R.string.estacionamiento));
                }else if(stratum.getText().toString().length()==0){
                    stratum.setError(getString(R.string.no_olvides) +getString(R.string.the_stratum)+getString(R.string.estacionamiento));
                }else if(height.getText().toString().length()==0){
                    height.setError(getString(R.string.no_olvides) +getString(R.string.the_he)+getString(R.string.estacionamiento));
                }else if(width.getText().toString().length()==0){
                    width.setError(getString(R.string.no_olvides) +getString(R.string.the_wi)+getString(R.string.estacionamiento));
                }else if(length.getText().toString().length()==0){
                    length.setError(getString(R.string.no_olvides) +getString(R.string.the_le)+getString(R.string.estacionamiento));
                }else if(Integer.parseInt(stratum.getText().toString())>6||Integer.parseInt(stratum.getText().toString())<0){
                    stratum.setError(getString(R.string.stratum_error));
                }else if(covertSelected==false||homeSelected==false){
                    address.setError(getString(R.string.detail_park));
                }
                else{
                    guardar();
                    go();
                }

            }
        });
        back=(Button) findViewById(R.id.backRp);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go();
            }
        });
    }

    private void go() {
        Intent intent=new Intent(this, HomeActivity.class);
        Bundle bundle = new Bundle();
        startActivity(intent);
    }
    public void guardar() {

        RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "https://shareparkservices.herokuapp.com/parkings/";
        HashMap<String, String> parksParam = new HashMap<String, String>();
        parksParam.put("owner_id",LoginActivity.user.getId().toString());
        parksParam.put("covert",covert.toString());
        parksParam.put("home", home.toString());
        parksParam.put("height", height.getText().toString());
        parksParam.put("width", width.getText().toString());
        parksParam.put("length", length.getText().toString());
        parksParam.put("available", "false");
        parksParam.put("costMinute", cost.getText().toString());
        Random rn=new Random();
        Double id= rn.nextDouble();
        Double x=4.60+id;
        Double y=74.0+id;
        parksParam.put("x", x.toString());
        parksParam.put("y", "-"+y.toString());
        parksParam.put("address", address.getText().toString());
        parksParam.put("stratum", stratum.getText().toString());

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





