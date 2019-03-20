package com.example.assignment6.Ques1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {

    private static MyDBHandler instance;
    public static String DB_NAME = "my_db";
    public static int DB_VERSION = 1;
    public static final String TABLE_NAME = "Employee";
    public static final String ID = "ID";
    public static final String NAME = "Name";
    public static final String ADDRESS = "Address";
    public static final String PHONE = "Phone_No";

    public MyDBHandler getInstance(Context context) {
        if (this.instance == null)
            instance = new MyDBHandler(context);
        return instance;
    }

    public MyDBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + TABLE_NAME + " (  " +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                NAME + " TEXT , " +
                ADDRESS + " TEXT ," +
                PHONE + " VARCHAR  )";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addEmployee(Employee employee) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, employee.getName());
        contentValues.put(ADDRESS, employee.getAddress());
        contentValues.put(PHONE, employee.getPhone());
        database.insert(TABLE_NAME, null, contentValues);
        database.close();
    }

    public void editEmployee(long id, Employee employee) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, employee.getName());
        contentValues.put(ADDRESS, employee.getAddress());
        contentValues.put(PHONE, employee.getPhone());
        database.update(TABLE_NAME, contentValues, ID + " = " +id, null);
        database.close();
    }

    public Cursor fetch() {
        SQLiteDatabase database = getWritableDatabase();
        String[] columns = new String[]{ID, NAME, PHONE, ADDRESS};
        Cursor cursor = database.query(TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public void deleteEmployee(long id) {
        SQLiteDatabase database = getWritableDatabase();
        database.delete(TABLE_NAME, ID + " = ? ", new String[]{String.valueOf(id)});
    }
}
