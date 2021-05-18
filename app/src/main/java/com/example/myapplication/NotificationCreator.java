package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
//this class models the notification feature of our app
//It was made with the help of the following resources: https://www.youtube.com/watch?v=ov9HBdg33kM and https://www.youtube.com/watch?v=_5bSz4tsdP4
public class NotificationCreator extends AppCompatActivity {
    Button btNotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(NotificationCreator.this);
            myAlertBuilder.setTitle("CARe has detected some suspicious activity on your route!");
            myAlertBuilder.setMessage("Are you safe?");
            //User confirms with pin that they are indeed safe after they've clicked "yes"
            myAlertBuilder.setPositiveButton("yes", (dialog, which) -> {
                Toast.makeText(NotificationCreator.this, "You clicked yes.",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), EnterPasswordActivity.class);
                startActivity(i);
                finish();
            });
            //user says that they are not safe, so an SMS is sent to emergency contacts
            myAlertBuilder.setNegativeButton("No" , (dialog, which) -> {
                Toast.makeText(NotificationCreator.this,"You clicked no", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), SendSMSMessage.class);
            });


            myAlertBuilder.show();


    }
}