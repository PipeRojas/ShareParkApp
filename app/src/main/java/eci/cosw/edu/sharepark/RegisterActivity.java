package eci.cosw.edu.sharepark;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView loginScreen = (TextView) findViewById(R.id.link_to_login);


        loginScreen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                finish();
            }
        });
        Button bm1 = (Button) findViewById(R.id.btnRegister);
        bm1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder(RegisterActivity.this);
                alert.setPositiveButton("OK", null);
                alert.show();
            }
        });

    }
}
