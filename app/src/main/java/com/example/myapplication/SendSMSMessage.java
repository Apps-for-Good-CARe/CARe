package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
//import com.example.myapplication.CreatePasswordActivity;

import android.support.v7.app.AppCompatActivity;

// Made with the help of Mrs. Taricco

public class SendSMSMessage extends AppCompatActivity {

    private final String SHARED_PREFS = "CONTACT_PREFS";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("TARICCO", "In SendSMSMessage Activity loadContactData() method");

        String notFound = "";

        // Get the SharedPreferences object, then get the contact phone numbers
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, 0);
        String contactphone1 = sharedPreferences.getString("ContactPhone1", notFound);
        String contactphone2 = sharedPreferences.getString("ContactPhone2", notFound);
        String contactphone3 = sharedPreferences.getString("ContactPhone3", notFound);

        // Debugging statements - just logging here to help
        Log.d("StoresPhone1", "Contact Phone 1: " + contactphone1);
        Log.d("StoresPhone2", "Contact Phone 2: " + contactphone2);
        Log.d("StoresPhone3", "Contact Phone 3: " + contactphone3);

        String message = "This is CARe, a ride safety app. Please make sure this contact is safe, as we suspect suspicious behavior due to their current route.";

        SmsManager smsManager = SmsManager.getDefault();
        if (!contactphone1.isEmpty()) {
            Log.d("Test1", "Sending a message to contact phone 1: " + contactphone1);
            smsManager.sendTextMessage(contactphone1, null, message, null, null);
        }

        if (!contactphone2.isEmpty()) {
            Log.d("Test2", "Sending a message to contact phone 2: " + contactphone2);
            smsManager.sendTextMessage(contactphone2, null, message, null, null);
        }

        if (!contactphone3.isEmpty()) {
            Log.d("Test3", "Sending a message to contact phone 3: " + contactphone3);
            smsManager.sendTextMessage(contactphone3, null, message, null, null);
        }
    }

    //protected void sendSMSMessage() {

    //    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

    //        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) { }

    //        else {

    //            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);

    //        }

    //    }

    //}

    //public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {

    //    final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;

    //    switch (requestCode) {

    //        case MY_PERMISSIONS_REQUEST_SEND_SMS: {

    //            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

    //                Toast.makeText(getApplicationContext(), "Permission granted.", Toast.LENGTH_LONG).show();

    //            } else {

    //                Toast.makeText(getApplicationContext(), "This app will not have the ability to send SMS messages to your emergency contacts.", Toast.LENGTH_LONG).show();
    //                return;

    //            }

    //        }

    //    }

    //}

}


