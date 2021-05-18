package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;
//This class models what it's like to enter a pin into the app after it has been created
//This class was made with the help of the following resource: https://www.youtube.com/watch?v=OOclvSIelcI&t=432s
//the timer aspect of this class was made with the help of the following resource: https://developer.android.com/reference/android/os/CountDownTimer
public class EnterPasswordActivity<view> extends AppCompatActivity {

    EditText enteredpassword;
    Button button;
    String password;
    int wrongPassCount = 0;
    EditText timer;
    long seconds;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_password);



//loads the the password that had been created in the CreatePassword Activty


        SharedPreferences settings = getSharedPreferences("PREFS", 0);
        password = settings.getString("pin", "");
        System.out.println(password);
        Log.d("Sriya", password + "correctpass");
        enteredpassword = findViewById(R.id.EnterPassword);
        timer = findViewById(R.id.timer);
        button = findViewById(R.id.EnterPassbutton);
      text = enteredpassword.getText().toString();
System.out.println(text);
        //the user has a minute to type the right pin in to confirm their security before emergency contacts are notified anyway
        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
             seconds =millisUntilFinished/1000;
                timer.setText(seconds+" seconds left to enter your pin correctly.");
                Log.d("Sriya", text + "entered");
/*compares pin entered to the pin that had been set up earlier to determine whether the user
            if safe or not and take further measures of needed*/


            }

            public void onFinish() {
//change to sms activity
                timer.setText("Time has run out! Your emergency contact will be notified!");
                Intent intent = new Intent(getApplicationContext(), InputContactActivity.class);
                startActivity(intent);
                Toast.makeText(EnterPasswordActivity.this, "Your emergency contact has been notified!", Toast.LENGTH_SHORT).show();
                finish();

            }
        }.start();

        button.setOnClickListener(v -> {
            text = enteredpassword.getText().toString();

System.out.println(text);
            if (text.equals(password)) {
                //return to the home/map page if the user dismisses the notification by inputting their pin in correctly and confirming their safety
                // will need to change this to the map screen/algorithm that andrew's working on
                Toast.makeText(EnterPasswordActivity.this, "Correct pin!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), CreatePasswordActivity.class);

                startActivity(intent);
                wrongPassCount = 0;
                Log.d("TEST", "password");
                finish();


            } else {
                wrongPassCount++;
                Toast.makeText(EnterPasswordActivity.this, "Wrong pin!", Toast.LENGTH_SHORT).show();
                //if the pin has been entered incorrectly 3 times, then a notification will be sent to the emergency contacts
                if (wrongPassCount == 3) {
                    Intent intent = new Intent(getApplicationContext(), SendSMSMessage.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(EnterPasswordActivity.this, "Your emergency contact has been notified!", Toast.LENGTH_SHORT).show();
                }

            }

        });




    };
}
