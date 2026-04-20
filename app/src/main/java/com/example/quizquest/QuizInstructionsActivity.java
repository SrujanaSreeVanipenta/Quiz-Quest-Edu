package com.example.quizquest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QuizInstructionsActivity extends AppCompatActivity {

    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_instructions);

        TextView txtInstructions = findViewById(R.id.txtInstructions);
        Button btnStartQuiz = findViewById(R.id.btnStartQuiz);

        category = getIntent().getStringExtra("category");

        txtInstructions.setText("You are about to start the quiz on " + category +
                ".\n\nEach question has 4 options.\nYou will get points for correct answers.\nGood luck!");

        btnStartQuiz.setOnClickListener(v -> {
            Intent intent = new Intent(this, QuizActivity.class);
            intent.putExtra("category", category);
            startActivity(intent);
        });
    }
}
