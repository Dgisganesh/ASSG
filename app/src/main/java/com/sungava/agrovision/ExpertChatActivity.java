package com.sungava.agrovision;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ExpertChatActivity extends AppCompatActivity {

    private Spinner spinnerQuestion;

    private Button btnAsk;

    private LinearLayout chatContainer;

    private ScrollView chatScroll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_expert_chat
        );


        spinnerQuestion =
                findViewById(
                        R.id.spinnerQuestion
                );

        btnAsk =
                findViewById(
                        R.id.btnAsk
                );

        chatContainer =
                findViewById(
                        R.id.chatContainer
                );

        chatScroll =
                findViewById(
                        R.id.chatScroll
                );


        String[] questions = {

                "Select a question",

                "Why are my leaves turning yellow?",

                "Why are brown spots appearing?",

                "How often should I water my plant?",

                "How can I prevent plant diseases?",

                "Should I remove infected leaves?",

                "When should I contact an agriculture expert?"

        };


        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        questions
                );


        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );


        spinnerQuestion.setAdapter(
                adapter
        );


        btnAsk.setOnClickListener(v -> {

            int position =
                    spinnerQuestion
                            .getSelectedItemPosition();


            if (position == 0) {
                return;
            }


            String question =
                    spinnerQuestion
                            .getSelectedItem()
                            .toString();


            addUserMessage(question);


            String answer =
                    getExpertAnswer(question);


            addExpertMessage(answer);


            spinnerQuestion.setSelection(0);

        });
    }


    private void addUserMessage(
            String message) {

        TextView textView =
                new TextView(this);


        textView.setText(
                "👤 You: " + message
        );


        textView.setTextSize(
                16
        );


        textView.setTextColor(
                0xFF333333
        );


        textView.setBackgroundColor(
                0xFFE8F5E9
        );


        textView.setPadding(
                18,
                18,
                18,
                18
        );


        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );


        params.setMargins(
                0,
                12,
                0,
                0
        );


        chatContainer.addView(
                textView,
                params
        );


        scrollToBottom();
    }


    private void addExpertMessage(
            String message) {

        TextView textView =
                new TextView(this);


        textView.setText(
                "👨‍🌾 Expert: " + message
        );


        textView.setTextSize(
                16
        );


        textView.setTextColor(
                0xFF333333
        );


        textView.setBackgroundColor(
                0xFFFFFFFF
        );


        textView.setPadding(
                18,
                18,
                18,
                18
        );


        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );


        params.setMargins(
                0,
                12,
                0,
                0
        );


        chatContainer.addView(
                textView,
                params
        );


        scrollToBottom();
    }


    private String getExpertAnswer(
            String question) {


        if (question.equals(
                "Why are my leaves turning yellow?")) {

            return
                    "Yellow leaves can have several causes, " +
                            "including overwatering, underwatering, " +
                            "nutrient problems or natural aging. " +
                            "Check the soil moisture and inspect the plant carefully.";
        }


        if (question.equals(
                "Why are brown spots appearing?")) {

            return
                    "Brown spots may be caused by fungal or bacterial " +
                            "diseases, water stress, sun damage or nutrient problems. " +
                            "Check the spots and surrounding leaves carefully.";
        }


        if (question.equals(
                "How often should I water my plant?")) {

            return
                    "There is no single watering schedule for every plant. " +
                            "Check soil moisture, plant type, temperature and weather. " +
                            "Avoid keeping the soil constantly waterlogged.";
        }


        if (question.equals(
                "How can I prevent plant diseases?")) {

            return
                    "Use healthy planting material, maintain good spacing, " +
                            "provide appropriate water and nutrition, remove severely " +
                            "infected material and keep the growing area clean.";
        }


        if (question.equals(
                "Should I remove infected leaves?")) {

            return
                    "For some diseases, removing severely infected leaves " +
                            "can help reduce disease spread. Dispose of infected " +
                            "material appropriately and avoid spreading plant debris.";
        }


        if (question.equals(
                "When should I contact an agriculture expert?")) {

            return
                    "Contact a local agriculture expert when the disease " +
                            "is spreading quickly, the crop is severely damaged, " +
                            "or you are unsure about the diagnosis or treatment.";
        }


        return
                "Please provide more information about the plant " +
                        "and its symptoms.";
    }


    private void scrollToBottom() {

        chatScroll.post(() -> {

            chatScroll.fullScroll(
                    View.FOCUS_DOWN
            );

        });
    }
}