package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreatePasswordActivity extends AppCompatActivity {

    EditText editTextTextPassword1, editTextTextPassword2;
Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);

        editTextTextPassword1= findViewById(R.id.editTextTextPassword1);
        editTextTextPassword2= findViewById(R.id.editTextTextPassword2);
       button= findViewById(R.id.button);

       button.setOnClickListener(view -> {
           String text1 = editTextTextPassword1.getText().toString();
           Log.d("onClick.CreatePass", text1+"correctpass");
           String text2 = editTextTextPassword2.getText().toString();
           Log.d("onClick.CreatePass", text2+"correctpass");
           //store password
          // SharedPreferences settings=getSharedPreferences("PREFS",0);

           if(text1.equals("")||text2.equals("")) {
               //there's no password
               Toast.makeText(CreatePasswordActivity.this, "No pin entered!", Toast.LENGTH_SHORT).show();
           }else{
               if(text1.equals(text2)) {
                   //save the password
                   SharedPreferences settings = getSharedPreferences("PREFS", 0);
                   SharedPreferences.Editor editor = settings.edit();
                   editor.putString("pin", text2);
                   editor.apply();
                  // Intent i = new Intent(this, EnterPasswordActivity.class);
                   //enter the app, will need to change this to the map screen/algorithm that andrew's working on
                   Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                   startActivity(intent);
                   finish();
               }else{
                   //there is no match between the pins
                   Toast.makeText(CreatePasswordActivity.this, "Pins do not match!", Toast.LENGTH_SHORT).show();

               }
           }
       });
    }
}