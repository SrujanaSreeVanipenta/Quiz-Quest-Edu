package com.example.quizquest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private TextView tvGreeting, tvWeeklyCount, tvMonthlyCount, tvAccuracy;
    private LinearLayout layoutRecentAttempts;
    private ProgressBar pbLoading;
    private ImageView btnOpenProfileIcon;
    private Button btnGoToProfile, btnDashboardBack;

    private LinearLayout cardWeeklyStats, cardMonthlyStats;

    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Toolbar with back arrow
        MaterialToolbar toolbar = findViewById(R.id.toolbarDashboard);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        // Views
        tvGreeting = findViewById(R.id.tvGreeting);
        tvWeeklyCount = findViewById(R.id.tvWeeklyCount);
        tvMonthlyCount = findViewById(R.id.tvMonthlyCount);
        tvAccuracy = findViewById(R.id.tvAccuracy);
        layoutRecentAttempts = findViewById(R.id.layoutRecentAttempts);
        pbLoading = findViewById(R.id.pbLoading);
        btnOpenProfileIcon = findViewById(R.id.btnOpenProfile);
        btnGoToProfile = findViewById(R.id.btnGoToProfile);
        btnDashboardBack = findViewById(R.id.btnDashboardBack);

        cardWeeklyStats = findViewById(R.id.cardWeeklyStats);
        cardMonthlyStats = findViewById(R.id.cardMonthlyStats);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Profile navigation
        btnOpenProfileIcon.setOnClickListener(v ->
                startActivity(new Intent(DashboardActivity.this, ProfileActivity.class)));

        btnGoToProfile.setOnClickListener(v ->
                startActivity(new Intent(DashboardActivity.this, ProfileActivity.class)));

        // Back button
        btnDashboardBack.setOnClickListener(v -> finish());

        // Weekly / monthly click → open AttemptsListActivity
        cardWeeklyStats.setOnClickListener(v -> openAttemptsList("weekly"));
        cardMonthlyStats.setOnClickListener(v -> openAttemptsList("monthly"));

        loadUserName();
        loadStatsAndRecent();
    }

    private void openAttemptsList(String mode) {
        Intent intent = new Intent(DashboardActivity.this, AttemptsListActivity.class);
        intent.putExtra("mode", mode); // "weekly" or "monthly"
        startActivity(intent);
    }

    private void loadUserName() {
        if (auth.getCurrentUser() == null) {
            tvGreeting.setText("Hello!");
            return;
        }

        String uid = auth.getCurrentUser().getUid();

        db.collection("users").document(uid)
                .get()
                .addOnSuccessListener(doc -> {
                    if (doc.exists()) {
                        String name = doc.getString("name");
                        if (name != null && !name.isEmpty()) {
                            tvGreeting.setText("Hello, " + name + "!");
                        } else {
                            tvGreeting.setText("Hello!");
                        }
                    } else {
                        tvGreeting.setText("Hello!");
                    }
                })
                .addOnFailureListener(e -> tvGreeting.setText("Hello!"));
    }

    private void loadStatsAndRecent() {
        if (auth.getCurrentUser() == null) {
            tvWeeklyCount.setText("0 quizzes");
            tvMonthlyCount.setText("0 quizzes");
            tvAccuracy.setText("N/A");
            return;
        }

        String uid = auth.getCurrentUser().getUid();
        pbLoading.setVisibility(View.VISIBLE);
        layoutRecentAttempts.removeAllViews();

        long nowMillis = System.currentTimeMillis();
        long last7Millis = nowMillis - 7L * 24 * 60 * 60 * 1000;
        long last30Millis = nowMillis - 30L * 24 * 60 * 60 * 1000;

        db.collection("quizAttempts")
                .whereEqualTo("userId", uid)
                .get()
                .addOnSuccessListener(snaps -> {

                    List<DocumentSnapshot> docs = new ArrayList<>(snaps.getDocuments());

                    // Sort on client by timestamp DESC (latest first)
                    Collections.sort(docs, (d1, d2) -> {
                        long t1 = getTimestampMillis(d1.get("timestamp"));
                        long t2 = getTimestampMillis(d2.get("timestamp"));
                        return Long.compare(t2, t1);
                    });

                    int weekly = 0;
                    int monthly = 0;
                    int correctSum = 0;
                    int totalQSum = 0;

                    boolean hasAttempts = false;

                    for (DocumentSnapshot d : docs) {

                        long tsMillis = getTimestampMillis(d.get("timestamp"));

                        // stats: weekly and monthly counts
                        if (tsMillis >= last30Millis) {
                            monthly++;
                        }
                        if (tsMillis >= last7Millis) {
                            weekly++;
                        }

                        Long correct = d.getLong("correct");
                        Long totalQ = d.getLong("totalQuestions");
                        if (correct != null && totalQ != null) {
                            correctSum += correct;
                            totalQSum += totalQ;
                        }

                        // Show all recent attempts in list
                        addRecentAttemptView(d, tsMillis);
                        hasAttempts = true;
                    }

                    if (!hasAttempts) {
                        TextView empty = new TextView(this);
                        empty.setText("No recent attempts");
                        empty.setPadding(12, 16, 12, 16);
                        layoutRecentAttempts.addView(empty);
                    }

                    tvWeeklyCount.setText(weekly + " quizzes");
                    tvMonthlyCount.setText(monthly + " quizzes");

                    if (totalQSum > 0) {
                        int acc = Math.round((correctSum * 100f) / totalQSum);
                        tvAccuracy.setText(acc + "%");
                    } else {
                        tvAccuracy.setText("N/A");
                    }

                    pbLoading.setVisibility(View.GONE);
                })
                .addOnFailureListener(e -> {
                    pbLoading.setVisibility(View.GONE);

                    tvWeeklyCount.setText("0 quizzes");
                    tvMonthlyCount.setText("0 quizzes");
                    tvAccuracy.setText("N/A");

                    TextView error = new TextView(this);
                    error.setText("Error loading attempts");
                    error.setPadding(12, 16, 12, 16);
                    layoutRecentAttempts.addView(error);
                });
    }

    // Helper: convert Firestore timestamp (Timestamp/Date/Long) to millis
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

    // Helper: add one recent attempt row to layout
    private void addRecentAttemptView(DocumentSnapshot d, long tsMillis) {
        String topic = d.getString("topic");
        Long score = d.getLong("score");
        Long totalQ = d.getLong("totalQuestions");

        String dateStr = "";
        if (tsMillis > 0) {
            dateStr = android.text.format.DateFormat
                    .format("dd MMM yyyy, hh:mm a", new Date(tsMillis))
                    .toString();
        }

        TextView entry = new TextView(this);
        entry.setPadding(12, 16, 12, 16);

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

        entry.setText(s.toString());
        layoutRecentAttempts.addView(entry);
    }
}