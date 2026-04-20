package com.example.quizquest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity {

    private TextView tvScore;
    private Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        tvScore = findViewById(R.id.tvScore);
        btnHome = findViewById(R.id.btnHome);

        int score = getIntent().getIntExtra("score", 0);
        int total = getIntent().getIntExtra("total", 0);

        tvScore.setText("Your Score: " + score + " / " + total);

        btnHome.setOnClickListener(v -> {
            startActivity(new Intent(ScoreActivity.this, HomeActivity.class));
            finish();
        });
    }
}