package com.sungava.agrovision;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private LinearLayout cardDetectDisease;
    private LinearLayout cardHistory;
    private LinearLayout cardTreatment;
    private LinearLayout cardExpert;
    private LinearLayout cardSettings;
    private LinearLayout cardAgroStore;
    private LinearLayout cardCropRecommendation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        // Find cards

        cardDetectDisease =
                findViewById(R.id.cardDetectDisease);

        cardCropRecommendation =
                findViewById(
                        R.id.cardCropRecommendation);

        cardHistory =
                findViewById(R.id.cardHistory);

        cardTreatment =
                findViewById(R.id.cardTreatment);

        cardExpert =
                findViewById(R.id.cardExpert);

        cardAgroStore =
                findViewById(
                        R.id.cardAgroStore
                );

        cardSettings =
                findViewById(R.id.cardSettings);


        // Detect Disease

        cardDetectDisease.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            DetectActivity.class
                    );

            startActivity(intent);
        });

        cardCropRecommendation.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            CropRecommendationActivity.class
                    );

            startActivity(intent);

        });


        // History

        cardHistory.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            HistoryActivity.class
                    );

            startActivity(intent);
        });


        // Treatment

        cardTreatment.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            TreatmentGuideActivity.class
                    );

            startActivity(intent);
        });


        // Expert

        cardExpert.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            ExpertChatActivity.class
                    );

            startActivity(intent);
        });

        // Agro Store

        cardAgroStore.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            AgroStoreActivity.class
                    );

            startActivity(intent);
        });

        //Logout button
        Button btnLogout =
                findViewById(
                        R.id.btnLogout
                );


        btnLogout.setOnClickListener(v -> {

            new androidx.appcompat.app.AlertDialog.Builder(
                    MainActivity.this
            )
                    .setTitle("Logout")
                    .setMessage("Are you sure you want to logout?")
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("Logout", (dialog, which) -> {

                        logoutUser();

                    })
                    .show();

        });

        // Settings

        cardSettings.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            SettingsActivity.class
                    );

            startActivity(intent);


        });
    }
    private void logoutUser() {

        SharedPreferences preferences =
                getSharedPreferences(
                        "PlantDoctorPrefs",
                        MODE_PRIVATE
                );


        preferences
                .edit()
                .putBoolean(
                        "isLoggedIn",
                        false
                )
                .apply();


        Intent intent =
                new Intent(
                        MainActivity.this,
                        LoginActivity.class
                );


        intent.setFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK
        );

        startActivity(intent);

        finish();

    }
}