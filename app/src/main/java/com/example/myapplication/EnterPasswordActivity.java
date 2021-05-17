package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EnterPasswordActivity extends AppCompatActivity {

    EditText enteredpassword;
    Button button;
    String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_password);
        //private
//load the password


        SharedPreferences settings=getSharedPreferences("PREFS",0);
        password = settings.getString("pin", "");
        Log.d("Sriya", password+"correctpass");
        enteredpassword= findViewById(R.id.EnterPassword);
        button= findViewById(R.id.EnterPassbutton);


        //  @Override
        button.setOnClickListener(view -> {
            String text = enteredpassword.getText().toString();
            Log.d("Sriya", text+"entered");
            //There's a logic error somewhere here I think. Even when the password is right, it says that it is wrong.
            if(text.equals(password)){
             //enter the app, will need to change this to the map screen/algorithm that andrew's working on
                Intent intent = new Intent(getApplicationContext(), CreatePasswordActivity.class);
                startActivity(intent);
                finish();
            }
            else {
                Toast.makeText(EnterPasswordActivity.this, "Wrong pin!", Toast.LENGTH_SHORT).show();

            }
       });
    }
}