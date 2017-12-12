package com.example.fr_philippe.gestionemploye;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Intent t;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer=new Timer();
        timer.scheduleAtFixedRate(new MyTimertask(),2000,4000);
    }

    public class MyTimertask extends TimerTask{

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    startActivityDots();
                }
            });
        }
    }

    public void startActivityDots(){
                t=new Intent(this,SlideShowActivity.class);
                startActivity(t);
                timer.cancel();
    }
}
