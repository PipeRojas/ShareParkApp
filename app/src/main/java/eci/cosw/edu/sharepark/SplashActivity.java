package eci.cosw.edu.sharepark;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by alejandra on 7/12/16.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        try {
            Thread.sleep(1500);
            goAhead();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void goAhead(){
        Intent intent=new Intent(this, HomeActivity.class);
        Bundle bundle = new Bundle();
        startActivity(intent);

    }
}
