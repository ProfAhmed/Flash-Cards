package receiver.service.android.vogella.com.project1edx.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hp on 07/05/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final String TABLE_CARDS = "cards2";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_QUESTION = "question";
    public static final String COLUMN_ANSWER = "answer";

    private static final String DATABASE_NAME = "CARDS.db";
    private static final int DATABASE_VERSION = 5;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_CARDS + "( " + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_QUESTION
            + " string, " + COLUMN_ANSWER + " string);";

    private static final String DATABASE_ALTER_CARD_1 = "ALTER TABLE "
            + TABLE_CARDS + " ADD COLUMN " + COLUMN_ANSWER + " string;";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion == 5){
            db.execSQL(DATABASE_CREATE);
        }
    }
}
