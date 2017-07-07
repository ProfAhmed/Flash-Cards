package receiver.service.android.vogella.com.project1edx.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import receiver.service.android.vogella.com.project1edx.R;
import receiver.service.android.vogella.com.project1edx.adapter.CardsAdapter;
import receiver.service.android.vogella.com.project1edx.database.CardDataSource;
import receiver.service.android.vogella.com.project1edx.model.Card;

/**
 * Created by hp on 07/05/2017.
 */

public class AddCard {
    EditText etQuestion, etAnswer;
    Button add, cancel;
    Context context;
    View customView;
    String question, answer;
    List<Card> cards;

    public AddCard(final Context context, final CardDataSource dataSource, final CardsAdapter adapter) {
        this.context = context;

        LayoutInflater li = LayoutInflater.from(context);
        customView = li.inflate(R.layout.custom_dialog, null);
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setView(customView);

        add = (Button) customView.findViewById(R.id.add);
        cancel = (Button) customView.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "cancel ", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etQuestion = (EditText) customView.findViewById(R.id.etQuestion);
                etAnswer = (EditText) customView.findViewById(R.id.etAnswer);
                question = etQuestion.getText().toString();
                answer = etAnswer.getText().toString();
                if (question.equals("") || answer.equals("")) {
                    Toast.makeText(context, "Please Enter the empty fields ", Toast.LENGTH_SHORT).show();
                } else {
                    Card card = new Card(question, answer);
                    dataSource.addCard(card);
                    cards = dataSource.getAllCards();
                    adapter.update((ArrayList<Card>) cards);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(context, question + " Is Added ", Toast.LENGTH_SHORT).show();
                    Snackbar.make(customView, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }
}
