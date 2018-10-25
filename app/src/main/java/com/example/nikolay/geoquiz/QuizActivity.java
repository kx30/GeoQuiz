package com.example.nikolay.geoquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "Index";

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreviousButton;

    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia, true, false),
            new Question(R.string.question_ocean, true, false),
            new Question(R.string.question_mideast, false, false),
            new Question(R.string.question_africa, false, false),
            new Question(R.string.question_americas, true, false),
            new Question(R.string.question_asia, true, false),
    };

    private int mCurrentIndex = 0;

    private int rightAnswers = 0;
    private int mAmountOfAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "OnCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCurrentIndex();
                updateQuestion();
            }
        });

        mPreviousButton = (Button) findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPreviousCurrentIndex();
                updateQuestion();
                isAnswered();
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCurrentIndex();
                updateQuestion();
                isAnswered();
            }
        });

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
                questionAnswered();
                isAnswered();
                checkCorrectPercent();
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
                questionAnswered();
                isAnswered();
                checkCorrectPercent();
            }
        });

        updateQuestion();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "OnSaveInstanceState");
        outState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void updateCurrentIndex() {
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
    }

    private void setPreviousCurrentIndex() {
        if (mCurrentIndex != 0) {
            mCurrentIndex -= 1;
        } else {
            mCurrentIndex = mQuestionBank.length - 1;
        }
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId;

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
            rightAnswers++;
        } else {
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();

    }

    private void questionAnswered() {
        mQuestionBank[mCurrentIndex].setQuestionAnswered(true);
    }

    private void isAnswered() {
        if (mQuestionBank[mCurrentIndex].isQuestionAnswered()) {
            mFalseButton.setClickable(false);
            mTrueButton.setClickable(false);
        } else {
            mFalseButton.setClickable(true);
            mTrueButton.setClickable(true);
        }
    }

    private void checkCorrectPercent() {
        mAmountOfAnswers++;
        if (mAmountOfAnswers == mQuestionBank.length) {
            Toast.makeText(this, "Correct answers: " + String.valueOf((100 * rightAnswers / mQuestionBank.length) + "%"), Toast.LENGTH_LONG).show();
        }
        Log.d(TAG, mAmountOfAnswers + "/" + mQuestionBank.length);
    }

}