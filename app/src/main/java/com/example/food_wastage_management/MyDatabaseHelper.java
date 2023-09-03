package com.example.food_wastage_management;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "food-management.db";
    private static final int DATABASE_VERSION = 2; // Increment for version update
    private static final String TABLE_NAME = "my_foods";
    private static final String COLUMN_ID = "customer_id";
    private static final String COLUMN_NAME = "customer_name";
    private static final String COLUMN_EMAIL = "customer_email";
    private static final String COLUMN_PHNO = "customer_phno";
    private static final String COLUMN_ADDRESS = "customer_address";
    private static final String COLUMN_ZIP = "customer_zip";
    private static final String COLUMN_FOODTYPE = "food_type";
    private static final String COLUMN_FOODQUANTITY = "food_quantity";
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT NOT NULL, " + COLUMN_EMAIL + " TEXT NOT NULL, " + COLUMN_PHNO + " INTEGER NOT NULL, " + COLUMN_ADDRESS + " TEXT NOT NULL, " + COLUMN_ZIP + " INTEGER NOT NULL, " + COLUMN_FOODTYPE + " TEXT, " + COLUMN_FOODQUANTITY + " INTEGER DEFAULT 0 NOT NULL);");
        String userTableQuery = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT NOT NULL, " +
                COLUMN_PASSWORD + " TEXT NOT NULL);";
        db.execSQL(userTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db); // Recreate the table with updated schema
        }
    }

    public boolean addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USERNAME, username);
        cv.put(COLUMN_PASSWORD, password);
        long result = db.insert(TABLE_USERS, null, cv);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkUserCredentials(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID};
        String selection = COLUMN_USERNAME + " = ?" + " AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);

        int count = cursor.getCount();
        cursor.close();

        return count > 0;
    }

    public boolean addFood(String name, String email, String phno, String address, String zip, String foodtype, String foodquantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_PHNO, phno);
        cv.put(COLUMN_ADDRESS, address);
        cv.put(COLUMN_ZIP, zip);
        cv.put(COLUMN_FOODTYPE, foodtype);
        cv.put(COLUMN_FOODQUANTITY, foodquantity);


        long result = db.insert(TABLE_NAME, null, cv);
        if (result != -1) {
            return true;
        } else {
            return false;
        }
    }

    public Cursor readAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;
    }

    public boolean deleteFood(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        return result > 0;
    }
}
