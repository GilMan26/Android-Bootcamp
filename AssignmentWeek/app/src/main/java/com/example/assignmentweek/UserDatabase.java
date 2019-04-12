package com.example.assignmentweek;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.assignmentweek.Constants.Contract;
import com.example.assignmentweek.Response.Datum;

@Database(entities = {Datum.class}, version = 1)

public abstract class UserDatabase extends RoomDatabase {

    private static UserDatabase instance;
    public abstract DataDao dao();

    public static synchronized UserDatabase getInstance(Context context){
        if (instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    UserDatabase.class, Contract.DATABASE_NAME)
                    .build();
        }
        return instance;
    }
}
