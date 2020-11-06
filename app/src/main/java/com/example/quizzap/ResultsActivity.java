package com.example.quizzap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    public static String qContext;
    TextView finalScore, totalQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        finalScore = (TextView)findViewById(R.id.score);
        totalQuestions = (TextView)findViewById(R.id.total_questions);

        Bundle bundle = getIntent().getExtras();
        int score = bundle.getInt("finalScore");
        int totalQ = bundle.getInt("totalQuestions");

        finalScore.setText("" + score);
        totalQuestions.setText("" + totalQ);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                openDialog();
            }
        }, 3000);
    }

    public void openDialog() {
        Dialog dialog = new Dialog();
        dialog.show(getSupportFragmentManager(), "Quizzap!");
    }
}
