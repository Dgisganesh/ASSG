package com.sungava.agrovision;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;

    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);


        etEmail =
                findViewById(
                        R.id.etEmail
                );

        etPassword =
                findViewById(
                        R.id.etPassword
                );

        btnLogin =
                findViewById(
                        R.id.btnLogin
                );


        preferences =
                getSharedPreferences(
                        "PlantDoctorPrefs",
                        MODE_PRIVATE
                );


        btnLogin.setOnClickListener(v -> {

            loginUser();

        });
    }

    private void loginUser() {

        String email =
                etEmail.getText()
                        .toString()
                        .trim();

        String password =
                etPassword.getText()
                        .toString()
                        .trim();

        if (email.isEmpty()) {

            etEmail.setError(
                    "Enter email"
            );

            return;
        }


        if (password.isEmpty()) {

            etPassword.setError(
                    "Enter password"
            );

            return;
        }


        // DEMO LOGIN

        if (email.equals(
                "demo@Assg_sungava.com"
        )
                && password.equals(
                "123456"
        )) {

            // Save login status

            preferences
                    .edit()
                    .putBoolean(
                            "isLoggedIn",
                            true
                    )
                    .apply();


            Toast.makeText(
                    this,
                    "Login successful!",
                    Toast.LENGTH_SHORT
            ).show();


            Intent intent =
                    new Intent(
                            LoginActivity.this,
                            MainActivity.class
                    );

            startActivity(intent);

            finish();

        } else {

            Toast.makeText(
                    this,
                    "Invalid email or password",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}