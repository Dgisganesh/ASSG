package com.sungava.agrovision;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class CropRecommendationActivity extends AppCompatActivity {

    // Soil views
    private Spinner spinnerSoil;

    private EditText etPh;
    private EditText etNitrogen;
    private EditText etPhosphorus;
    private EditText etPotassium;
    private EditText etRainfall;

    // Buttons
    private Button btnRecommend;
    private Button btnUploadSoilReport;

    // TextViews
    private TextView txtRecommendation;
    private TextView txtReportStatus;

    // SOIL REPORT FILE PICKER

    private final ActivityResultLauncher<String> reportLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.GetContent(),
                    uri -> {

                        if (uri != null) {

                            txtReportStatus.setText(
                                    "✅ Soil report uploaded successfully."
                            );

                            Toast.makeText(
                                    this,
                                    "Soil report selected",
                                    Toast.LENGTH_SHORT
                            ).show();

                        }

                    }
            );

    // ON CREATE

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_crop_recommendation
        );



        // FIND VIEWS

        spinnerSoil =
                findViewById(
                        R.id.spinnerSoil
                );

        etPh =
                findViewById(
                        R.id.etph
                );

        etNitrogen =
                findViewById(
                        R.id.etNitrogen
                );

        etPhosphorus =
                findViewById(
                        R.id.etPhosphorus
                );

        etPotassium =
                findViewById(
                        R.id.etPotassium
                );

        etRainfall =
                findViewById(
                        R.id.etRainfall
                );

        btnRecommend =
                findViewById(
                        R.id.btnRecommend
                );

        btnUploadSoilReport =
                findViewById(
                        R.id.btnUploadSoilReport
                );

        txtRecommendation =
                findViewById(
                        R.id.txtRecommendation
                );

        txtReportStatus =
                findViewById(
                        R.id.txtReportStatus
                );



        // SOIL TYPE LIST


        String[] soilTypes = {

                "Select Soil Type",
                "Loamy",
                "Sandy",
                "Clay",
                "Silty"

        };


        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        soilTypes
                );


        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );


        spinnerSoil.setAdapter(
                adapter
        );



        // UPLOAD SOIL REPORT


        btnUploadSoilReport.setOnClickListener(v -> {

            reportLauncher.launch("*/*");

        });



        // RECOMMEND CROP BUTTON


        btnRecommend.setOnClickListener(v -> {

            recommendCrop();

        });

    }



    // CROP RECOMMENDATION


    private void recommendCrop() {

        // Get soil type

        String soil =
                spinnerSoil
                        .getSelectedItem()
                        .toString();


        // Check soil type

        if (soil.equals("Select Soil Type")) {

            Toast.makeText(
                    this,
                    "Please select soil type.",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }



        // GET INPUT VALUES


        String phText =
                etPh.getText()
                        .toString()
                        .trim();


        String nitrogenText =
                etNitrogen.getText()
                        .toString()
                        .trim();


        String phosphorusText =
                etPhosphorus.getText()
                        .toString()
                        .trim();


        String potassiumText =
                etPotassium.getText()
                        .toString()
                        .trim();


        String rainfallText =
                etRainfall.getText()
                        .toString()
                        .trim();



        // CHECK EMPTY VALUES


        if (phText.isEmpty()
                || nitrogenText.isEmpty()
                || phosphorusText.isEmpty()
                || potassiumText.isEmpty()
                || rainfallText.isEmpty()) {

            Toast.makeText(
                    this,
                    "Please enter all soil values.",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }



        // CONVERT TEXT TO NUMBERS


        double ph;
        double nitrogen;
        double phosphorus;
        double potassium;
        double rainfall;


        try {

            ph =
                    Double.parseDouble(phText);

            nitrogen =
                    Double.parseDouble(nitrogenText);

            phosphorus =
                    Double.parseDouble(phosphorusText);

            potassium =
                    Double.parseDouble(potassiumText);

            rainfall =
                    Double.parseDouble(rainfallText);

        } catch (NumberFormatException e) {

            Toast.makeText(
                    this,
                    "Please enter valid numbers.",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }



        // RECOMMENDATION


        String recommendation;



        // LOAMY SOIL


        if (soil.equals("Loamy")
                && ph >= 6.0
                && ph <= 7.0
                && rainfall >= 100) {

            recommendation =
                    "🥇 Recommended Crop: Tomato\n\n"
                            + "🥈 Alternative: Maize\n\n"
                            + "🥉 Alternative: Wheat\n\n"
                            + "🌱 Reason:\n"
                            + "Loamy soil with a near-neutral "
                            + "pH is suitable for many crops.";


        }



        // CLAY SOIL


        else if (soil.equals("Clay")
                && ph >= 5.5
                && ph <= 7.0
                && rainfall >= 150) {

            recommendation =
                    "🥇 Recommended Crop: Rice\n\n"
                            + "🥈 Alternative: Wheat\n\n"
                            + "🥉 Alternative: Maize\n\n"
                            + "🌱 Reason:\n"
                            + "Clay soil can retain water well "
                            + "and may be suitable for water-loving crops.";


        }



        // SANDY SOIL


        else if (soil.equals("Sandy")
                && ph >= 5.5
                && ph <= 7.5) {

            recommendation =
                    "🥇 Recommended Crop: Maize\n\n"
                            + "🥈 Alternative: Potato\n\n"
                            + "🥉 Alternative: Groundnut\n\n"
                            + "🌱 Reason:\n"
                            + "Sandy soil provides good drainage.";


        }



        // SILTY SOIL


        else if (soil.equals("Silty")
                && ph >= 6.0
                && ph <= 7.5) {

            recommendation =
                    "🥇 Recommended Crop: Wheat\n\n"
                            + "🥈 Alternative: Maize\n\n"
                            + "🥉 Alternative: Vegetables\n\n"
                            + "🌱 Reason:\n"
                            + "Silty soil can be suitable for "
                            + "many agricultural crops.";


        }



        // DEFAULT


        else {

            recommendation =
                    "🌱 Suggested Crop: Wheat\n\n"
                            + "The current soil conditions "
                            + "do not strongly match our demo rules.\n\n"
                            + "Please consult local agricultural "
                            + "guidance for a more accurate recommendation.";

        }



        // SHOW RESULT

        txtRecommendation.setText(
                recommendation
        );

    }

}