package com.example.quizquest;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * Helper to write a quiz attempt to:
 * 1) top-level "quizAttempts"
 * 2) users/{uid}/quizAttempts
 */
public class QuizAttemptsWriter {
    private static final String TAG = "QuizAttemptsWriter";

    public static void saveAttempt(Context ctx,
                                   String uid,
                                   String userName,   // ⭐ Added
                                   String topic,
                                   int score,
                                   int totalQuestions,
                                   int correct) {

        if (uid == null || uid.isEmpty()) {
            Log.w(TAG, "saveAttempt: uid null/empty — not saving");
            Toast.makeText(ctx, "Not signed in — attempt not saved", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> attempt = new HashMap<>();
        attempt.put("userId", uid);
        attempt.put("userName", userName != null ? userName : "Unknown User");  // ⭐ Saves name
        attempt.put("topic", topic != null ? topic : "Unknown");
        attempt.put("score", score);
        attempt.put("totalQuestions", totalQuestions);
        attempt.put("correct", correct);
        attempt.put("timestamp", Timestamp.now());

        // 1) Top-level
        db.collection("quizAttempts")
                .add(attempt)
                .addOnSuccessListener(docRef -> Log.d(TAG, "Top-level saved: " + docRef.getId()))
                .addOnFailureListener(e -> Log.w(TAG, "Failed: " + e.getMessage(), e));

        // 2) User subcollection
        db.collection("users")
                .document(uid)
                .collection("quizAttempts")
                .add(attempt)
                .addOnSuccessListener(docRef -> Log.d(TAG, "User subcollection saved: " + docRef.getId()))
                .addOnFailureListener(e -> Log.w(TAG, "Failed (user attempts): " + e.getMessage(), e));
    }
}
