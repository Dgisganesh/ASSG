package com.sungava.agrovision;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class TreatmentActivity extends AppCompatActivity {

    private TextView txtTreatmentPlant;
    private TextView txtTreatmentDisease;
    private TextView txtOrganic;
    private TextView txtChemical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_treatment);

        txtTreatmentPlant =
                findViewById(R.id.txtTreatmentPlant);

        txtTreatmentDisease =
                findViewById(R.id.txtTreatmentDisease);

        txtOrganic =
                findViewById(R.id.txtOrganic);

        txtChemical =
                findViewById(R.id.txtChemical);

        // Get data

        String plant =
                getIntent().getStringExtra("plant");

        String disease =
                getIntent().getStringExtra("disease");

        // Show plant

        if (plant != null) {

            txtTreatmentPlant.setText(
                    "Plant: " + plant
            );
        }

        // Show disease

        if (disease != null) {

            txtTreatmentDisease.setText(
                    "Disease: " + disease
            );

            showTreatment(disease);
        }
    }


    private void showTreatment(String disease) {

        if (disease.equals("Healthy Plant")) {

            txtOrganic.setText(
                    "• Continue regular plant care\n" +
                            "• Maintain proper watering\n" +
                            "• Keep the growing area clean\n" +
                            "• Monitor the plant regularly"
            );

            txtChemical.setText(
                    "No chemical treatment is recommended " +
                            "for a healthy plant."
            );
        }

        else if (disease.equals("Early Blight")) {

            txtOrganic.setText(
                    "• Remove badly affected leaves\n" +
                            "• Keep leaves dry when possible\n" +
                            "• Improve air circulation\n" +
                            "• Remove infected plant debris\n" +
                            "• Use appropriate organic disease-management products"
            );

            txtChemical.setText(
                    "Use only a fungicide that is legally " +
                            "registered for this crop and disease. " +
                            "Follow the product label for application " +
                            "rate, timing and harvest interval."
            );
        }

        else if (disease.equals("Late Blight")) {

            txtOrganic.setText(
                    "• Remove severely affected plant parts\n" +
                            "• Improve air circulation\n" +
                            "• Avoid unnecessary leaf wetness\n" +
                            "• Remove infected plant debris"
            );

            txtChemical.setText(
                    "Use an appropriately registered fungicide " +
                            "for the crop and disease. Follow the " +
                            "product label and local agricultural advice."
            );
        }

        else if (disease.equals("Leaf Mold")) {

            txtOrganic.setText(
                    "• Improve ventilation\n" +
                            "• Reduce excessive humidity\n" +
                            "• Remove heavily affected leaves\n" +
                            "• Avoid prolonged leaf wetness"
            );

            txtChemical.setText(
                    "Use a locally registered fungicide only " +
                            "when appropriate. Follow the product " +
                            "label and local agricultural guidance."
            );
        }

        else if (disease.equals("Brown Spot")) {

            txtOrganic.setText(
                    "• Remove severely affected leaves\n" +
                            "• Keep the field clean\n" +
                            "• Maintain balanced plant nutrition\n" +
                            "• Avoid unnecessary leaf wetness"
            );

            txtChemical.setText(
                    "Use only an approved product for the " +
                            "specific crop and disease. Follow the " +
                            "label instructions."
            );
        }

        else if (disease.equals("Rice Blast")) {

            txtOrganic.setText(
                    "• Maintain balanced crop nutrition\n" +
                            "• Avoid excessive nitrogen application\n" +
                            "• Remove heavily affected material\n" +
                            "• Monitor the crop regularly"
            );

            txtChemical.setText(
                    "Use a registered rice blast management " +
                            "product only when recommended locally. " +
                            "Follow the label instructions."
            );
        }

        else if (disease.equals("Rust")) {

            txtOrganic.setText(
                    "• Remove heavily affected leaves when practical\n" +
                            "• Improve air circulation\n" +
                            "• Monitor nearby plants"
            );

            txtChemical.setText(
                    "Use only a fungicide registered for the " +
                            "specific crop and rust disease. Follow " +
                            "local recommendations and the product label."
            );
        }

        else if (disease.equals("Powdery Mildew")) {

            txtOrganic.setText(
                    "• Improve air circulation\n" +
                            "• Avoid excessive humidity\n" +
                            "• Remove severely affected leaves\n" +
                            "• Use suitable organic disease-management options"
            );

            txtChemical.setText(
                    "Use a locally registered treatment if needed. " +
                            "Follow the product label carefully."
            );
        }

        else if (disease.equals("Corn Leaf Blight")) {

            txtOrganic.setText(
                    "• Remove heavily affected plant material\n" +
                            "• Keep the field clean\n" +
                            "• Monitor nearby plants\n" +
                            "• Use good crop-management practices"
            );

            txtChemical.setText(
                    "Use a product registered for corn and the " +
                            "specific disease. Follow local agricultural " +
                            "recommendations and the product label."
            );
        }

        else {

            txtOrganic.setText(
                    "Monitor the plant carefully and consult " +
                            "a qualified agricultural professional."
            );
            txtChemical.setText(
                    "Do not apply a chemical product until the " +
                            "disease and crop have been properly identified."
            );
        }
    }
}