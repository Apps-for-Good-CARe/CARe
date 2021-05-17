package com.example.myapplication.futuresmsactivity;

// if statement, only goes through if the user said that they were in danger or if they did not enter the proper PIN. Will be finalized later.

// Made with the help of Mrs. Taricco, https://youtu.be/FknRti6n_F8, https://programmerworld.co/android/how-to-track-your-location-using-gps-and-send-it-over-sms-in-your-andoid-app-complete-source-code/, and https://github.com/pfeuffer/SMSloc/tree/bd3edbaaafcc1f593049e4d76bc4f28f47a5de9c/src/de/pfeufferweb/android/whereru

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.telephony.SmsManager;

import com.example.myapplication.R;

public class SMSLocationSender {

    private final Context context;
    private final SMSSettings settings;
    private final SMSRequestHandler requestHandler;

    public SMSLocationSender(Context context) {
        this.context = context;
        settings = new SMSSettings(context);
        requestHandler = new SMSRequestHandler(context);
    }

    public void sendToPhone(Location location, String phoneNumber) {
        requestHandler.send(location, phoneNumber);
    }

    public void sendNetwork(Location location, String phoneNumber) {
        sendSMS(format(location, R.string.locationResponseNetwork), phoneNumber);
    }

    public void sendNoLocation(String phoneNumber) {
        sendSMS(context.getString(R.string.locationUnknown), phoneNumber);
    }

    private void sendSMS(String message, final String receiver) {
        PendingIntent intent = PendingIntent.getActivity(context, 0,
                new Intent(context, SMSReceiver.class), 0);
        SmsManager.getDefault().sendTextMessage(receiver, null, message,
                intent, null);
    }

    private String format(Location location, int text) {
        return context.getString(text,
                formatDegreeIfWanted(location.getLatitude()),
                formatDegreeIfWanted(location.getLongitude()),
                location.getAccuracy(), location.getSpeed(), getAge(location));
    }

    private String formatDegreeIfWanted(double d) {
        if (settings.getGoogleMaps()) {
            return new SMSDegreeFormatter().format(d);
        } else {
            return Double.toString(d);
        }
    }

    private long getAge(Location location) {
        return (System.currentTimeMillis() - location.getTime()) / 1000;
    }

}

