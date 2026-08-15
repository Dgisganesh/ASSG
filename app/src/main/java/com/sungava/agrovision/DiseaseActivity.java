package com.sungava.agrovision;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DiseaseActivity extends AppCompatActivity {

    private RadioGroup radioDiseases;

    private TextView txtPlant;

    private ImageView imgPlantPreview;

    private RadioButton radioHealthy;
    private RadioButton radioDisease1;
    private RadioButton radioDisease2;
    private RadioButton radioDisease3;

    private String selectedPlant;

    private String imageUriString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Load the correct layout FIRST
        setContentView(R.layout.activity_disease);


        // Find views

        radioDiseases =
                findViewById(R.id.radioDiseases);

        txtPlant =
                findViewById(R.id.txtPlant);

        imgPlantPreview =
                findViewById(R.id.imgPlantPreview);

        radioHealthy =
                findViewById(R.id.radioHealthy);

        radioDisease1 =
                findViewById(R.id.radioEarlyBlight);

        radioDisease2 =
                findViewById(R.id.radioLateBlight);

        radioDisease3 =
                findViewById(R.id.radioLeafMold);


        Button btnCheckDisease =
                findViewById(R.id.btnCheckDisease);


        // Get selected plant

        selectedPlant =
                getIntent().getStringExtra("plant");


        // Get image

        imageUriString =
                getIntent().getStringExtra("imageUri");


        // Show plant

        if (selectedPlant != null) {

            txtPlant.setText(
                    "Plant: " + selectedPlant
            );

            setupDiseases(selectedPlant);
        }


        // Show image

        if (imageUriString != null
                && !imageUriString.isEmpty()) {

            try {

                Uri imageUri =
                        Uri.parse(imageUriString);

                imgPlantPreview.setImageURI(
                        imageUri
                );

            } catch (Exception e) {

                e.printStackTrace();
            }
        }


        // Check Disease button

        btnCheckDisease.setOnClickListener(v -> {

            int selectedId =
                    radioDiseases
                            .getCheckedRadioButtonId();


            if (selectedId == -1) {

                Toast.makeText(
                        this,
                        "Please select a disease.",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }


            RadioButton selectedRadio =
                    findViewById(selectedId);


            String disease =
                    selectedRadio
                            .getText()
                            .toString();


            // Open ResultActivity

            Intent intent =
                    new Intent(
                            DiseaseActivity.this,
                            ResultActivity.class
                    );


            intent.putExtra(
                    "plant",
                    selectedPlant
            );


            intent.putExtra(
                    "disease",
                    disease
            );


            intent.putExtra(
                    "imageUri",
                    imageUriString
            );


            startActivity(intent);
        });
    }


    private void setupDiseases(String plant) {

        // Make all buttons visible first

        radioDisease1.setVisibility(
                RadioButton.VISIBLE
        );

        radioDisease2.setVisibility(
                RadioButton.VISIBLE
        );

        radioDisease3.setVisibility(
                RadioButton.VISIBLE
        );


        // TOMATO

        if (plant.equals("Tomato")) {

            radioDisease1.setText(
                    "Early Blight"
            );

            radioDisease2.setText(
                    "Late Blight"
            );

            radioDisease3.setText(
                    "Leaf Mold"
            );
        }


        // POTATO

        else if (plant.equals("Potato")) {

            radioDisease1.setText(
                    "Early Blight"
            );

            radioDisease2.setText(
                    "Late Blight"
            );

            radioDisease3.setVisibility(
                    RadioButton.GONE
            );
        }


        // RICE

        else if (plant.equals("Rice")) {

            radioDisease1.setText(
                    "Rice Blast"
            );

            radioDisease2.setText(
                    "Brown Spot"
            );

            radioDisease3.setVisibility(
                    RadioButton.GONE
            );
        }


        // WHEAT

        else if (plant.equals("Wheat")) {

            radioDisease1.setText(
                    "Rust"
            );

            radioDisease2.setText(
                    "Powdery Mildew"
            );

            radioDisease3.setVisibility(
                    RadioButton.GONE
            );
        }


        // CORN

        else if (plant.equals("Corn")) {

            radioDisease1.setText(
                    "Corn Leaf Blight"
            );

            radioDisease2.setVisibility(
                    RadioButton.GONE
            );

            radioDisease3.setVisibility(
                    RadioButton.GONE
            );
        }


        // UNKNOWN

        else {

            radioDisease1.setText(
                    "Unknown Disease"
            );

            radioDisease2.setVisibility(
                    RadioButton.GONE
            );

            radioDisease3.setVisibility(
                    RadioButton.GONE
            );
        }
    }
}