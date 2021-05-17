package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
//import android.content.Context;

import android.widget.EditText;
import android.Manifest;


public class MainActivity extends AppCompatActivity {
    Button btNotification;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    Button sendBtn;
    String message;
   // private final InputContactActivity contacts;
    //private final Context context;

   // public MainActivity(Context context) {
     //   this.context = context;
       // contacts = new InputContactActivity(context);
   // }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btNotification = findViewById(R.id.bt_notification);

        sendBtn = (Button) findViewById(R.id.btnSendSMS);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMSMessage();
            }
        });


        btNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(MainActivity.this);
                myAlertBuilder.setTitle("CARe has detected some suspicious activity on your route!");
                myAlertBuilder.setMessage("Are you safe?");
                myAlertBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "You clicked yes.", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), EnterPasswordActivity.class);
                        startActivity(i);
                        finish();
                    }
                });
                myAlertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "You clicked no", Toast.LENGTH_SHORT).show();
                    }
                });
                myAlertBuilder.show();
                String message = "This is a notification example.";
                NotificationCompat.Builder builder = new NotificationCompat.Builder(
                        MainActivity.this
                )
                        .setContentTitle("New Notification").setContentText(message).setAutoCancel(true);


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

            protected void sendSMSMessage() {
                   // phoneNo = phoneCon1.getText().toString();
                   // message = txtMessage.getText().toString();
                    String phoneNo = "5083806075";
                    String message = "hi";
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNo, null, message, null, null);
                Toast.makeText(getApplicationContext(), "SMS sent.",
                        Toast.LENGTH_LONG).show();
                    //if (ContextCompat.checkSelfPermission(this,
                    //        Manifest.permission.SEND_SMS)
                      //      != PackageManager.PERMISSION_GRANTED) {
                       // if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                         //       Manifest.permission.SEND_SMS)) {
                        //} else {
                       //     ActivityCompat.requestPermissions(this,
                         //           new String[]{Manifest.permission.SEND_SMS},
                       //             MY_PERMISSIONS_REQUEST_SEND_SMS);
                       // }
                    //}
                }

               // @Override
               // public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {

                //switch (requestCode) {
                  //      case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                    //        if (grantResults.length > 0
                      //              && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        //        SmsManager smsManager = SmsManager.getDefault();
                          //      smsManager.sendTextMessage(phoneNo, null, message, null, null);
                            //    Toast.makeText(getApplicationContext(), "SMS sent.",
                          //              Toast.LENGTH_LONG).show();
                 //           } else {
                   //             Toast.makeText(getApplicationContext(),
                     //                   "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                       //         return;
                         //   }
                   //     }
                   // }

                //}
           // }

    //    });

}