package com.example.quizquest;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class FirestoreCheckActivity extends AppCompatActivity {

    TextView status;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        status = new TextView(this);
        status.setText("Testing Firestore...");
        status.setTextSize(20);
        setContentView(status);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> testDoc = new HashMap<>();
        testDoc.put("test", true);
        testDoc.put("time", System.currentTimeMillis());

        db.collection("TEST_WRITE")
                .add(testDoc)
                .addOnSuccessListener(r -> {
                    status.setText("SUCCESS! ID: " + r.getId());
                    Log.d("TEST_FIRE", "Write Success: " + r.getId());
                })
                .addOnFailureListener(e -> {
                    status.setText("ERROR: " + e.getMessage());
                    Log.e("TEST_FIRE", "Write FAILED", e);
                });
    }
}
