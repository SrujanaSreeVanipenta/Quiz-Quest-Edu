package com.example.quizquest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ProgrammingLanguagesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programming_languages);

        Button btnC = findViewById(R.id.btnC);
        Button btnCpp = findViewById(R.id.btnCpp);
        Button btnJava = findViewById(R.id.btnJava);
        Button btnPython = findViewById(R.id.btnPython);

        btnC.setOnClickListener(v -> openInstructions("C"));
        btnCpp.setOnClickListener(v -> openInstructions("C++"));
        btnJava.setOnClickListener(v -> openInstructions("Java"));
        btnPython.setOnClickListener(v -> openInstructions("Python"));
    }

    private void openInstructions(String language) {
        Intent intent = new Intent(this, QuizInstructionsActivity.class);
        intent.putExtra("category", language);
        startActivity(intent);
    }
}
