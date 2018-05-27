package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    Button mTrueButton;
    Button mFalseButton;
    TextView mTextView;
    int mIndex;
    int mQuestion;
    TextView mScoreView;
    int mScore;
    ProgressBar mProgressView;

    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };

    private final int PROGRESS_BAR_INCREMENT = (int) Math.ceil(100.0 / mQuestionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mScore = savedInstanceState.getInt("ScoreKey");
            mIndex = savedInstanceState.getInt("IndexKey");
        }
        else {

            mScore = 0;
            mIndex = 0;
        }

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mTextView = (TextView) findViewById(R.id.question_text_view);
        mScoreView = (TextView) findViewById(R.id.score);
        mProgressView = (ProgressBar) findViewById(R.id.progress_bar);

        mQuestion = mQuestionBank[mIndex].getmQuestoinId();
        mTextView.setText(mQuestion);

        mScoreView.setText(("Score " + mScore + "/" + (mQuestionBank.length)));
        takeAnswer();
    }

    private void takeAnswer() {

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Quizzler", "True Button has Been Pressed");
                checkAnswer(true);
                updateQuestion();
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Quizzler", "False Button has Been Pressed");
                checkAnswer(false);
                updateQuestion();
            }
        });
    }

    private void checkAnswer(boolean userSelection) {


        boolean correctAnswer = mQuestionBank[mIndex].ismAnswer();

        Toast toast = null;

        if (correctAnswer == userSelection) {
            toast = Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT);
            mScore++;
        }
        else {
            toast = Toast.makeText(getApplicationContext(), R.string.incorrect_toast, Toast.LENGTH_SHORT);
        }

        toast.show();
    }

    private void updateQuestion() {
        mIndex++;
        if (mIndex < mQuestionBank.length) {
            mQuestion = mQuestionBank[mIndex].getmQuestoinId();
            mTextView.setText(mQuestion);
            mProgressView.incrementProgressBy(PROGRESS_BAR_INCREMENT);
            mScoreView.setText(("Score " + mScore + "/" + (mQuestionBank.length)));
        }
        else {
            AlertDialog.Builder completeAlert  = new AlertDialog.Builder(this);
            completeAlert.setTitle("Game Over");
            completeAlert.setCancelable(false);
            completeAlert.setMessage("You scored: " + mScore);
            completeAlert.setPositiveButton("Close App", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            completeAlert.show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putInt("ScoreKey", mScore);
        outState.putInt("IndexKey", mIndex);
    }
}
