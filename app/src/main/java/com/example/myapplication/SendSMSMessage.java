package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SendSMSMessage extends AppCompatActivity {

    final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    String phone1;
    String phone2;
    String phone3;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // setContentView(R.layout.testing);

        // Button sendBtn = (Button) findViewById(R.id.button3);

        //sendBtn.setOnClickListener(new View.OnClickListener() {
        //  public void onClick(View view) {
        //    loadContactData();
    }
    // });




    // Loads the contact information taken from the Input Contacts Activity

    private void loadContactData(){

        SharedPreferences sharedPreferences = getSharedPreferences("shared prefs", Context.MODE_PRIVATE);
        SmsManager smsManager = SmsManager.getDefault();
        phone1 = sharedPreferences.getString("phoneCon1", "");
        phone2 = sharedPreferences.getString("phoneCon2", "");
        phone3 = sharedPreferences.getString("phoneCon3", "");
        String message = "This is CARe, a ride safety app. Please make sure this contact is safe, as we suspect suspicious behavior due to their current route.";

        if (!phone1.equals("")) {
            //smsManager.sendTextMessage(phone1, null, message, null, null);
            Log.d("Name", phone1);
        }

        if (!phone2.equals("")) {
            //smsManager.sendTextMessage(phone2, null, message, null, null);
            Log.d("Name", phone2);
        }

        if (!phone3.equals("")) {
            //smsManager.sendTextMessage(phone3, null, message, null, null);
            Log.d("Name", phone3);
        }

    }

    // Sends the SMS message

    protected void sendSMSMessage() {

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) { }

            else {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);

            }

        }

    }

    // Requests permission to use SMS messages on the user's phone

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {

        switch (requestCode) {

            case MY_PERMISSIONS_REQUEST_SEND_SMS: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(getApplicationContext(), "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                    return;

                }

            }

        }

    }

}


