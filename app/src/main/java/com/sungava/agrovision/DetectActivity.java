package com.sungava.agrovision;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;

public class DetectActivity extends AppCompatActivity {

    private ImageView imgPlant;

    private LinearLayout cardCamera;
    private LinearLayout cardGallery;

    private Spinner spinnerPlant;

    private Button btnCheckDisease;

    private Uri selectedImageUri;

    // GALLERY

    private final ActivityResultLauncher<String> galleryLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.GetContent(),
                    uri -> {

                        if (uri != null) {

                            selectedImageUri = uri;

                            imgPlant.setImageURI(uri);
                        }
                    }
            );

    // CAMERA


    private final ActivityResultLauncher<Void> cameraLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.TakePicturePreview(),
                    bitmap -> {

                        if (bitmap != null) {

                            imgPlant.setImageBitmap(bitmap);

                            selectedImageUri =
                                    saveBitmapToCache(bitmap);
                        }
                    }
            );


    // CAMERA PERMISSION


    private final ActivityResultLauncher<String>
            cameraPermissionLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.RequestPermission(),
                    isGranted -> {

                        if (isGranted) {

                            cameraLauncher.launch(null);

                        } else {

                            Toast.makeText(
                                    this,
                                    "Camera permission is required.",
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
                R.layout.activity_detect
        );



        // FIND VIEWS


        imgPlant =
                findViewById(
                        R.id.imgPlant
                );

        cardCamera =
                findViewById(
                        R.id.cardCamera
                );

        cardGallery =
                findViewById(
                        R.id.cardGallery
                );

        spinnerPlant =
                findViewById(
                        R.id.spinnerPlant
                );

        btnCheckDisease =
                findViewById(
                        R.id.btnCheckDisease
                );



        // PLANT LIST


        String[] plants = {

                "Select Plant",
                "Tomato",
                "Potato",
                "Rice",
                "Wheat",
                "Corn"
        };


        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        plants
                );


        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );


        spinnerPlant.setAdapter(
                adapter
        );



        // CAMERA CARD


        cardCamera.setOnClickListener(v -> {

            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED) {

                cameraLauncher.launch(null);

            } else {

                cameraPermissionLauncher.launch(
                        Manifest.permission.CAMERA
                );
            }
        });



        // GALLERY CARD


        cardGallery.setOnClickListener(v -> {

            galleryLauncher.launch(
                    "image/*"
            );
        });



        // CHECK DISEASE


        btnCheckDisease.setOnClickListener(v -> {

            String selectedPlant =
                    spinnerPlant
                            .getSelectedItem()
                            .toString();


            // Check plant

            if (selectedPlant.equals(
                    "Select Plant"
            )) {

                Toast.makeText(
                        this,
                        "Please select a plant.",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }


            // Check image

            if (selectedImageUri == null) {

                Toast.makeText(
                        this,
                        "Please take a photo or select an image.",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }



            // OPEN DISEASE ACTIVITY


            Intent intent =
                    new Intent(
                            DetectActivity.this,
                            DiseaseActivity.class
                    );


            intent.putExtra(
                    "plant",
                    selectedPlant
            );


            intent.putExtra(
                    "imageUri",
                    selectedImageUri.toString()
            );


            startActivity(intent);
        });
    }

    // SAVE CAMERA IMAGE

    private Uri saveBitmapToCache(
            Bitmap bitmap
    ) {

        try {

            File file =
                    new File(
                            getCacheDir(),
                            "plant_photo.jpg"
                    );


            FileOutputStream outputStream =
                    new FileOutputStream(file);


            bitmap.compress(
                    Bitmap.CompressFormat.JPEG,
                    90,
                    outputStream
            );


            outputStream.flush();

            outputStream.close();


            return Uri.fromFile(file);


        } catch (Exception e) {

            e.printStackTrace();

            Toast.makeText(
                    this,
                    "Could not save camera image.",
                    Toast.LENGTH_SHORT
            ).show();

            return null;
        }
    }
}