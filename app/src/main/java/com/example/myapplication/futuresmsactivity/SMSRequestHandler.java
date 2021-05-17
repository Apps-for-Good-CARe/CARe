package com.example.myapplication.futuresmsactivity;

// Made with the help of https://github.com/pfeuffer/SMSloc/tree/bd3edbaaafcc1f593049e4d76bc4f28f47a5de9c/src/de/pfeufferweb/android/whereru

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.example.myapplication.R;

//import de.pfeufferweb.android.whereru.repository.LocationRequest;
//import de.pfeufferweb.android.whereru.repository.RequestRepository;
//import de.pfeufferweb.android.whereru.repository.SimpleLocation;

public class SMSRequestHandler {
    private final Context context;
    private final SMSNotifications notifications;
    private final SMSSettings settings;

    public SMSRequestHandler(Context context) {
        this.context = context;
        this.notifications = new SMSNotifications(context);
        this.settings = new SMSSettings(context);
    }

    SMSActiveLocationRequest newRequest(String receiver) {
        SMSLocationRequest request = new SMSRequestRepository(context)
                .createRequest(receiver);
        int notificationId = notifications.newRequest(receiver);
        Toast.makeText(context,
                context.getString(R.string.toastRequest, receiver),
                Toast.LENGTH_LONG).show();
        SMSListenActivityBroadcast.updateActivity(context);
        return new SMSActiveLocationRequest(request, notificationId);
    }

    void noGps(SMSActiveLocationRequest request) {
        request.request.setNoGps();
        updateRequest(request.request);
        notifications.noGps(request.request.getRequester(),
                request.notificationId);
    }

    void success(SMSActiveLocationRequest request, SMSSimpleLocation location) {
        request.request.setSuccess(location);
        updateRequest(request.request);
        notifications.success(request.request.getRequester(),
                request.notificationId);
    }

    void aborted(SMSActiveLocationRequest request) {
        request.request.setAborted();
        updateRequest(request.request);
        notifications.aborted(request.request.getRequester(),
                request.notificationId);
    }

    void noFix(SMSActiveLocationRequest request) {
        request.request.setNoLocation();
        updateRequest(request.request);
        notifications.noFix(request.request.getRequester(),
                request.notificationId);
    }

    private void updateRequest(SMSLocationRequest request) {
        new SMSRequestRepository(context).updateRequest(request);
        SMSListenActivityBroadcast.updateActivity(context);
    }

    public void networkFix(SMSActiveLocationRequest request,
                           SMSSimpleLocation location) {
        request.request.setNetwork(location);
        updateRequest(request.request);
        notifications.network(request.request.getRequester(),
                request.notificationId);
    }

    public void networkFixNoGps(SMSActiveLocationRequest request,
                                SMSSimpleLocation location) {
        request.request.setNetworkNoGps(location);
        updateRequest(request.request);
        notifications.network(request.request.getRequester(),
                request.notificationId);
    }

    public void send(Location location, String phoneNumber) {
        sendSMS(format(location, R.string.locationResponse), phoneNumber);
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