package com.sungava.agrovision;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private Spinner spinnerLanguage;

    private Switch switchVoice;

    private Switch switchNotifications;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_settings
        );


        // Find views

        spinnerLanguage =
                findViewById(
                        R.id.spinnerLanguage
                );

        switchVoice =
                findViewById(
                        R.id.switchVoice
                );

        switchNotifications =
                findViewById(
                        R.id.switchNotifications
                );


        // SharedPreferences

        preferences =
                getSharedPreferences(
                        "PlantDoctorSettings",
                        MODE_PRIVATE
                );


        // Language list

        String[] languages = {

                "English",
                "नेपाली",
                "हिन्दी"

        };


        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        languages
                );


        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );


        spinnerLanguage.setAdapter(
                adapter
        );

        int savedLanguage =
                preferences.getInt(
                        "language",
                        0
                );

        spinnerLanguage.setSelection(
                savedLanguage
        );

        spinnerLanguage.setOnItemSelectedListener(
                new android.widget.AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(
                            android.widget.AdapterView<?> parent,
                            android.view.View view,
                            int position,
                            long id) {

                        preferences.edit()
                                .putInt(
                                        "language",
                                        position
                                )
                                .apply();
                    }

                    @Override
                    public void onNothingSelected(
                            android.widget.AdapterView<?> parent) {

                    }
                }
        );

        // Load saved settings

        boolean voiceEnabled =
                preferences.getBoolean(
                        "voiceEnabled",
                        true
                );


        boolean notificationsEnabled =
                preferences.getBoolean(
                        "notificationsEnabled",
                        true
                );


        switchVoice.setChecked(
                voiceEnabled
        );


        switchNotifications.setChecked(
                notificationsEnabled
        );


        // Save voice setting

        switchVoice.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {

                    preferences.edit()
                            .putBoolean(
                                    "voiceEnabled",
                                    isChecked
                            )
                            .apply();

                }
        );


        // Save notification setting

        switchNotifications
                .setOnCheckedChangeListener(
                        (buttonView, isChecked) -> {

                            preferences.edit()
                                    .putBoolean(
                                            "notificationsEnabled",
                                            isChecked
                                    )
                                    .apply();

                        }
                );
    }
}