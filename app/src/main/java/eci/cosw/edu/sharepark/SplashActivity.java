package eci.cosw.edu.sharepark;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by alejandra on 7/12/16.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        ImageView img=(ImageView) findViewById(R.id.imageView7);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    goAhead();
            }
        });
    }

    public void goAhead(){
        Intent intent=new Intent(this, LoginActivity.class);
        Bundle bundle = new Bundle();
        startActivity(intent);

    }
}
