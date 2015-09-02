package book.geoquiz.geoquiz.model;

import android.util.Log;

/**
 * Created by jorge.bautista on 9/1/15.
 */
public class Question {

    private final static String TAG = "Question" ;

    private int mTextId;
    private boolean mAnswerTrue;

    public Question(int questionId, boolean answer) {

        Log.d(TAG, "Question id: " + questionId + " answer: " + answer);
        mTextId = questionId;
        mAnswerTrue = answer;

    }

    public int getTextId() {
        return mTextId;
    }

    public void setTextId(int textId) {
        mTextId = textId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
