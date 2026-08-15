package com.sungava.agrovision;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);


        new android.os.Handler().postDelayed(() -> {

            SharedPreferences preferences =
                    getSharedPreferences(
                            "PlantDoctorPrefs",
                            MODE_PRIVATE
                    );


            boolean isLoggedIn =
                    preferences.getBoolean(
                            "isLoggedIn",
                            false
                    );


            Intent intent;


            if (isLoggedIn) {

                // Already logged in
                intent =
                        new Intent(
                                SplashActivity.this,
                                MainActivity.class
                        );

            } else {

                // Not logged in
                intent =
                        new Intent(
                                SplashActivity.this,
                                LoginActivity.class
                        );
            }


            startActivity(intent);

            finish();

        }, 2000);
    }
}