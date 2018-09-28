package com.example.hp_pc.newsapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    public static int SPLASH_TIME_OUT=4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent home=new Intent(HomeActivity.this,MainActivity.class);
                startActivity(home);
                finish();
            }
        },SPLASH_TIME_OUT);

        TextView txt = (TextView) findViewById(R.id.header);
        Typeface font = Typeface.createFromAsset(getAssets(), "Algerian.ttf");
        txt.setTypeface(font);

    }
}
