package com.example.food_wastage_management;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "food-management.db";
    private static final int DATABASE_VERSION =1;
    private static final String TABLE_NAME = "my_foods";
    private static final String COLUMN_ID = "customer_id";
    private static final String COLUMN_NAME = "customer_name";
    private static final String COLUMN_EMAIL = "customer_email";
    private static final String COLUMN_PHNO = "customer_phno";
    private static final String COLUMN_ADDRESS = "customer_address";
    private static final String COLUMN_ZIP = "customer_zip";
    private static final String COLUMN_FOODTYPE = "food_type";
    private static final String COLUMN_FOODQUANTITY = "food_quantity";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_EMAIL + " TEXT NOT NULL, " +
                COLUMN_PHNO + " TEXT NOT NULL, " +
                COLUMN_ADDRESS + " TEXT NOT NULL, " +
                COLUMN_ZIP + " TEXT NOT NULL, " +
                COLUMN_FOODTYPE + " TEXT, " +
                COLUMN_FOODQUANTITY + " INTEGER DEFAULT 0);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    void addFood(String name, String email, int phno, String address, int zip, String foodtype, int foodquantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_PHNO, phno);
        cv.put(COLUMN_ADDRESS, address);
        cv.put(COLUMN_ZIP, zip);
        cv.put(COLUMN_FOODTYPE, foodtype);
        cv.put(COLUMN_FOODQUANTITY, foodquantity);
        long result = db.insert(TABLE_NAME, null,cv);
        if (result == -1) {
            Toast.makeText(context, "Failed to submit!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Submitted Successfully!", Toast.LENGTH_LONG).show();
        }

    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db!=null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
