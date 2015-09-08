package book.geoquiz.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import book.geoquiz.geoquiz.model.Question;

public class QuizActivity extends AppCompatActivity {

    private final static String TAG =  "QuizActivity";
    private final static String KEY_INDEX =  "index";
    private final static int REQUEST_CODE_CHEAT = 0;


    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;
    private Question question;
    private int mCurrentIndex = 0;
    private boolean mIsCheater;

    private Question [] mQuestionBank = new Question[]{
            new Question(R.string.question_africa, true),
            new Question(R.string.question_america, false),
            new Question(R.string.question_mideast, true),
            new Question(R.string.question_oceans, false),
            new Question(R.string.question_asia, true)
    };

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        extractingInfoFromIntent();
        recoverFromActivityRestart(savedInstanceState);

        setBehaviourOfTrueButton();
        setBehaviourOfFalseButton();
        setBehaviourOfNextButton();
        setBehaviourOfCheatButton();

        updateQuestion();

    }

    private void extractingInfoFromIntent() {
        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
    }

    private void setBehaviourOfCheatButton() {
        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent i = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                startActivityForResult(i, REQUEST_CODE_CHEAT);

            }
        });
    }

    private void setBehaviourOfNextButton() {
        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mIsCheater = false;
                updateQuestion();
            }
        });
    }

    private void setBehaviourOfFalseButton() {
        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
    }

    private void setBehaviourOfTrueButton() {
        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        if(resultCode != Activity.RESULT_OK){
            return;
        }

        if(requestCode == REQUEST_CODE_CHEAT){
            if(result == null){
                return;
            }
            mIsCheater = CheatActivity.wasAnswerShown(result);
        }

    }

    private void recoverFromActivityRestart(Bundle savedInstanceState) {
        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
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

    private void checkAnswer(boolean userClickedOnTrueButton) {

        int messageId = -1;

        boolean isAnswerReallyTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        if(mIsCheater) {
            messageId = R.string.judgement_toast;
        } else {
            if(userClickedOnTrueButton && isAnswerReallyTrue) {
                messageId = R.string.true_message;
            } else {
                messageId = R.string.false_message;
            }
        }


        Toast.makeText(QuizActivity.this, messageId, Toast.LENGTH_SHORT).show();
    }

    private void updateQuestion() {
        int questionId = mQuestionBank[mCurrentIndex].getTextId();
        mQuestionTextView.setText(questionId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
