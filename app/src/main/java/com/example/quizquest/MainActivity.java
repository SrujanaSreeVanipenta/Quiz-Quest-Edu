package com.example.quizquest;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Redirect to HomeActivity
        startActivity(new Intent(MainActivity.this, HomeActivity.class));
        finish();
    }
}
