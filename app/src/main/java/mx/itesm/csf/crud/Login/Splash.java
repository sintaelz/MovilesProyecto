package mx.itesm.csf.crud.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ImageView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import mx.itesm.csf.crud.R;

public class Splash extends AppCompatActivity {

    ImageView imgV;
    int waitTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        Random randomGen = new Random();

        int[] ids = new int[] {R.drawable.clothesplash, R.drawable.clothesplash2, R.drawable.clothesplash3, R.drawable.clothesplash4};
        int nextR = randomGen.nextInt(ids.length);
        imgV = (ImageView) findViewById(R.id.splash_imageview);
        waitTime = 5000;

        imgV.setImageDrawable(getResources().getDrawable(ids[nextR]));

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                finish();
                Intent mainIntent = new Intent().setClass(Splash.this, MainActivity.class);
                startActivity(mainIntent);
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, waitTime);
    }

}

