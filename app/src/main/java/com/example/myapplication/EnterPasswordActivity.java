package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EnterPasswordActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_password);

//load the password
        SharedPreferences settings=getSharedPreferences("PREFS",0);
        password = settings.getString("password", "");
        editText= (EditText) findViewById(R.id.editTextTextPassword3);
        button= (Button) findViewById(R.id.button2);


        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
String text = editText.getText().toString();
//There's a logic error somewhere here I think. Even when the password is right, it says that it is wrong.
if(text.equals(password)){
    //enter the app, will need to change this to the map screen/algorithm that andrew's working on
    Intent intent = new Intent(getApplicationContext(), EnterPasswordActivity.class);
    startActivity(intent);
    finish();
}else{
    Toast.makeText(EnterPasswordActivity.this, "Wrong pin!", Toast.LENGTH_SHORT).show();

}
            }

        });
    }
}