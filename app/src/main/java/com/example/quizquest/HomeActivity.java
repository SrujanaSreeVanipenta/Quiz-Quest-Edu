package com.example.quizquest;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private Button btnLogout, btnProfile;
    private GridLayout gridCategories;

    private FirebaseAuth auth;
    private FirebaseFirestore db;

    // Order matches the cards in activity_home.xml (top-left to bottom-right)
    private final String[] topics = new String[]{
            "Programming",        // cardProgramming
            "DBMS",               // cardDBMS
            "Operating Systems",  // cardOS
            "Web Development",    // cardWeb
            "Important Topics",   // cardImportant
            "General Knowledge",  // cardGK
            "Health Care",        // cardHealth
            "Aptitude" ,
            // cardAptitude
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvWelcome = findViewById(R.id.tvWelcome);
        btnLogout = findViewById(R.id.btnLogout);
        btnProfile = findViewById(R.id.btnProfile);
        gridCategories = findViewById(R.id.gridCategories);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        loadUserName();

        btnLogout.setOnClickListener(v -> {
            auth.signOut();
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            finish();
        });

        btnProfile.setOnClickListener(v ->
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class))
        );

        // Set click listeners for each category card
        int childCount = gridCategories.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View card = gridCategories.getChildAt(i);
            int index = i;

            card.setOnClickListener(v -> {
                if (index < 0 || index >= topics.length) return;
                String topic = topics[index];
                showDifficultyDialog(topic);
            });
        }
    }

    private void loadUserName() {
        if (auth.getCurrentUser() == null) {
            tvWelcome.setText("Hello, User!");
            return;
        }

        String userId = auth.getCurrentUser().getUid();

        db.collection("users").document(userId).get()
                .addOnSuccessListener(snapshot -> {
                    if (snapshot.exists()) {
                        String name = snapshot.getString("name");
                        if (name != null && !name.isEmpty()) {
                            tvWelcome.setText("Hello, " + name + "!");
                        } else {
                            tvWelcome.setText("Hello, User!");
                        }
                    } else {
                        tvWelcome.setText("Hello, User!");
                    }
                })
                .addOnFailureListener(e -> tvWelcome.setText("Hello, User!"));
    }

    // 🔹 Show difficulty selection (Easy / Medium / Hard) before starting quiz
    private void showDifficultyDialog(String topic) {
        String[] levels = {"Easy", "Medium", "Hard"};

        new AlertDialog.Builder(this)
                .setTitle(topic + " - Choose Difficulty")
                .setItems(levels, (dialog, which) -> {
                    String difficulty = levels[which];  // "Easy", "Medium", or "Hard"
                    startQuiz(topic, difficulty);
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void startQuiz(String topic, String difficulty) {
        Intent intent = new Intent(HomeActivity.this, QuizActivity.class);
        intent.putExtra("topic", topic);
        intent.putExtra("difficulty", difficulty);   // pass chosen difficulty to QuizActivity
        startActivity(intent);
    }
}
