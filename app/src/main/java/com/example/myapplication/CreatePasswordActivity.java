package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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

        editTextTextPassword1= (EditText) findViewById(R.id.editTextTextPassword1);
        editTextTextPassword2= (EditText) findViewById(R.id.editTextTextPassword2);
       button= (Button) findViewById(R.id.button);

       button.setOnClickListener(new View.OnClickListener(){

           @Override
           public void onClick(View view){
String text1 = editTextTextPassword1.getText().toString();
               String text2 = editTextTextPassword2.getText().toString();


               if(text1.equals("")||text2.equals("")) {
                   //there's no password
                   Toast.makeText(CreatePasswordActivity.this, "No pin entered!", Toast.LENGTH_SHORT).show();
               }else{
                   if(text1.equals(text2)) {
                       //save the password
                       SharedPreferences settings = getSharedPreferences("PREFS", 0);
                       SharedPreferences.Editor editor = settings.edit();
                       editor.putString("pin", text1);
                       editor.apply();

                       //enter the app, will need to change this to the map screen/algorithm that andrew's working on
                       Intent intent = new Intent(getApplicationContext(), EnterPasswordActivity.class);
                       startActivity(intent);
                       finish();
                   }else{
                       //there is no match between the pins
                       Toast.makeText(CreatePasswordActivity.this, "Pins do not match!", Toast.LENGTH_SHORT).show();

                   }
               }
           }
       });
    }
}