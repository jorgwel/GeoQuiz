package book.geoquiz.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import book.geoquiz.geoquiz.model.Question;

public class QuizActivity extends AppCompatActivity {

    private final static String TAG =  "QuizActivity";

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;

    private TextView mQuestionTextView;
    private Question question;
    private int mCurrentIndex = 0;

    private Question [] mQuestionBank = new Question[]{
            new Question(R.string.question_africa, true),
            new Question(R.string.question_america, false),
            new Question(R.string.question_mideast, true),
            new Question(R.string.question_oceans, false),
            new Question(R.string.question_asia, true)
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementIndexAndUpdate();
            }
        });

        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementIndexAndUpdate();
            }
        });

        mPrevButton = (ImageButton) findViewById(R.id.previous_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Decrementing", TAG);
                decrementIndexAndUpdate();
            }
        });

        updateQuestion();

    }

    private void decrementIndexAndUpdate() {
        mCurrentIndex = mCurrentIndex - 1 <= 0 ?
                                    mQuestionBank.length -1
                                    :
                                    mCurrentIndex - 1;

        updateQuestion();
    }

    private void incrementIndexAndUpdate() {
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
        updateQuestion();
    }

    private void checkAnswer(boolean userClickedOnTrueButton) {

        int messageId = -1;

        boolean isAnswerReallyTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        if(userClickedOnTrueButton && isAnswerReallyTrue) {
            messageId = R.string.true_message;
        } else {
            messageId = R.string.false_message;
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
