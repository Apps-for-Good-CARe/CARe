package com.example.myapplication.futuresmsactivity;

// Made with the help of https://github.com/pfeuffer/SMSloc/tree/bd3edbaaafcc1f593049e4d76bc4f28f47a5de9c/src/de/pfeufferweb/android/whereru

import java.util.Locale;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        SMSSettings settings = new SMSSettings(context);
        String requestText = settings.getRequestText();
        boolean active = settings.getActive();

        Log.d("SmsReceiver", "request text: " + requestText + "; active: "
                + active);

        Bundle bundle = intent.getExtras();
        if (active && bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            for (int i = 0; i < pdus.length; i++) {
                SmsMessage msg = SmsMessage.createFromPdu((byte[]) pdus[i]);
                String origin = msg.getOriginatingAddress();
                String message = msg.getMessageBody().toString().trim();
                if (message
                        .trim()
                        .toUpperCase(Locale.getDefault())
                        .equals(requestText.trim().toUpperCase(
                                Locale.getDefault()))) {
                    Intent startService = new Intent(context, SMSSendService.class);
                    startService.putExtra("receiver", origin);
                    context.startService(startService);
                }
            }
        }
    }

}

