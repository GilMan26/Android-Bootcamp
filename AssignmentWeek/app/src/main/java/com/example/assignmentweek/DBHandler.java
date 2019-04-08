package com.example.assignmentweek;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.assignmentweek.Constants.Contract;

public class DBHandler extends SQLiteOpenHelper {

    private static DBHandler instance;

    public DBHandler(Context context) {
        super(context, Contract.DATABASE_NAME, null, Contract.DATABASE_VERSION);
    }

    public DBHandler getInstance(Context context){
        if(this.instance==null)
            instance=new DBHandler(context);
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + Contract.TABLE_NAME + " (  " +
                Contract.COLUMN_ID + " INTEGER PRIMARY KEY , " +
                Contract.COLUMN_FIRST_NAME + " TEXT ," +
                Contract.COLUMN_LAST_NAME + " TEXT ," +
                Contract.COLUMN_AVATAR + " TEXT  )";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addUser(){

    }

    public void deleteuser(){

    }

    public void updateUser(){

    }


}
