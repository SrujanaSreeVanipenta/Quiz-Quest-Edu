package com.example.quizquest;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizInstructionsDialog extends Dialog {

    private String topic;

    public QuizInstructionsDialog(Context context, String topic) {
        super(context);
        this.topic = topic;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_quiz_instructions);

        TextView tvInstructions = findViewById(R.id.tvInstructions);
        Button btnStart = findViewById(R.id.btnStartQuiz);
        Button btnCancel = findViewById(R.id.btnCancel);

        // Customize instructions per topic
        String instructionsText = "You are about to start the " + topic + " quiz.\n"
                + "Read carefully and select the correct option for each question.";
        tvInstructions.setText(instructionsText);

        btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), QuizActivity.class);
            intent.putExtra("topic", topic);
            getContext().startActivity(intent);
            dismiss();
        });

        btnCancel.setOnClickListener(v -> dismiss());
    }
}
