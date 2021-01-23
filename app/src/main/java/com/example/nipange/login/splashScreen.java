package com.example.nipange.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.nipange.R;
import com.google.firebase.FirebaseApp;

public class splashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Material_Light_NoActionBar_Fullscreen);
        setContentView(R.layout.activity_splash_screen);
        FirebaseApp.initializeApp(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(splashScreen.this, MainActivity.class));
                finish();
            }
        },2000);
    }
}