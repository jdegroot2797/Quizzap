package com.example.quizzap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class
QuizActivity extends AppCompatActivity {

    private TextView scoreView, question, questionsRemaining;
    private Button trueButton, falseButton;
    private ProgressBar pb;

    private boolean answer;
    private int score, questionNumber, totalQuestions;
    private int pbMultiplier = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionsRemaining = (TextView)findViewById(R.id.questions_left);
        question = (TextView)findViewById(R.id.quiz_question);
        totalQuestions = QuizBank.answers.length;
        trueButton = (Button)findViewById(R.id.true_button);
        falseButton = (Button)findViewById(R.id.false_button);
        pb = (ProgressBar)findViewById(R.id.progressBar);
        pb.setMax(QuizBank.answers.length * pbMultiplier);

        //load initial question
        updateQuestion();

        //true button logic
        trueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //check if user got question right
                if(answer == true){
                    correctSound();
                    score++;
                    Toast.makeText(getApplicationContext(), "Correct answer!", Toast.LENGTH_SHORT).show();
                    checkIfLastQuestion(); //check if this is the last question

                //check if user got question wrong
                }else{
                    Toast.makeText(getApplicationContext(), "Wrong answer!", Toast.LENGTH_SHORT).show();
                    checkIfLastQuestion(); //if false, still check if this is the last question
                }
            }
        });

        //False button logic
        falseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(answer == false){
                    correctSound();
                    score++;
                    Toast.makeText(getApplicationContext(), "Correct answer!", Toast.LENGTH_SHORT).show();
                    checkIfLastQuestion(); //check if this is the last question

                //check if user got question wrong
                }else{
                    Toast.makeText(getApplicationContext(), "Wrong answer!", Toast.LENGTH_SHORT).show();
                    checkIfLastQuestion(); //if false, still check if this is the last question
                }

            }
        });
    }

    private void correctSound(){
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.coin);
        mp.start();
    }

    private void checkIfLastQuestion(){
        //check if last question, if so finish current activity and launch results screen
        if(questionNumber == QuizBank.questions.length){
            Intent i = new Intent (QuizActivity.this, ResultsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("finalScore", score);
            bundle.putInt("totalQuestions",totalQuestions);
            i.putExtras(bundle);
            QuizActivity.this.finish();
            startActivity(i);
        }
        //otherwise update the question
        else {
            updateQuestion();
        }
    }

    private void updateQuestion(){
        question.setText(QuizBank.questions[questionNumber]);
        answer = QuizBank.answers[questionNumber];
        pb.setProgress(questionNumber * pbMultiplier);
        questionsRemaining.setText("" + (QuizBank.questions.length - questionNumber));
        questionNumber++;

    }
}
