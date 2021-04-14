package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

// Made with the help of Mrs. Taricco, https://youtu.be/jjL4R-aiwPE, and https://developer.android.com/training/contacts-provider/modify-data#create-an-intent

// Automatically make 911 an emergency contact?

public class InputContactActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputcontacts);

        /* addContact.setOnClickListener(new View.OnClickListener) { */
    }

    public void addContact(View v) {

         EditText name = findViewById(R.id.EmergencyContactInputName);
         EditText phone = findViewById(R.id.EmergencyContactInputPhone);
         Button addContact = findViewById(R.id.AddContactButton);

        if (!name.getText().toString().isEmpty() || !phone.getText().toString().isEmpty()) {

            Intent intent = new Intent(Intent.ACTION_INSERT);
            Intent intent1 = intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
            Intent intentName = intent.putExtra(ContactsContract.Intents.Insert.NAME, name.getText().toString());
            Intent intentPhone = intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone.getText().toString());

            if (intent.resolveActivity(getPackageManager()) != null)
                startActivity(intent);

            else
                // Toast.makeText(context: MainActivity.this, text: "There is no app that supports this action", Toast.LENGTH_SHORT);
                System.out.println("There is no app that supports this action");


        }

        else
            // Toast.makeText(context:MainActivity.this, text:"Please fill in all fields", Toast.LENGTH_SHORT);
            System.out.println("Please fill in all fields");

    }

}