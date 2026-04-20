package com.example.quizquest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class AttemptsListActivity extends AppCompatActivity {

    private TextView tvAttemptsTitle;
    private LinearLayout layoutAttemptsList;
    private ProgressBar pbAttemptsLoading;

    private FirebaseAuth auth;
    private FirebaseFirestore db;

    private String mode = "weekly"; // or "monthly"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attempts_list);

        // Toolbar
        MaterialToolbar toolbar = findViewById(R.id.toolbarAttemptsList);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        // Views
        tvAttemptsTitle = findViewById(R.id.tvAttemptsTitle);
        layoutAttemptsList = findViewById(R.id.layoutAttemptsList);
        pbAttemptsLoading = findViewById(R.id.pbAttemptsLoading);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Get mode: "weekly" or "monthly"
        String incomingMode = getIntent().getStringExtra("mode");
        if (incomingMode != null) {
            mode = incomingMode;
        }

        if ("monthly".equalsIgnoreCase(mode)) {
            tvAttemptsTitle.setText("Monthly Attempts");
        } else {
            tvAttemptsTitle.setText("Weekly Attempts");
        }

        loadAttempts();
    }

    private void loadAttempts() {
        if (auth.getCurrentUser() == null) {
            showEmpty("Not logged in");
            return;
        }

        String uid = auth.getCurrentUser().getUid();
        pbAttemptsLoading.setVisibility(View.VISIBLE);
        layoutAttemptsList.removeAllViews();

        long nowMillis = System.currentTimeMillis();
        long rangeStartMillis;

        if ("monthly".equalsIgnoreCase(mode)) {
            rangeStartMillis = nowMillis - 30L * 24 * 60 * 60 * 1000;
        } else {
            rangeStartMillis = nowMillis - 7L * 24 * 60 * 60 * 1000;
        }

        db.collection("quizAttempts")
                .whereEqualTo("userId", uid)
                .get()
                .addOnSuccessListener(snaps -> {
                    List<DocumentSnapshot> docs = new ArrayList<>(snaps.getDocuments());

                    // Filter by time range
                    List<DocumentSnapshot> filtered = new ArrayList<>();
                    for (DocumentSnapshot d : docs) {
                        long ts = getTimestampMillis(d.get("timestamp"));
                        if (ts >= rangeStartMillis) {
                            filtered.add(d);
                        }
                    }

                    // Sort by timestamp DESC
                    Collections.sort(filtered, (d1, d2) -> {
                        long t1 = getTimestampMillis(d1.get("timestamp"));
                        long t2 = getTimestampMillis(d2.get("timestamp"));
                        return Long.compare(t2, t1);
                    });

                    if (filtered.isEmpty()) {
                        showEmpty("No attempts in this period");
                    } else {
                        for (DocumentSnapshot d : filtered) {
                            addAttemptRow(d);
                        }
                    }

                    pbAttemptsLoading.setVisibility(View.GONE);
                })
                .addOnFailureListener(e -> {
                    pbAttemptsLoading.setVisibility(View.GONE);
                    showEmpty("Error loading attempts");
                });
    }

    private void showEmpty(String message) {
        layoutAttemptsList.removeAllViews();
        TextView tv = new TextView(this);
        tv.setText(message);
        tv.setPadding(16, 24, 16, 24);
        layoutAttemptsList.addView(tv);
    }

    private long getTimestampMillis(Object tObj) {
        if (tObj == null) return 0L;
        if (tObj instanceof Timestamp) {
            return ((Timestamp) tObj).toDate().getTime();
        } else if (tObj instanceof Date) {
            return ((Date) tObj).getTime();
        } else if (tObj instanceof Long) {
            return (Long) tObj;
        }
        return 0L;
    }

    private void addAttemptRow(DocumentSnapshot d) {
        String attemptId = d.getId();
        String topic = d.getString("topic");
        Long score = d.getLong("score");
        Long totalQ = d.getLong("totalQuestions");

        long tsMillis = getTimestampMillis(d.get("timestamp"));
        String dateStr = "";
        if (tsMillis > 0) {
            dateStr = android.text.format.DateFormat
                    .format("dd MMM yyyy, hh:mm a", new Date(tsMillis))
                    .toString();
        }

        // Row layout (horizontal: text + button)
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setPadding(12, 16, 12, 16);
        row.setWeightSum(10);

        TextView tvInfo = new TextView(this);
        tvInfo.setLayoutParams(new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                7f
        ));

        StringBuilder s = new StringBuilder();
        if (topic != null) s.append(topic);
        if (score != null && totalQ != null) {
            if (s.length() > 0) s.append(" — ");
            s.append(score).append("/").append(totalQ);
        }
        if (!dateStr.isEmpty()) {
            if (s.length() > 0) s.append(" • ");
            s.append(dateStr);
        }
        if (s.length() == 0) s.append("Attempt");

        tvInfo.setText(s.toString());

        Button btnView = new Button(this);
        btnView.setText("View Test");
        btnView.setLayoutParams(new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                3f
        ));

        btnView.setOnClickListener(v -> {
            Intent intent = new Intent(AttemptsListActivity.this, AttemptDetailActivity.class);
            intent.putExtra("attemptId", attemptId);
            startActivity(intent);
        });

        row.addView(tvInfo);
        row.addView(btnView);

        layoutAttemptsList.addView(row);
    }
}
