package com.example.myapplication;

// Made with the help of https://github.com/pfeuffer/SMSloc/tree/bd3edbaaafcc1f593049e4d76bc4f28f47a5de9c/src/de/pfeufferweb/android/whereru

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class SMSSendService extends Service {
    private final IBinder binder = new LocalBinder();
    private final SMSRequestHandler requestHandler = new SMSRequestHandler(this);

    public class LocalBinder extends Binder {
        SMSSendService getService() {
            return SMSSendService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final String receiver = intent.getExtras().getString("receiver");
        SMSActiveLocationRequest newRequestResult = requestHandler
                .newRequest(receiver);
        getPosition(newRequestResult);
        SMSListenActivityBroadcast.updateActivity(this);
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private void getPosition(final SMSActiveLocationRequest request) {
        new SMSTimedLocationSender(this, request).start();
    }
}