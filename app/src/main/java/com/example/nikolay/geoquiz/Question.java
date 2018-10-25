package com.example.nikolay.geoquiz;

public class Question {

    private int mTextResId;
    private boolean mAnswerTrue;
    private boolean mQuestionAnswered;

    public Question(int textResId, boolean answerTrue, boolean questionAnswered) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
        mQuestionAnswered = questionAnswered;
    }

    public boolean isQuestionAnswered() {
        return mQuestionAnswered;
    }

    public void setQuestionAnswered(boolean questionAnswered) {
        mQuestionAnswered = questionAnswered;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}