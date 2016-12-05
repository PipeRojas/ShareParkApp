package eci.cosw.edu.sharepark;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView registerScreen = (TextView) findViewById(R.id.link_to_register);


        registerScreen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });

        Button bm2 = (Button) findViewById(R.id.btnLogin);

        bm2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                mapa();

            }
        });
        Button regis=(Button) findViewById(R.id.link_to_register);
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrar();
            }
        });

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
