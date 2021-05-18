package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.Toast;
//This class models the welcome page of our app and directs the user to pin set up and emergency contact set up
public class Welcome extends AppCompatActivity {
    Button PinSetup;
    Button ContactSetUp;
    Button Tap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        PinSetup = findViewById(R.id.PinSetup);
        ContactSetUp = findViewById(R.id.ContactSetUp);
        Tap= findViewById(R.id.tap);

        //guiding the user to the Pin Set-Up page
                PinSetup.setOnClickListener(v -> {

                Toast.makeText(Welcome.this, "You clicked Pin Set-Up.",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), CreatePasswordActivity.class);
                startActivity(i);
                finish();

            });
        //guiding the user to the Contact Set-Up page
            ContactSetUp.setOnClickListener(v -> {

                Toast.makeText(Welcome.this, "You clicked Contact Set-Up.", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), InputContactActivity.class);
                startActivity(i);
                finish();


            }
    );
        //guiding the user to the Map/navigation page
        //change to andrew's activity
        Tap.setOnClickListener(v -> {

            Toast.makeText(Welcome.this, "You clicked Continue.", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), NotificationCreator.class);
            startActivity(i);
            finish();
    });}}