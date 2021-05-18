package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;

//Made with the help of Mrs. Taricco, https://youtu.be/jjL4R-aiwPE, https://developer.android.com/training/contacts-provider/modify-data#create-an-intent, and https://youtu.be/fJEFZ6EOM9o

public class InputContactActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "CONTACT_PREFS";

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

        loadDataAndUpdateView();

    }

    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "onStop caller", Toast.LENGTH_LONG).show();

        // Save data back to SharedPreference onStop()
        saveData();

    }

    // Saves the contact information using Shared Preferences

    private void saveData() {

        Log.d("TESTER1", "Calling saveData() method");

        EditText name1 = findViewById(R.id.EmergencyContactInputName1);
        EditText phone1 = findViewById(R.id.EmergencyContactInputPhone1);
        EditText name2 = findViewById(R.id.EmergencyContactInputName2);
        EditText phone2 = findViewById(R.id.EmergencyContactInputPhone2);
        EditText name3 = findViewById(R.id.EmergencyContactInputName3);
        EditText phone3 = findViewById(R.id.EmergencyContactInputPhone3);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("ContactName1", name1.getText().toString());
        editor.putString("ContactPhone1", phone1.getText().toString());
        editor.putString("ContactName2", name2.getText().toString());
        editor.putString("ContactPhone2", phone2.getText().toString());
        editor.putString("ContactName3", name3.getText().toString());
        editor.putString("ContactPhone3", phone3.getText().toString());

        editor.apply();

        Toast.makeText(this, "Contact(s) saved", Toast.LENGTH_SHORT).show();

    }

    private void loadDataAndUpdateView() {

        String notFound = "";
        Log.d("TESTER2", "Calling loadDataAndUpdateView() method");

        // Get the SharedPreferences by filename
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, 0);

        // Get all six contact data fields from SharedPreferences
        String nameCon1 = sharedPreferences.getString("ContactName1", notFound);
        String phoneCon1 = sharedPreferences.getString("ContactPhone1", notFound);
        String nameCon2 = sharedPreferences.getString("ContactName2", notFound);
        String phoneCon2 = sharedPreferences.getString("ContactPhone2", notFound);
        String nameCon3 = sharedPreferences.getString("ContactName3", notFound);
        String phoneCon3 = sharedPreferences.getString("ContactPhone3", notFound);

        // Get all six EditText widgets from the XML activity
        EditText name1 = findViewById(R.id.EmergencyContactInputName1);
        EditText phone1 = findViewById(R.id.EmergencyContactInputPhone1);
        EditText name2 = findViewById(R.id.EmergencyContactInputName2);
        EditText phone2 = findViewById(R.id.EmergencyContactInputPhone2);
        EditText name3 = findViewById(R.id.EmergencyContactInputName3);
        EditText phone3 = findViewById(R.id.EmergencyContactInputPhone3);

        // Get all six EditText widgets with each specific contact data information
        name1.setText(nameCon1);
        phone1.setText(phoneCon1);
        name2.setText(nameCon2);
        phone2.setText(phoneCon2);
        name3.setText(nameCon3);
        phone3.setText(phoneCon3);

    }

}













