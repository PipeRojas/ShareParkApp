package eci.cosw.edu.sharepark;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ServiceTimerActivity extends AppCompatActivity {
    
    private TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_timer);

        time =(TextView)findViewById(R.id.textView3);

        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                time.setText("Te quedan: " + millisUntilFinished / 1000 + " de servicio.");
                Button btn = (Button) findViewById(R.id.button3);
                btn.setEnabled(false);
            }

            public void onFinish() {

                time.setText("Tu servicio ha finalizado.");
                Button btn = (Button) findViewById(R.id.button3);
                btn.setEnabled(true);
            }
        }.start();
    }

}
