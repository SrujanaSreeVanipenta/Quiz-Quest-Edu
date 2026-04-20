package com.example.quizquest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ProgrammingCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programming_category);

        Button btnC = findViewById(R.id.btnC);
        Button btnCpp = findViewById(R.id.btnCpp);
        Button btnJava = findViewById(R.id.btnJava);
        Button btnPython = findViewById(R.id.btnPython);

        btnC.setOnClickListener(v -> openQuiz("C"));
        btnCpp.setOnClickListener(v -> openQuiz("C++"));
        btnJava.setOnClickListener(v -> openQuiz("Java"));
        btnPython.setOnClickListener(v -> openQuiz("Python"));
    }

    private void openQuiz(String category) {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("category", category); // <-- IMPORTANT: key is exactly "category"
        startActivity(intent);
    }
}
