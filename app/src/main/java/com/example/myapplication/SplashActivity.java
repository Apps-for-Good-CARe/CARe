package com.example.myapplication;

// 15 (ALR) + 20 (DF) + 325 (LA) + 27 (LAB) + 88 (

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //load the password
        SharedPreferences settings=getSharedPreferences("PREFS",0);
        password = settings.getString("password", "");
        Handler handler = new Handler();
        handler.postDelayed (new Runnable(){
            @Override
            public void run(){
                if (password.equals("")) {

                    // if there is no password
                    Intent intent = new Intent(getApplicationContext(), CreatePasswordActivity.class);
                    startActivity(intent);
                    finish();
                } else{
                    //if there is a password
                    Intent intent = new Intent(getApplicationContext(), EnterPasswordActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 2000);
    }
}