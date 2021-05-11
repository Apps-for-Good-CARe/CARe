package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btNotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btNotification = findViewById(R.id.bt_notification);
        btNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(MainActivity.this);
                myAlertBuilder.setTitle("CARe has detected some suspicious activity on your route!");
                myAlertBuilder.setMessage("Are you safe?");
                myAlertBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "You clicked yes.",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), EnterPasswordActivity.class);
                        startActivity(i);
                        finish();
                    }
                });
                myAlertBuilder.setNegativeButton("No" , new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        Toast.makeText(MainActivity.this,"You clicked no", Toast.LENGTH_SHORT).show();
                    }
                });
                myAlertBuilder.show();
                String message = "This is a notification example.";
                NotificationCompat.Builder builder = new NotificationCompat.Builder(
                        MainActivity.this
                )
                        .setSmallIcon(R.drawable.messagepic)
                        .setContentTitle("New Notification")
                        .setContentText(message)
                        .setAutoCancel(true);


                Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("message", message);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(0, builder.build());

            }


        });
    }
}