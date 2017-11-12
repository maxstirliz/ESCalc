package lymansky.artem.escalc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;

/**
 * Created by artem on 11/3/2017.
 */

public class DatabaseManager {
  
  private static final String TABLE_CART = "CART";
  private static final String CART_NAME = "NAME";
  private static final String CART_PRICE = "PRICE";
  private static final String CART_NUMBER = "NUMBER";
  private static final String CART_TOTAL = "TOTAL";
  private static final String CART_INCLUDED = "INCLUDED";
  private static final int CART_NAME_NO = 0;
  private static final int CART_PRICE_NO = 1;
  private static final int CART_NUMBER_NO = 2;
  private static final int CART_INCLUDED_NO = 3;
  
  private Context mContext;
  private SQLiteDatabase mDatabase;
  
  public DatabaseManager(Context context) {
    mContext = context.getApplicationContext();
    mDatabase = new DatabaseHelper(mContext).getWritableDatabase();
  }
  
  public void loadDataFromDB(ArrayList<Product> dataSet) {
    
    dataSet.clear();
    try {
      Cursor cursor = mDatabase.query(
              TABLE_CART,
              new String[]{CART_NAME, CART_PRICE, CART_NUMBER, CART_INCLUDED},
              null, null, null, null, null);
      if (cursor.moveToFirst()) {
        do {
          String name = cursor.getString(CART_NAME_NO);
          double price = cursor.getDouble(CART_PRICE_NO);
          double number = cursor.getDouble(CART_NUMBER_NO);
          boolean included = cursor.getInt(CART_INCLUDED_NO) == 1;
          dataSet.add(new Product(name, price, number, included));
        } while (cursor.moveToNext());
        cursor.close();
        mDatabase.close();
      }
    } catch (SQLiteException e) {
      e.printStackTrace();
    }
  }
  
  public void writeDataToDB(ArrayList<Product> productSet) {
    
    try {
      mDatabase.delete(TABLE_CART, null, null);
      for (int i = 0; i < productSet.size(); i++) {
        Product product = productSet.get(i);
        String name = product.getName();
        double price = product.getPrice();
        double number = product.getNumber();
        double total = product.getTotal();
        boolean isIncluded = product.getIsIncluded();
        insertData(name, price, number, total, isIncluded);
      }
    } catch (SQLiteException e) {
      e.printStackTrace();
    }
  }
  
  private void insertData(String name,
                          double price,
                          double number,
                          double total,
                          boolean isIncluded) {
    try {
      ContentValues contentValues = new ContentValues();
      contentValues.put(CART_NAME, name);
      contentValues.put(CART_PRICE, price);
      contentValues.put(CART_NUMBER, number);
      contentValues.put(CART_TOTAL, total);
      contentValues.put(CART_INCLUDED, isIncluded);
      mDatabase.insert(TABLE_CART, null, contentValues);
    } catch (SQLiteException e) {
      e.printStackTrace();
    }
  }
  
  //TODO: explicitly close database in a lifecycle method
  public void closeDatabase() {
    mDatabase.close();
  }
  
}
