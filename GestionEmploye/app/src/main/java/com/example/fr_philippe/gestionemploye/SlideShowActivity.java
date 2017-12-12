package com.example.fr_philippe.gestionemploye;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

public class SlideShowActivity extends AppCompatActivity {
    ViewPager viewPager;
    LinearLayout sliderDotsPanel;
    private int dotsCount;
    private ImageView[] dots;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_show);
        viewPager=(ViewPager)findViewById(R.id.viewerPager);
        sliderDotsPanel=(LinearLayout)findViewById(R.id.SliderDots);

        ViewPagerAdapter viewPagerAdapter= new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        dotsCount=viewPagerAdapter.getCount();
        dots=new ImageView[dotsCount];

        for(int i=0; i<dotsCount; i++){
            dots[i]=new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.selected_item));
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8,0,8,0);
            sliderDotsPanel.addView(dots[i],params);
        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.no_selected_item));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(int i=0;i<dotsCount;i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.no_selected_item));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.selected_item));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new MyTimertask(),2000,4000);
    }
    public class MyTimertask extends TimerTask{

        @Override
        public void run() {
            SlideShowActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(viewPager.getCurrentItem()==0){
                        viewPager.setCurrentItem(1);
                    }else if(viewPager.getCurrentItem()==1){
                        viewPager.setCurrentItem(2);
                    }else if(viewPager.getCurrentItem()==2){
                        viewPager.setCurrentItem(3);
                    }else{
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

    public void onLoginView(View v){
        Intent k=new Intent(this,LoginActivity.class);
        startActivity(k);
    }
}
