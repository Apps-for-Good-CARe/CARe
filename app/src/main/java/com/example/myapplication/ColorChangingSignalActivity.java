package com.example.myapplication;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
//This class models a button that changes color to show how much danger a rider-share user is in during their journey (by means of a color-system)
//It was made with the help of https://stackoverflow.com/questions/52558780/how-to-change-background-color-to-materialbutton-from-android-support-design-pro
public class ColorChangingSignalActivity extends AppCompatActivity {
    Button colorButton;
    Boolean onRoute;
    int susCount;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        colorButton = findViewById(R.id.colorButton);
        colorButton.setOnClickListener(v -> {

                    Toast.makeText(ColorChangingSignalActivity.this, "You clicked the Contact Emergency Contact Button.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), SendSMSMessage.class);
                    startActivity(i);
                    finish();


            });

        //if statement, andrew makes boolean in his code that i store in a shared preference?
        Drawable background = colorButton.getBackground();
        //if the user is on route or on a route that is "safe" because it is one of the most common, the button will be green.
if(onRoute=true){
    colorButton.setBackgroundResource(R.color.safe);
}
//if the user is not on route the button will turn yellow to indicate some suspicious activity but nothing too severe yet
else{
    colorButton.setBackgroundResource(R.color. suspicious);
    susCount++;
}
//if the route has been deemed "suspicious" 3 times in a row, then the user is officially in danger and the button will turn red, sending a
        //notification to the user that there is suspicious activity
if(susCount==3){
    colorButton.setBackgroundResource(R.color.danger);
    Intent intent = new Intent(getApplicationContext(), NotificationCreator.class);
    startActivity(intent);
    finish();
}

    }



}
