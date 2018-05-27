package com.londonappbrewery.quizzler;

public class TrueFalse {

    private int mQuestoinId;
    private boolean mAnswer;

    public TrueFalse(int mQuestoinId, boolean mAnswer) {
        this.mQuestoinId = mQuestoinId;
        this.mAnswer = mAnswer;
    }

    public int getmQuestoinId() {
        return mQuestoinId;
    }

    public void setmQuestoinId(int mQuestoinId) {
        this.mQuestoinId = mQuestoinId;
    }

    public boolean ismAnswer() {
        return mAnswer;
    }

    public void setmAnswer(boolean mAnswer) {
        this.mAnswer = mAnswer;
    }
}
