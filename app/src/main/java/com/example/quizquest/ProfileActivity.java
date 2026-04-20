package com.example.quizquest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvName, tvEmail, tvGender, tvDOB;
    private Button btnViewDashboard, btnProfileBack;
    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // toolbar
        MaterialToolbar toolbar = findViewById(R.id.toolbarProfile);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        // views
        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvGender = findViewById(R.id.tvGender);
        tvDOB = findViewById(R.id.tvDOB);

        btnViewDashboard = findViewById(R.id.btnViewDashboard);
        btnProfileBack = findViewById(R.id.btnProfileBack);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        loadUserProfile();

        btnViewDashboard.setOnClickListener(v -> startActivity(new Intent(ProfileActivity.this, DashboardActivity.class)));
        btnProfileBack.setOnClickListener(v -> finish());
    }

    private void loadUserProfile() {
        if (auth.getCurrentUser() == null) {
            tvName.setText("No user");
            return;
        }

        String userId = auth.getCurrentUser().getUid();
        db.collection("users").document(userId).get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        tvName.setText(" " + documentSnapshot.getString("name"));
                        tvEmail.setText("  " + documentSnapshot.getString("email"));
                        tvGender.setText(" " + documentSnapshot.getString("gender"));
                        tvDOB.setText("  " + documentSnapshot.getString("dob"));
                    }
                })
                .addOnFailureListener(e -> {
                    tvName.setText("Error loading profile");
                });
    }
}
