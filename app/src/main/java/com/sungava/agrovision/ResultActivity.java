package com.sungava.agrovision;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class ResultActivity extends AppCompatActivity {

    private TextView txtResultPlant;
    private TextView txtResultDisease;
    private TextView txtSeverity;
    private TextView txtSymptoms;
    private ImageView imgResultPlant;
    private TextToSpeech textToSpeech;
    private String plant;
    private String disease;
    private String imageUriString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);


        // Find views

        txtResultPlant =
                findViewById(R.id.txtResultPlant);

        txtResultDisease =
                findViewById(R.id.txtResultDisease);

        txtSeverity =
                findViewById(R.id.txtSeverity);

        txtSymptoms =
                findViewById(R.id.txtSymptoms);

        imgResultPlant =
                findViewById(R.id.imgResultPlant);


        Button btnTreatment =
                findViewById(R.id.btnTreatment);

        Button btnVoice =
                findViewById(R.id.btnVoice);

        Button btnStopVoice =
                findViewById(R.id.btnStopVoice);

        RadioGroup languageGroup =
                findViewById(R.id.languageGroup);


        // Get data

        plant =
                getIntent().getStringExtra("plant");

        disease =
                getIntent().getStringExtra("disease");

        imageUriString =
                getIntent().getStringExtra("imageUri");

        saveHistory();


        // Show plant

        if (plant != null) {

            txtResultPlant.setText(
                    "Plant: " + plant
            );
        }


        // Show disease

        if (disease != null) {

            txtResultDisease.setText(
                    "Disease: " + disease
            );

            showDiseaseInformation(disease);
        }


        // Show image

        if (imageUriString != null
                && !imageUriString.isEmpty()) {

            try {

                Uri imageUri =
                        Uri.parse(imageUriString);

                imgResultPlant.setImageURI(
                        imageUri
                );

            } catch (Exception e) {

                e.printStackTrace();
            }
        }


        // Text To Speech

        textToSpeech =
                new TextToSpeech(
                        this,
                        status -> {

                            if (status ==
                                    TextToSpeech.SUCCESS) {

                                textToSpeech.setLanguage(
                                        Locale.ENGLISH
                                );
                            }
                        }
                );


        // Treatment button

        btnTreatment.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            ResultActivity.this,
                            TreatmentActivity.class
                    );

            intent.putExtra(
                    "plant",
                    plant
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


        // Voice button

        btnVoice.setOnClickListener(v -> {

            int selectedId =
                    languageGroup
                            .getCheckedRadioButtonId();

            if (selectedId == -1) {

                return;
            }

            RadioButton selectedLanguage =
                    findViewById(selectedId);

            String language =
                    selectedLanguage
                            .getText()
                            .toString();

            speakExplanation(language);
        });


        // Stop voice

        btnStopVoice.setOnClickListener(v -> {

            if (textToSpeech != null) {

                textToSpeech.stop();
            }
        });
    }


    private void showDiseaseInformation(
            String disease) {

        if (disease.equals("Healthy Plant")) {

            txtSeverity.setText(
                    "Severity: 🟢 LOW"
            );

            txtSymptoms.setText(
                    "• Leaves look healthy\n" +
                            "• No major disease symptoms detected\n" +
                            "• Continue regular plant care"
            );

        }

        else if (disease.equals("Early Blight")) {

            txtSeverity.setText(
                    "Severity: 🟡 MEDIUM"
            );

            txtSymptoms.setText(
                    "• Brown spots on leaves\n" +
                            "• Yellow areas around spots\n" +
                            "• Older leaves may dry first"
            );

        }

        else if (disease.equals("Late Blight")) {

            txtSeverity.setText(
                    "Severity: 🔴 HIGH"
            );

            txtSymptoms.setText(
                    "• Dark brown or black spots\n" +
                            "• Water-soaked appearance\n" +
                            "• Leaves may quickly become damaged"
            );

        }

        else if (disease.equals("Leaf Mold")) {

            txtSeverity.setText(
                    "Severity: 🟡 MEDIUM"
            );

            txtSymptoms.setText(
                    "• Yellow patches on leaves\n" +
                            "• Yellow-green discoloration\n" +
                            "• Mold-like growth may appear"
            );

        }

        else if (disease.equals("Brown Spot")) {

            txtSeverity.setText(
                    "Severity: 🟡 MEDIUM"
            );

            txtSymptoms.setText(
                    "• Brown spots on leaves\n" +
                            "• Leaf discoloration\n" +
                            "• Affected leaves may become weak"
            );

        }

        else if (disease.equals("Rice Blast")) {

            txtSeverity.setText(
                    "Severity: 🔴 HIGH"
            );

            txtSymptoms.setText(
                    "• Spindle-shaped leaf lesions\n" +
                            "• Gray or brown centers\n" +
                            "• Leaves may become damaged"
            );

        }

        else if (disease.equals("Rust")) {

            txtSeverity.setText(
                    "Severity: 🟡 MEDIUM"
            );

            txtSymptoms.setText(
                    "• Small rust-colored spots\n" +
                            "• Orange or brown powdery areas\n" +
                            "• Leaves may weaken"
            );

        }

        else if (disease.equals("Powdery Mildew")) {

            txtSeverity.setText(
                    "Severity: 🟡 MEDIUM"
            );

            txtSymptoms.setText(
                    "• White powdery patches\n" +
                            "• Leaves may curl\n" +
                            "• Plant growth may slow"
            );

        }

        else if (disease.equals("Corn Leaf Blight")) {

            txtSeverity.setText(
                    "Severity: 🔴 HIGH"
            );

            txtSymptoms.setText(
                    "• Long brown lesions\n" +
                            "• Damaged leaf tissue\n" +
                            "• Leaves may dry prematurely"
            );

        }

        else {

            txtSeverity.setText(
                    "Severity: 🟡 MEDIUM"
            );

            txtSymptoms.setText(
                    "• Symptoms require further observation\n" +
                            "• Check affected leaves carefully\n" +
                            "• Consider consulting an agriculture expert"
            );
        }
    }


    private void speakExplanation(
            String language) {

        if (textToSpeech == null) {
            return;
        }


        String message;

        Locale locale;


        // 🇳🇵 Nepali

        if (language.equals("नेपाली")) {

            locale =
                    new Locale("ne", "NP");

            message =
                    "तपाईंको " +
                            plant +
                            " बोटमा " +
                            disease +
                            " देखिएको छ। " +
                            "कृपया बोटको राम्रोसँग हेरचाह गर्नुहोस् " +
                            "र उपचारको जानकारी हेर्नुहोस्.";
        }


        // 🇮🇳 Hindi

        else if (language.equals("हिन्दी")) {

            locale =
                    new Locale("hi", "IN");

            message =
                    "आपके " +
                            plant +
                            " के पौधे में " +
                            disease +
                            " पाया गया है। " +
                            "कृपया पौधे की अच्छी तरह देखभाल करें " +
                            "और उपचार की जानकारी देखें.";
        }

        // 🇬🇧 English

        else {

            locale =
                    Locale.ENGLISH;

            message =
                    "Your " +
                            plant +
                            " plant may have " +
                            disease +
                            ". " +
                            "The current demo severity is " +
                            getSeverityForVoice() +
                            ". " +
                            "Please check the treatment information.";
        }


        int result =
                textToSpeech.setLanguage(
                        locale
                );


        if (result ==
                TextToSpeech.LANG_MISSING_DATA
                ||
                result ==
                        TextToSpeech.LANG_NOT_SUPPORTED) {

            textToSpeech.setLanguage(
                    Locale.ENGLISH
            );

            message =
                    "Your " +
                            plant +
                            " plant may have " +
                            disease +
                            ". Please check the treatment information.";
        }

        textToSpeech.speak(
                message,
                TextToSpeech.QUEUE_FLUSH,
                null,
                "plant_explanation"
        );
    }


    @Override
    protected void onDestroy() {

        if (textToSpeech != null) {

            textToSpeech.stop();

            textToSpeech.shutdown();
        }

        super.onDestroy();
    }
    private void saveHistory() {

        android.content.SharedPreferences preferences =
                getSharedPreferences(
                        "PlantDoctorHistory",
                        MODE_PRIVATE
                );


        String oldHistory =
                preferences.getString(
                        "history",
                        ""
                );


        String severity =
                "MEDIUM";


        if (disease != null) {

            if (disease.equals("Healthy Plant")) {

                severity = "LOW";

            } else if (
                    disease.equals("Late Blight")
                            ||
                            disease.equals("Rice Blast")
                            ||
                            disease.equals("Corn Leaf Blight")) {

                severity = "HIGH";
            }
        }


        String newRecord =
                "🌱 Plant: " + plant +
                        "\n🦠 Disease: " + disease +
                        "\n⚠️ Severity: " + severity;


        String updatedHistory;


        if (oldHistory.isEmpty()) {

            updatedHistory =
                    newRecord;

        } else {

            updatedHistory =
                    newRecord +
                            "###" +
                            oldHistory;
        }


        preferences.edit()
                .putString(
                        "history",
                        updatedHistory
                )
                .apply();
    }

    private String getSeverityForVoice() {

        if (disease == null) {
            return "unknown";
        }


        if (disease.equals("Healthy Plant")) {

            return "low";
        }


        if (disease.equals("Late Blight")
                || disease.equals("Rice Blast")
                || disease.equals("Corn Leaf Blight")) {

            return "high";
        }


        return "medium";
    }
}