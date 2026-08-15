package com.sungava.agrovision;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TreatmentGuideActivity extends AppCompatActivity {

    private Spinner spinnerDisease;

    private TextView txtSelectedDisease;
    private TextView txtOrganic;
    private TextView txtChemical;
    private TextView txtSafety;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_treatment_guide
        );


        spinnerDisease =
                findViewById(
                        R.id.spinnerDisease
                );

        txtSelectedDisease =
                findViewById(
                        R.id.txtSelectedDisease
                );

        txtOrganic =
                findViewById(
                        R.id.txtOrganic
                );

        txtChemical =
                findViewById(
                        R.id.txtChemical
                );

        txtSafety =
                findViewById(
                        R.id.txtSafety
                );


        String[] diseases = {

                "Select Disease",
                "Healthy Plant",
                "Early Blight",
                "Late Blight",
                "Leaf Mold",
                "Brown Spot",
                "Rice Blast",
                "Rust",
                "Powdery Mildew",
                "Corn Leaf Blight"

        };


        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        diseases
                );


        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );


        spinnerDisease.setAdapter(adapter);


        spinnerDisease.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(
                            AdapterView<?> parent,
                            View view,
                            int position,
                            long id) {

                        String disease =
                                parent
                                        .getItemAtPosition(position)
                                        .toString();

                        showTreatment(disease);
                    }


                    @Override
                    public void onNothingSelected(
                            AdapterView<?> parent) {

                    }
                }
        );
    }


    private void showTreatment(String disease) {

        if (disease.equals("Select Disease")) {

            txtSelectedDisease.setText(
                    "Select a disease"
            );

            txtOrganic.setText(
                    "Choose a disease to see organic remedies."
            );

            txtChemical.setText(
                    "Choose a disease to see chemical treatment information."
            );

            return;
        }


        txtSelectedDisease.setText(
                "Treatment: " + disease
        );


        if (disease.equals("Healthy Plant")) {

            txtOrganic.setText(
                    "• Keep the plant properly watered.\n" +
                            "• Provide sufficient sunlight.\n" +
                            "• Remove damaged leaves.\n" +
                            "• Maintain good soil nutrition."
            );

            txtChemical.setText(
                    "No disease treatment is required."
            );

            txtSafety.setText(
                    "Continue normal plant care. " +
                            "Avoid unnecessary pesticide use."
            );

        }


        else if (disease.equals("Early Blight")) {

            txtOrganic.setText(
                    "• Remove badly affected leaves.\n" +
                            "• Keep leaves dry when possible.\n" +
                            "• Improve air circulation.\n" +
                            "• Use properly prepared compost and maintain plant nutrition."
            );

            txtChemical.setText(
                    "For a real crop, use only a pesticide " +
                            "registered for the crop and disease in your area. " +
                            "Follow the product label for dose, timing, " +
                            "protective equipment and harvest interval."
            );

            txtSafety.setText(
                    "Do not mix pesticides unless the label permits it. " +
                            "Use protective clothing and follow local agricultural guidance."
            );

        }


        else if (disease.equals("Late Blight")) {

            txtOrganic.setText(
                    "• Remove severely affected plant material.\n" +
                            "• Improve air circulation.\n" +
                            "• Avoid prolonged leaf wetness.\n" +
                            "• Remove volunteer or infected plants where appropriate."
            );

            txtChemical.setText(
                    "Late blight can spread rapidly. " +
                            "Use only a locally registered fungicide " +
                            "recommended for the crop and disease. " +
                            "Follow the product label exactly."
            );

            txtSafety.setText(
                    "Do not delay professional advice when disease " +
                            "is spreading quickly."
            );

        }


        else if (disease.equals("Leaf Mold")) {

            txtOrganic.setText(
                    "• Improve ventilation around plants.\n" +
                            "• Avoid excessive humidity.\n" +
                            "• Remove heavily affected leaves.\n" +
                            "• Water the soil rather than wetting foliage."
            );

            txtChemical.setText(
                    "Use only a fungicide registered for the crop " +
                            "and disease in your area. Follow the label."
            );

            txtSafety.setText(
                    "Keep greenhouse or growing areas well ventilated."
            );

        }


        else if (disease.equals("Brown Spot")) {

            txtOrganic.setText(
                    "• Remove severely affected leaves.\n" +
                            "• Maintain balanced plant nutrition.\n" +
                            "• Avoid unnecessary leaf wetness.\n" +
                            "• Keep the growing area clean."
            );

            txtChemical.setText(
                    "Use only locally registered crop-protection products " +
                            "when recommended for the specific crop and disease."
            );

            txtSafety.setText(
                    "Correct plant nutrition and field hygiene are important."
            );

        }


        else if (disease.equals("Rice Blast")) {

            txtOrganic.setText(
                    "• Maintain balanced nitrogen nutrition.\n" +
                            "• Avoid excessive nitrogen application.\n" +
                            "• Remove heavily infected material where practical.\n" +
                            "• Maintain good field management."
            );

            txtChemical.setText(
                    "Use only fungicides registered for rice blast " +
                            "in your country or region. Follow the label exactly."
            );

            txtSafety.setText(
                    "For serious outbreaks, contact a local agriculture expert."
            );

        }


        else if (disease.equals("Rust")) {

            txtOrganic.setText(
                    "• Remove heavily affected leaves when practical.\n" +
                            "• Improve airflow around plants.\n" +
                            "• Maintain proper plant nutrition."
            );

            txtChemical.setText(
                    "Use only a locally registered fungicide " +
                            "for the specific crop and rust disease."
            );

            txtSafety.setText(
                    "Always follow the product label and protective-equipment requirements."
            );

        }


        else if (disease.equals("Powdery Mildew")) {

            txtOrganic.setText(
                    "• Improve air circulation.\n" +
                            "• Avoid overcrowding plants.\n" +
                            "• Remove heavily affected leaves.\n" +
                            "• Keep the growing area clean."
            );

            txtChemical.setText(
                    "Use only products registered for powdery mildew " +
                            "on the specific crop in your area."
            );

            txtSafety.setText(
                    "Do not apply household chemicals or unlabelled pesticides."
            );

        }


        else if (disease.equals("Corn Leaf Blight")) {

            txtOrganic.setText(
                    "• Remove or manage infected crop residue appropriately.\n" +
                            "• Maintain good field hygiene.\n" +
                            "• Use suitable crop rotation where practical.\n" +
                            "• Avoid unnecessary leaf wetness."
            );

            txtChemical.setText(
                    "Use only a fungicide registered for corn leaf blight " +
                            "and follow the product label."
            );

            txtSafety.setText(
                    "For severe crop damage, consult a local agriculture expert."
            );
        }
    }
}