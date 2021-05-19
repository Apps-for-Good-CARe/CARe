package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;

import android.support.v7.app.AppCompatActivity;

// Made with the help of Mrs. Taricco

public class SendSMSMessage extends AppCompatActivity {

    private final String SHARED_PREFS = "CONTACT_PREFS";

    // Sends SMS message to emergency contacts
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("TARICCO", "In SendSMSMessage Activity loadContactData() method");

        this.sendMessage();
    }

    private void sendMessage() {
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

}


