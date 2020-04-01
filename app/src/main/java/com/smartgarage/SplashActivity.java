package com.smartgarage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
