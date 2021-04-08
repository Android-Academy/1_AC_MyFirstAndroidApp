package com.vullnetlimani.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class SplashScreen extends AppCompatActivity {

    private static final long SPLASH_TIME_OUT = 500;
    private ConstraintLayout main_layout;
    private ImageView icon_image;
    private TextView title_textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.SplashScreenTheme);
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(intent);
        finish();

        //  setContentView(R.layout.activity_splash_screen);

//        main_layout = findViewById(R.id.main_layout);
//        icon_image = findViewById(R.id.icon_image);
//        title_textView = findViewById(R.id.title_textView);
//
//
//        main_layout.setBackgroundColor(getResources().getColor(R.color.primary));
//        icon_image.setColorFilter(Color.WHITE);
//        title_textView.setTextColor(Color.WHITE);
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        }, SPLASH_TIME_OUT);

    }
}