package com.example.quizquest;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;

public class SignupActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPassword, etConfirmPassword, etDOB;
    private Spinner spinnerGender;
    private Button btnSignup;
    private TextView tvLoginLink;

    private FirebaseAuth auth;
    private FirebaseFirestore db;

    private String selectedGender = "";
    private String selectedDOB = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Firebase
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Views
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        etDOB = findViewById(R.id.etDOB);
        spinnerGender = findViewById(R.id.spinnerGender);
        btnSignup = findViewById(R.id.btnSignup);
        tvLoginLink = findViewById(R.id.tvLoginLink);

        // ---------------------------
        // Gender Spinner with gray placeholder
        // ---------------------------
        String[] genderOptions = {"Select Gender", "Male", "Female", "Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, genderOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapter);

        spinnerGender.setSelection(0);

        // safe way to set initial color
        spinnerGender.post(() -> {
            View v = spinnerGender.getSelectedView();
            if (v instanceof TextView) {
                ((TextView) v).setTextColor(Color.GRAY);
            }
        });

        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(view instanceof TextView){
                    TextView tv = (TextView) view;
                    if(position == 0){
                        tv.setTextColor(Color.GRAY);
                        selectedGender = "";
                    } else {
                        tv.setTextColor(Color.BLACK);
                        selectedGender = genderOptions[position];
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // ---------------------------
        // DatePicker for DOB
        // ---------------------------
        etDOB.setFocusable(false);
        etDOB.setClickable(true);
        etDOB.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog dpd = new DatePickerDialog(SignupActivity.this,
                    (view, year, month, dayOfMonth) -> {
                        selectedDOB = dayOfMonth + "/" + (month+1) + "/" + year;
                        etDOB.setText(selectedDOB);
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            dpd.show();
        });

        // ---------------------------
        // Signup Button
        // ---------------------------
        btnSignup.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();

            if(name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()
                    || selectedGender.isEmpty() || selectedDOB.isEmpty()){
                Toast.makeText(SignupActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if(!password.equals(confirmPassword)){
                Toast.makeText(SignupActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            String userId = auth.getCurrentUser().getUid();
                            HashMap<String,Object> userMap = new HashMap<>();
                            userMap.put("name", name);
                            userMap.put("email", email);
                            userMap.put("gender", selectedGender);
                            userMap.put("dob", selectedDOB);

                            db.collection("users").document(userId).set(userMap)
                                    .addOnSuccessListener(aVoid -> {
                                        Toast.makeText(SignupActivity.this,"Signup Successful",Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(SignupActivity.this, HomeActivity.class));
                                        finish();
                                    })
                                    .addOnFailureListener(e -> Toast.makeText(SignupActivity.this,"DB Error: "+e.getMessage(),Toast.LENGTH_SHORT).show());

                        } else {
                            String error = task.getException() != null ? task.getException().getMessage() : "Signup Failed";
                            Toast.makeText(SignupActivity.this,error,Toast.LENGTH_LONG).show();
                        }
                    });
        });

        tvLoginLink.setOnClickListener(v -> startActivity(new Intent(SignupActivity.this, LoginActivity.class)));
    }
}
