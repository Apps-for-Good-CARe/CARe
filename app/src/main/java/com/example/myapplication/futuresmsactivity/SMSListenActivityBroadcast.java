package com.example.myapplication.futuresmsactivity;

// Made with the help of https://github.com/pfeuffer/SMSloc/tree/bd3edbaaafcc1f593049e4d76bc4f28f47a5de9c/src/de/pfeufferweb/android/whereru

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

class SMSListenActivityBroadcast {
    private static final String BROADCAST_NEW_REQUEST = "newRequest";

    static void updateActivity(Context context) {
        Intent intent = new Intent(
                SMSListenActivityBroadcast.BROADCAST_NEW_REQUEST);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    static void register(Context context, BroadcastReceiver receiver) {
        LocalBroadcastManager.getInstance(context)
                .registerReceiver(
                        receiver,
                        new IntentFilter(
                                SMSListenActivityBroadcast.BROADCAST_NEW_REQUEST));
    }
}