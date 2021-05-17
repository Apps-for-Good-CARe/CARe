package com.example.myapplication.futuresmsactivity;

// Made with the help of https://github.com/pfeuffer/SMSloc/tree/bd3edbaaafcc1f593049e4d76bc4f28f47a5de9c/src/de/pfeufferweb/android/whereru

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

import androidx.core.app.ActivityCompat;

public class SMSTimedLocationSender extends Thread {

    private static final float ACCEPTED_ACCURACY = 30f;
    private static final int WAIT_TIME = 1000;

    private final LocationManager locationManager;
    private final Context context;
    private final SMSSettings settings;
    private final SMSRequestHandler requestHandler;
    private final SMSActiveLocationRequest request;

    private long startTime;
    private Location lastLocation;

    public SMSTimedLocationSender(Context context, SMSActiveLocationRequest request) {
        this.context = context;
        this.requestHandler = new SMSRequestHandler(context);
        this.settings = new SMSSettings(context);
        this.request = request;
       // this.Location = Location;
        this.locationManager = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    public synchronized void run() {
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            tryNetworkLocationNoGps();
            return;
        }
        startTime = System.currentTimeMillis();
        UpdateThread updateThread = new UpdateThread();
        updateThread.start();
        try {
            while (!isGoodEnough() && inTime() && isActive()) {
                try {
                    this.wait(WAIT_TIME);
                    Log.d("TimedLocationProvider", "checking...");
                } catch (InterruptedException e) {
                }
            }
        } finally {
            updateThread.close();
        }
        if (isActive()) {
            if (lastLocation == null) {
                tryNetworkLocationNoFix();
            } else {
                sendSuccess();
            }
        } else {
            requestHandler.aborted(request);
        }
    }

    private void sendSuccess() {
        requestHandler.success(
                request,
                new SMSSimpleLocation(lastLocation.getLongitude(), lastLocation.getLatitude()));
                new SMSRequestHandler(context).send(lastLocation, request.request.getRequester());
    }

     private void tryNetworkLocationNoFix() {
         if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
             // TODO: Consider calling
             //     ActivityCompat#requestPermissions
             // here to request the missing permissions, and then overriding
             // public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
             // to handle the case where the user grants the permission. See the documentation
             // for ActivityCompat#requestPermissions for more details.
             return;
         }
         lastLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
         if (lastLocation == null) {
             requestHandler.noFix(request);
             new SMSRequestHandler(context).sendNoLocation(request.request
                     .getRequester());
         } else {
             requestHandler.networkFix(request,
                     new SMSSimpleLocation(lastLocation.getLongitude(), lastLocation.getLatitude()));
             new SMSRequestHandler(context).sendNetwork(lastLocation,
                     request.request.getRequester());
         }
     }

    private void tryNetworkLocationNoGps() {
        if (settings.getUseNetwork()) {
            Log.d("TimedLocationSender", "looking for network location");
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            lastLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        if (lastLocation == null) {
            requestHandler.noGps(request);
            new SMSRequestHandler(context).sendNoLocation(request.request
                    .getRequester());
        } else {
            requestHandler.networkFixNoGps(request, new SMSSimpleLocation(
                    lastLocation.getLongitude(), lastLocation.getLatitude()));
            new SMSRequestHandler(context).sendNetwork(lastLocation,
                    request.request.getRequester());
        }
    }


    private boolean isActive() {
        return settings.getActive();
    }

    private boolean inTime() {
        boolean inTime = (System.currentTimeMillis() - startTime) < settings
                .getSeconds() * 1000;
        Log.d("TimedLocationProvider", "in time: " + inTime);
        return inTime;
    }

    private boolean isGoodEnough() {
        boolean goodEnough = lastLocation != null
                && lastLocation.getAccuracy() < ACCEPTED_ACCURACY;
        Log.d("TimedLocationProvider", "good enough: " + goodEnough);
        return goodEnough;
    }

    private class UpdateThread extends Thread implements LocationListener {
        @Override
        public void run() {
            Log.d("TimedLocationProvider", "looper prepare");
            Looper.prepare();
            Log.d("TimedLocationProvider", "requesting location updates");
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
            Log.d("TimedLocationProvider", "looper loop");
            Looper.loop();
        }

        public void close() {
            locationManager.removeUpdates(this);
            Log.d("TimedLocationProvider", "looper quit");
            if (Looper.myLooper() != null) {
                Looper.myLooper().quit();
            }
        }

        @Override
        public void onLocationChanged(Location actLocation) {
            Log.d("TimedLocationProvider", "got location update");
            if (isBetter(actLocation)) {
                lastLocation = actLocation;
                synchronized (SMSTimedLocationSender.this) {
                    SMSTimedLocationSender.this.notify();
                }
            }
        }

        private boolean isBetter(Location actLocation) {
            return lastLocation == null || actLocation != null
                    && lastLocation.getAccuracy() >= actLocation.getAccuracy();
        }

        @Override
        public void onProviderDisabled(String arg0) {
        }

        @Override
        public void onProviderEnabled(String arg0) {
        }

        @Override
        public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
        }
    }
}