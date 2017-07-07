package receiver.service.android.vogella.com.project1edx.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import receiver.service.android.vogella.com.project1edx.AnswerActivity;
import receiver.service.android.vogella.com.project1edx.R;
import receiver.service.android.vogella.com.project1edx.database.CardDataSource;
import receiver.service.android.vogella.com.project1edx.model.Card;

/**
 * Created by hp on 06/05/2017.
 */

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardViewHolder> {

    List<Card> cards;
    Context context;

    public CardsAdapter(List<Card> cards, Context context) {
        this.cards = cards;
        this.context = context;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list, parent, false);
        return new CardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Card card = cards.get(position);
        holder.tvQuestion.setText(card.getQuestion());
    }

    public void update(ArrayList<Card> tasks) {
        if (tasks != null && tasks.size() > 0) {
            this.cards.clear();
            this.cards.addAll(tasks);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView tvQuestion;

        public CardViewHolder(View view) {
            super(view);

            tvQuestion = (TextView) view.findViewById(R.id.tvQuestionCustomList);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Card card = cards.get(getAdapterPosition());
            Intent intent = new Intent(context, AnswerActivity.class);
            intent.putExtra("q", card.getQuestion());
            intent.putExtra("a", card.getAnswer());
            context.startActivity(intent);
            Toast.makeText(context, card.getQuestion(), Toast.LENGTH_SHORT).show();
            v.setSelected(true);
        }

        @Override
        public boolean onLongClick(View v) {
            final Card card = cards.get(getAdapterPosition());
            final CardDataSource dataSource = new CardDataSource(context);
            AlertDialog dialog = new AlertDialog.Builder(context).create();
            dialog.setMessage("Do you want to delete this card?");
            dialog.setButton(dialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dataSource.open();
                    dataSource.deleteCard(card.getQuestion());

                    update((ArrayList<Card>) dataSource.getAllCards());
                    Toast.makeText(context, card.getQuestion() + "is deleted", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.setButton(dialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dialog.show();
            return false;
        }
    }
}
