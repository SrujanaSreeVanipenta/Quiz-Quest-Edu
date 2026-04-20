package com.example.quizquest;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizActivity extends AppCompatActivity {

    private TextView tvQuestionNumber, tvTimer, tvQuestion;
    private RadioGroup optionsGroup;
    private RadioButton rbOption1, rbOption2, rbOption3, rbOption4;
    private Button btnNext;
    private FirebaseFirestore db;
    private FirebaseAuth auth;

    private List<DocumentSnapshot> questionList = new ArrayList<>();
    private int currentIndex = 0;
    private int score = 0;                 // number of correct answers
    private CountDownTimer timer;
    private long timeLeftInMillis;
    private String selectedAnswer = "";

    // Topic & difficulty chosen by the user
    private String topic = "Programming";
    private String chosenDifficulty = "Easy";  // default if nothing passed

    // NEW: store user answers in order of questions
    private List<String> userAnswers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Initialize UI
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvTimer = findViewById(R.id.tvTimer);
        tvQuestion = findViewById(R.id.tvQuestion);
        optionsGroup = findViewById(R.id.optionsGroup);
        rbOption1 = findViewById(R.id.rbOption1);
        rbOption2 = findViewById(R.id.rbOption2);
        rbOption3 = findViewById(R.id.rbOption3);
        rbOption4 = findViewById(R.id.rbOption4);
        btnNext = findViewById(R.id.btnNext);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        // Get topic from Intent and store in field
        String incomingTopic = getIntent().getStringExtra("topic");
        if (incomingTopic != null && !incomingTopic.isEmpty()) {
            topic = incomingTopic;
        }

        // Get chosen difficulty from HomeActivity (Easy / Medium / Hard)
        String incomingDifficulty = getIntent().getStringExtra("difficulty");
        if (incomingDifficulty != null && !incomingDifficulty.isEmpty()) {
            chosenDifficulty = incomingDifficulty;
        }

        // Load questions based on topic + chosen difficulty
        loadQuestions(topic, chosenDifficulty);

        btnNext.setOnClickListener(v -> handleNext());
    }

    private void loadQuestions(String topic, String difficulty) {
        // Base query filtered by topic
        Query query = db.collection("quizQuestions")
                .whereEqualTo("topic", topic);

        // Also filter by difficulty if provided
        if (difficulty != null && !difficulty.isEmpty()) {
            query = query.whereEqualTo("difficulty", difficulty);
        }

        query.get()
                .addOnSuccessListener(this::onQuestionsLoaded)
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Error loading questions", Toast.LENGTH_SHORT).show());
    }

    private void onQuestionsLoaded(QuerySnapshot snapshot) {
        if (snapshot == null || snapshot.isEmpty()) {
            Toast.makeText(this,
                    "No questions found for " + topic + " (" + chosenDifficulty + ")",
                    Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        questionList = new ArrayList<>(snapshot.getDocuments());
        Collections.shuffle(questionList);

        // Pick only 5 random unique questions (change if you want more)
        if (questionList.size() > 5) {
            questionList = questionList.subList(0, 5);
        }

        currentIndex = 0;
        score = 0;
        userAnswers.clear();
        showQuestion();
    }

    private void showQuestion() {
        if (currentIndex >= questionList.size()) {
            finishQuiz();
            return;
        }

        DocumentSnapshot q = questionList.get(currentIndex);

        tvQuestionNumber.setText("Question " + (currentIndex + 1) + " of " + questionList.size());
        tvQuestion.setText(q.getString("question"));

        rbOption1.setText(q.getString("option1"));
        rbOption2.setText(q.getString("option2"));
        rbOption3.setText(q.getString("option3"));
        rbOption4.setText(q.getString("option4"));

        optionsGroup.clearCheck();
        selectedAnswer = "";

        // Handle timer based on difficulty stored in question document
        String difficulty = q.getString("difficulty");
        if (difficulty == null) difficulty = "Easy";

        switch (difficulty) {
            case "Medium":
                timeLeftInMillis = 15000; // 15 sec
                break;
            case "Hard":
                timeLeftInMillis = 20000; // 20 sec
                break;
            default:
                timeLeftInMillis = 10000; // Easy: 10 sec
        }

        startTimer();

        if (currentIndex == questionList.size() - 1)
            btnNext.setText("Submit");
        else
            btnNext.setText("Next");
    }

    private void handleNext() {
        int selectedId = optionsGroup.getCheckedRadioButtonId();

        if (selectedId == -1) {
            Toast.makeText(this, "Please select an answer!", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selected = findViewById(selectedId);
        selectedAnswer = selected.getText().toString();

        // Save user's chosen answer for this question
        userAnswers.add(selectedAnswer);

        // Check if it's correct
        checkAnswer();

        currentIndex++;
        showQuestion();
    }

    private void checkAnswer() {
        if (timer != null) timer.cancel();

        DocumentSnapshot q = questionList.get(currentIndex);
        String correctAnswerKey = q.getString("answer"); // "option1"/"option2"/"option3"/"option4"

        if (correctAnswerKey == null) return;

        String correctAnswer = q.getString(correctAnswerKey);
        if (correctAnswer == null) return;

        if (selectedAnswer.trim().equalsIgnoreCase(correctAnswer.trim())) {
            score++; // number of correct answers
        }
    }

    private void startTimer() {
        if (timer != null) timer.cancel();

        timer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                tvTimer.setText("Time: " + millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                Toast.makeText(QuizActivity.this, "⏰ Time's up!", Toast.LENGTH_SHORT).show();
                // No answer selected → will be recorded as "Not answered" later
                currentIndex++;
                showQuestion();
            }
        }.start();
    }

    private void finishQuiz() {
        if (timer != null) timer.cancel();

        // 🔹 Save attempt with detailed Q&A to Firestore
        saveAttemptToFirestore();

        // Move to ScoreActivity as usual
        Intent intent = new Intent(QuizActivity.this, ScoreActivity.class);
        intent.putExtra("score", score);
        intent.putExtra("total", questionList.size());
        intent.putExtra("correct", score);
        intent.putExtra("topic", topic);
        intent.putExtra("difficulty", chosenDifficulty);
        startActivity(intent);
        finish();
    }

    private void saveAttemptToFirestore() {
        if (auth.getCurrentUser() == null) return;

        String uid = auth.getCurrentUser().getUid();
        String userEmail = auth.getCurrentUser().getEmail();

        Map<String, Object> attempt = new HashMap<>();
        attempt.put("userId", uid);
        attempt.put("userEmail", userEmail);
        attempt.put("topic", topic);
        attempt.put("difficulty", chosenDifficulty);
        attempt.put("score", score);
        attempt.put("totalQuestions", questionList.size());
        attempt.put("correct", score);
        attempt.put("timestamp", new Date());

        // Build detailed question list
        List<Map<String, Object>> questionsDetails = new ArrayList<>();

        for (int i = 0; i < questionList.size(); i++) {
            DocumentSnapshot qDoc = questionList.get(i);

            String questionText = qDoc.getString("question");
            String answerKey = qDoc.getString("answer");   // e.g., "option2"
            String correctAnswerText = null;
            if (answerKey != null) {
                correctAnswerText = qDoc.getString(answerKey);
            }

            String userAnswerText;
            if (i < userAnswers.size()) {
                userAnswerText = userAnswers.get(i);
            } else {
                userAnswerText = "Not answered";
            }

            boolean isCorrect = false;
            if (userAnswerText != null && correctAnswerText != null) {
                isCorrect = userAnswerText.trim().equalsIgnoreCase(correctAnswerText.trim());
            }

            Map<String, Object> qMap = new HashMap<>();
            qMap.put("question", questionText);
            qMap.put("userAnswerText", userAnswerText);
            qMap.put("correctAnswerText", correctAnswerText);
            qMap.put("isCorrect", isCorrect);

            questionsDetails.add(qMap);
        }

        attempt.put("questions", questionsDetails);

        FirebaseFirestore.getInstance()
                .collection("quizAttempts")
                .add(attempt);
        // (No need to wait; ScoreActivity can open immediately)
    }
}
