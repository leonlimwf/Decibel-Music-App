package com.example.musicsteam;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import gr.net.maroulis.library.EasySplashScreen;
public class splashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EasySplashScreen config = new EasySplashScreen(splashScreen.this)
                .withFullScreen()
                .withTargetActivity(startScreen.class)
                .withSplashTimeOut(3000)
                .withBackgroundColor(Color.parseColor("#141526"))
                .withLogo(R.drawable.splashscreen);
        View easySplashScreen = config.create();
        setContentView(easySplashScreen);
    }
}