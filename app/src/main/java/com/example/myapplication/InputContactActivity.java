package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// Made with the help of Mrs. Taricco, https://youtu.be/jjL4R-aiwPE, https://developer.android.com/training/contacts-provider/modify-data#create-an-intent, and https://youtu.be/fJEFZ6EOM9o

// Automatically make 911 an emergency contact?

public class InputContactActivity extends AppCompatActivity {

    private TextView textView;
    private TextView phoneView;
    private EditText editText;

    public static final String SHARED_PREFS = "shared prefs";
    public static final String NAME= "text";
    public static final String PHONE = "num";

    private String nameCon1;
    private String phoneCon1;
    private String nameCon2;
    private String phoneCon2;
    private String nameCon3;
    private String phoneCon3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputcontacts);

        // Initiating the tabhost
        TabHost tabHost = findViewById(R.id.tabhost);

        // Setting up the tab host
        tabHost.setup();

        // Code for adding first tab to the tabhost
        TabHost.TabSpec spec = tabHost.newTabSpec("Contact 1");
        spec.setContent(R.id.contact1);

        // Setting the name of the first tab as "Contact 1"
        spec.setIndicator("Contact 1");

        // Adding the tab to tabhost
        tabHost.addTab(spec);

        // Code for adding second tab to the tabhost
        spec = tabHost.newTabSpec("Contact 2");
        spec.setContent(R.id.contact2);

        // Setting the name of the second tab as "Contact 2"
        spec.setIndicator("Contact 2");
        tabHost.addTab(spec);

        // Code for adding the third tab to the tabhost
        spec = tabHost.newTabSpec("Contact 3");
        spec.setContent(R.id.contact3);

        // Setting the name of the third tab as "Contact 3"
        spec.setIndicator("Contact 3");
        tabHost.addTab(spec);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

            Log.d("InputContactActivity", "TAB ID: " + tabId);
            EditText name1 = findViewById(R.id.EmergencyContactInputName1);
            EditText phone1 = findViewById(R.id.EmergencyContactInputPhone1);
            EditText name2 = findViewById(R.id.EmergencyContactInputName2);
            EditText phone2 = findViewById(R.id.EmergencyContactInputPhone2);
            EditText name3 = findViewById(R.id.EmergencyContactInputName3);
            EditText phone3 = findViewById(R.id.EmergencyContactInputPhone3);


            // Uses switch condition to store each tab with contact's information

            switch(tabId) {

                // Inputs the first contact, if applicable


                case "1":

                    if (!name1.getText().toString().isEmpty() || !phone1.getText().toString().isEmpty()) {
                        saveData();
                        loadData();
                        updateViews();
                    }

                    else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Emergency Contact 1 is empty", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                    break;


                // Inputs the first contact, if applicable

                case "2":

                    if (!name2.getText().toString().isEmpty() || !phone2.getText().toString().isEmpty()) {
                        saveData();
                        loadData();
                        updateViews();
                    }

                    else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Emergency Contact 2 is empty", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                    break;

                 // Inputs the third contact, if applicable

                case "3":

                    if (!name3.getText().toString().isEmpty() || !phone3.getText().toString().isEmpty()) {
                        saveData();
                        loadData();
                        updateViews();
                    }

                    else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Emergency Contact 2 is empty", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                    break;

            }

            }

        });

    }

     // Saves the contact information using Shared Preferences

    public void saveData() {

        EditText name1 = findViewById(R.id.EmergencyContactInputName1);
        EditText phone1 = findViewById(R.id.EmergencyContactInputPhone1);
        EditText name2 = findViewById(R.id.EmergencyContactInputName2);
        EditText phone2 = findViewById(R.id.EmergencyContactInputPhone2);
        EditText name3 = findViewById(R.id.EmergencyContactInputName3);
        EditText phone3 = findViewById(R.id.EmergencyContactInputPhone3);

        SharedPreferences sharedPreferences1 = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
        editor1.putString(NAME, name1.getText().toString());
        editor1.putString(PHONE, phone1.getText().toString());
        editor1.apply();

        SharedPreferences sharedPreferences2 = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedPreferences2.edit();
        editor2.putString(NAME, name2.getText().toString());
        editor2.putString(PHONE, phone2.getText().toString());
        editor2.apply();

        SharedPreferences sharedPreferences3 = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor3 = sharedPreferences3.edit();
        editor3.putString(NAME, name3.getText().toString());
        editor3.putString(PHONE, phone3.getText().toString());
        editor3.apply();

        Toast.makeText(this, "Contact(s) saved", Toast.LENGTH_SHORT).show();

    }

    public void loadData() {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        nameCon1 = sharedPreferences.getString(NAME, "");
        phoneCon1 = sharedPreferences.getString(PHONE, "");
        nameCon2 = sharedPreferences.getString(NAME, "");
        phoneCon2 = sharedPreferences.getString(PHONE, "");
        nameCon3 = sharedPreferences.getString(NAME, "");
        phoneCon3 = sharedPreferences.getString(PHONE, "");

    }

    public void updateViews() {

        textView.setText(nameCon1);
        phoneView.setText(phoneCon1);
        textView.setText(nameCon2);
        phoneView.setText(phoneCon2);
        textView.setText(nameCon3);
        phoneView.setText(phoneCon3);

    }
}
