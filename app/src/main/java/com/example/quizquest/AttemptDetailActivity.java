package com.example.quizquest;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class AttemptDetailActivity extends AppCompatActivity {

    private TextView tvAttemptHeader;
    private LinearLayout layoutAttemptDetail;
    private ProgressBar pbDetailLoading;

    private FirebaseFirestore db;
    private String attemptId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attempt_detail);

        MaterialToolbar toolbar = findViewById(R.id.toolbarAttemptDetail);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        tvAttemptHeader = findViewById(R.id.tvAttemptHeader);
        layoutAttemptDetail = findViewById(R.id.layoutAttemptDetail);
        pbDetailLoading = findViewById(R.id.pbDetailLoading);

        db = FirebaseFirestore.getInstance();

        attemptId = getIntent().getStringExtra("attemptId");
        if (attemptId == null || attemptId.trim().isEmpty()) {
            tvAttemptHeader.setText("Invalid Attempt");
            showText("Invalid attempt ID.");
            return;
        }

        loadAttemptDetail();
    }

    private void loadAttemptDetail() {
        pbDetailLoading.setVisibility(View.VISIBLE);
        layoutAttemptDetail.removeAllViews();

        db.collection("quizAttempts")
                .document(attemptId)
                .get()
                .addOnSuccessListener(doc -> {
                    pbDetailLoading.setVisibility(View.GONE);

                    if (!doc.exists()) {
                        showText("Attempt not found.");
                        return;
                    }

                    String topic = doc.getString("topic");
                    Long score = doc.getLong("score");
                    Long totalQ = doc.getLong("totalQuestions");
                    Object tsObj = doc.get("timestamp");

                    String dateStr = "";
                    if (tsObj instanceof Timestamp) {
                        dateStr = android.text.format.DateFormat
                                .format("dd MMM yyyy, hh:mm a", ((Timestamp) tsObj).toDate())
                                .toString();
                    } else if (tsObj instanceof Long) {
                        dateStr = android.text.format.DateFormat
                                .format("dd MMM yyyy, hh:mm a", new Date((Long) tsObj))
                                .toString();
                    }

                    String header = "Attempt";
                    if (topic != null) header = topic;
                    if (!dateStr.isEmpty()) header = header + " • " + dateStr;

                    tvAttemptHeader.setText(header);

                    if (score != null && totalQ != null) {
                        showText("Score: " + score + "/" + totalQ);
                    }

                    // Read the questions array from Firestore
                    List<Map<String, Object>> questions =
                            (List<Map<String, Object>>) doc.get("questions");

                    if (questions == null || questions.isEmpty()) {
                        showText("No detailed answers stored for this attempt.");
                        return;
                    }

                    int index = 1;
                    for (Map<String, Object> q : questions) {
                        addQuestionView(index, q);
                        index++;
                    }
                })
                .addOnFailureListener(e -> {
                    pbDetailLoading.setVisibility(View.GONE);
                    showText("Error loading attempt.");
                });
    }

    private void showText(String text) {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setPadding(12, 16, 12, 16);
        layoutAttemptDetail.addView(tv);
    }

    private void addQuestionView(int index, Map<String, Object> q) {
        String question = (String) q.get("question");
        String userAnsText = (String) q.get("userAnswerText");
        String correctAnsText = (String) q.get("correctAnswerText");
        Boolean isCorrect = (Boolean) q.get("isCorrect");

        TextView tv = new TextView(this);
        tv.setPadding(12, 16, 12, 16);

        StringBuilder s = new StringBuilder();
        s.append(index).append(". ");
        if (question != null) s.append(question);
        s.append("\n");

        s.append("Your answer: ");
        if (userAnsText != null) s.append(userAnsText);
        else s.append("-");

        s.append("\nCorrect answer: ");
        if (correctAnsText != null) s.append(correctAnsText);
        else s.append("-");

        if (isCorrect != null) {
            s.append("\nResult: ").append(isCorrect ? "✅ Correct" : "❌ Wrong");
        }

        tv.setText(s.toString());
        layoutAttemptDetail.addView(tv);
    }
}
