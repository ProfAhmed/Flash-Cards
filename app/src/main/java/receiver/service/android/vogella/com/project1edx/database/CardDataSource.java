package receiver.service.android.vogella.com.project1edx.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import receiver.service.android.vogella.com.project1edx.model.Card;

import static android.R.attr.id;

/**
 * Created by hp on 07/05/2017.
 */

public class CardDataSource {

    private SQLiteDatabase database;
    private DbHelper dbHelper;
    private String[] allColumns = {dbHelper.COLUMN_ID,
            dbHelper.COLUMN_QUESTION, dbHelper.COLUMN_ANSWER};

    public CardDataSource(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addCard(Card card) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COLUMN_QUESTION, card.getQuestion());
        values.put(dbHelper.COLUMN_ANSWER, card.getAnswer());
        database.insert(dbHelper.TABLE_CARDS, null, values);
    }

    public void deleteCard(String cardName) {
        System.out.println("Comment deleted with id: " + id);

        database.delete(dbHelper.TABLE_CARDS, dbHelper.COLUMN_QUESTION + " = ?",
                new String[]{cardName});
    }

    public List<Card> getAllCards() {
        List<Card> cards = new ArrayList();

        Cursor cursor = database.query(dbHelper.TABLE_CARDS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Card card = cursorToCard(cursor);
            cards.add(card);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return cards;
    }

    private Card cursorToCard(Cursor cursor) {
        Card card = new Card();
        card.setId(cursor.getInt(0));
        card.setQuestion(cursor.getString(1));
        card.setAnswer(cursor.getString(2));
        return card;
    }

}
