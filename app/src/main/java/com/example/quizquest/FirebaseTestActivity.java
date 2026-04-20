package com.example.quizquest;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.Timestamp;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FirebaseTestActivity extends AppCompatActivity {

    private static final String TAG = "FirebaseTest";
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Simple layout
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 100, 50, 100);

        Button btnWriteTest = new Button(this);
        btnWriteTest.setText("Write Test quizAttempts Document");
        layout.addView(btnWriteTest);

        setContentView(layout);

        // Initialize Firebase
        try {
            FirebaseApp.initializeApp(this);
        } catch (Exception ignored) {}

        mAuth = FirebaseAuth.getInstance();

        try {
            db = FirebaseFirestore.getInstance();
        } catch (Exception e) {
            Toast.makeText(this, "❌ Firestore init failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
            return;
        }

        // Debug info
        try {
            FirebaseOptions opts = FirebaseApp.getInstance().getOptions();
            Log.d(TAG, "Firebase App: " + opts.getApplicationId() + " / " + opts.getProjectId());
        } catch (Exception e) {
            Log.w(TAG, "Could not fetch Firebase options: " + e.getMessage());
        }

        btnWriteTest.setOnClickListener(v -> writeTestDocument());
    }

    private void writeTestDocument() {

        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            Toast.makeText(this, "⚠ Please Login First", Toast.LENGTH_LONG).show();
            Log.w(TAG, "No signed-in user");
            return;
        }

        String uid = user.getUid();

        Map<String, Object> attempt = new HashMap<>();
        attempt.put("userId", uid);
        attempt.put("topic", "Programming");
        attempt.put("score", 7);
        attempt.put("totalQuestions", 10);
        attempt.put("correct", 7);
        attempt.put("timestamp", Timestamp.now());

        db.collection("quizAttempts")
                .add(attempt)
                .addOnSuccessListener(ref -> {
                    String id = ref.getId();
                    Log.d(TAG, "Added quizAttempts id=" + id);
                    Toast.makeText(this, "✅ Added quizAttempts\nID: " + id, Toast.LENGTH_LONG).show();
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Write failed: ", e);
                    Toast.makeText(this, "❌ Write failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
    }
}
