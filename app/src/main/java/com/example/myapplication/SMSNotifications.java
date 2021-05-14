package com.example.myapplication;

// Made with the help of https://github.com/pfeuffer/SMSloc/tree/bd3edbaaafcc1f593049e4d76bc4f28f47a5de9c/src/de/pfeufferweb/android/whereru

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;

public class SMSNotifications {

    static int notificationCounter = 0;

    private final Context context;

    public SMSNotifications(Context context) {
        this.context = context;
    }

    public int newRequest(String phoneNumber) {
        int notificationId = ++notificationCounter;
        buildNotification(
                context.getString(R.string.notificationRequest, phoneNumber),
                notificationId);
        return notificationId;
    }

    public void success(String phoneNumber, int notificationId) {
        buildNotification(context.getString(R.string.notificationSentSuccess,
                phoneNumber), notificationId);
    }

    public void noFix(String phoneNumber, int notificationId) {
        buildNotification(
                context.getString(R.string.notificationSentNoFix, phoneNumber),
                notificationId);
    }

    public void noGps(String phoneNumber, int notificationId) {
        buildNotification(
                context.getString(R.string.notificationNoGps, phoneNumber),
                notificationId);
    }

    public void aborted(String phoneNumber, int notificationId) {
        buildNotification(
                context.getString(R.string.notificationAborted, phoneNumber),
                notificationId);
    }

    public void network(String phoneNumber, int notificationId) {
        buildNotification(context.getString(R.string.notificationSentNetwork,
                phoneNumber), notificationId);
    }

    private void buildNotification(String text, int notificationId) {
        Notification notification = new NotificationCompat.Builder(context)

                .setContentTitle(context.getString(R.string.notificationTitle))
                .setContentText(text)
                .setAutoCancel(true)
                .setContentIntent(
                        PendingIntent.getActivity(context, 0, new Intent(
                                context, SMSListenActivity.class), 0)).build();
        notify(notificationId, notification);
    }

    private void notify(int notificationId, Notification notification) {
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, notification);
    }
}
