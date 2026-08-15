package com.sungava.agrovision;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HistoryActivity extends AppCompatActivity {

    private LinearLayout historyContainer;
    private TextView txtNoHistory;

    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_history);


        historyContainer =
                findViewById(R.id.historyContainer);

        txtNoHistory =
                findViewById(R.id.txtNoHistory);


        preferences =
                getSharedPreferences(
                        "PlantDoctorHistory",
                        MODE_PRIVATE
                );


        loadHistory();
    }


    private void loadHistory() {

        String history =
                preferences.getString(
                        "history",
                        ""
                );


        if (history.isEmpty()) {

            txtNoHistory.setVisibility(
                    TextView.VISIBLE
            );

            return;
        }


        txtNoHistory.setVisibility(
                TextView.GONE
        );


        String[] records =
                history.split("###");


        for (String record : records) {

            if (record.trim().isEmpty()) {
                continue;
            }


            TextView historyItem =
                    new TextView(this);


            historyItem.setText(
                    record
            );


            historyItem.setTextSize(
                    17
            );


            historyItem.setTextColor(
                    getColor(
                            android.R.color.black
                    )
            );


            historyItem.setBackgroundColor(
                    0xFFFFFFFF
            );


            historyItem.setPadding(
                    20,
                    20,
                    20,
                    20
            );


            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );


            params.setMargins(
                    0,
                    15,
                    0,
                    0
            );


            historyContainer.addView(
                    historyItem,
                    params
            );
        }
    }
}