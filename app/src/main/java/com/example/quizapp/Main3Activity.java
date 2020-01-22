package com.example.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Main3Activity extends AppCompatActivity {

    TextView questionLabel, questionCountLabel, scoreLabel;
    EditText answerEdt;
    Button submitButton;
    ProgressBar progressBar;
    ArrayList<QuestionModel> questionModelsArrayList;

    int currentPossition = 0;
    int numberOfCorrectAnswer = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        questionCountLabel = findViewById(R.id.noQuestion);
        questionLabel = findViewById(R.id.question);
        scoreLabel = findViewById(R.id.score);

        answerEdt = findViewById(R.id.answer);
        submitButton = findViewById(R.id.submit);
        progressBar = findViewById(R.id.progress);

        questionModelsArrayList = new ArrayList<>();
        setUpQuestion();
        setData();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answerEdt.getText().toString().trim().equals("")) {
                    checkAnswer();
                }
                else{
                    Toast.makeText(Main3Activity.this,"please add correct answer!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void checkAnswer() {

        String answerString = answerEdt.getText().toString().trim();

        if (answerString.equalsIgnoreCase(questionModelsArrayList.get(currentPossition).getAnswer())) {
            numberOfCorrectAnswer++;

            new SweetAlertDialog(Main3Activity.this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Good Job")
                    .setContentText("Right Answer")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            currentPossition++;
                            setData();
                            answerEdt.setText("");
                            sweetAlertDialog.dismiss();
                        }
                    })

                    .show();

        } else {
            new SweetAlertDialog(Main3Activity.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Wrong Answer")
                    .setContentText("The Right answer is: " + questionModelsArrayList.get(currentPossition).getAnswer())
                    .setConfirmText("OK")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            currentPossition++;
                            setData();
                            answerEdt.setText("");
                        }
                    })
                    .show();
        }
        progressBar.setProgress(currentPossition+1);

    }

    public void setUpQuestion() {
        questionModelsArrayList.add(new QuestionModel("What is 1 + 2 ?", "3"));
        questionModelsArrayList.add(new QuestionModel("What is 8 + 8 ?", "16"));
        questionModelsArrayList.add(new QuestionModel("What is 6 * 6 ?", "36"));
        questionModelsArrayList.add(new QuestionModel("What is 4 - 2 ?", "2"));
        progressBar.setMax(questionModelsArrayList.size());
    }

    public void setData() {
        if (questionModelsArrayList.size() > currentPossition) {

            questionLabel.setText(questionModelsArrayList.get(currentPossition).getQuestionString());

            scoreLabel.setText("Score: " + numberOfCorrectAnswer + "/" + questionModelsArrayList.size());
            questionCountLabel.setText("Question No : " + (currentPossition + 1));
        } else {

            new SweetAlertDialog(Main3Activity.this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("You have Successfully completed the Quiz")
                    .setContentText("Your Score is :" + numberOfCorrectAnswer +"/" + questionModelsArrayList.size())
                    .setConfirmText("Restart")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                            currentPossition=0;
                            numberOfCorrectAnswer=0;
                            //progressBar.setProgress(0);
                            setData();
                        }
                    })
                    .setCancelText("Close")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                            finish();
                        }
                    })
                    .show();


        }
    }

}



