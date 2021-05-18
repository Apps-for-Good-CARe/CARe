package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.support.v7.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;

//this class models a pin set-up mechanism
//It was created with the help of the following resource: https://www.youtube.com/watch?v=OOclvSIelcI&t=432s
public class CreatePasswordActivity extends AppCompatActivity {

    EditText editTextTextPassword1, editTextTextPassword2;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);

        editTextTextPassword1= findViewById(R.id.enteredPin);
        editTextTextPassword2= findViewById(R.id.reenteredPin);
        button= findViewById(R.id.button);

       //password is set when the button is clicked, dependent on whether the two fields match or not
        button.setOnClickListener(view -> {
            String Pin1 = editTextTextPassword1.getText().toString();
            Log.d("onClick.CreatePass", Pin1+"correctpass");
            String confirmPin = editTextTextPassword2.getText().toString();

        //checks if either of the pin fields are blank and informs the user
            if(Pin1.equals("")||confirmPin.equals("")) {
                //if the user doesn't enter a pin in the pin set up
                Toast.makeText(CreatePasswordActivity.this, "No pin entered!", Toast.LENGTH_SHORT).show();
            }

            else {
                if(Pin1.equals(confirmPin)) {
                    //checks if the pin and re-entered pin are the same
                    // saves the password as a sharedpreference so it can be used later
                    SharedPreferences settings = getSharedPreferences("PREFS", 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("pin", confirmPin);
                    editor.apply();
                    this.sendSMSMessage();

                    //after creating pin, user is taken back to welcome page to explore other options (emergency contact set-up)
                    Intent intent = new Intent(getApplicationContext(), Welcome.class);
                    startActivity(intent);
                    finish();
                }

                else {
                    //there is no match between the pins
                    Toast.makeText(CreatePasswordActivity.this, "Pins do not match!", Toast.LENGTH_SHORT).show();

                }

            }

        });

    }

    // Requests permission to use SMS

    protected void sendSMSMessage() {

        Log.d("SMSPERMISSION", "in sendSMSMessage() method");

        final int PERMISSION_REQUEST_CODE = 1;

        // Handling different versions of the SDK
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

                    // If the app has permission to send, the method checkSelfPermission()
                    // returns PERMISSION_GRANTED, and the app can proceed with the operation.
                    // If the app does not have permission, the method returns PERMISSION_DENIED,
                    // and the app has to explicitly ask the user for permission.
                    if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {

                        Log.d("TARICCO", "Permission denied to SEND_SMS - requesting it ...");
                        String[] permissions = {Manifest.permission.SEND_SMS};
                        requestPermissions(permissions, PERMISSION_REQUEST_CODE);


                    } else {  // Permission has already been granted

                        Intent intent = new Intent(this, SendSMSMessage.class);
                        startActivity(intent);

                    }


                }




    }
}