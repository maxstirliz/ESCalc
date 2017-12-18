package lymansky.artem.escalc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by artem on 11/3/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "PRODUCTS";
    private static final int DB_VERSION = 1;
    private SQLiteDatabase db;

    DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL("CREATE TABLE CART (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NAME TEXT, " +
                "PRICE REAL, " +
                "NUMBER REAL, " +
                "TOTAL REAL, " +
                "INCLUDED NUMERIC);");

        db.execSQL("CREATE TABLE SHOPPINGLIST (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NAME TEXT, " +
                "BOUGHT NUMERIC);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
