package receiver.service.android.vogella.com.project1edx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {

    TextView tvQuestion, tvAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        tvQuestion = (TextView) findViewById(R.id.tvQuestion);
        tvAnswer = (TextView) findViewById(R.id.tvAnswer);
        Bundle intent = getIntent().getExtras();
        String question = intent.getString("q");
        String answer = intent.getString("a");
        tvQuestion.setText(question);
        tvAnswer.setText(answer);

    }
}
