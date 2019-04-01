package com.example.assignment14;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = User.class , version = 1)

public abstract class UserDatabase extends RoomDatabase {
    private static UserDatabase instance;
    DaoInterface userDao;


    public static synchronized UserDatabase getInstance(Context context){
        if (instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    UserDatabase.class,"note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }return instance;
    }

    private static RoomDatabase.Callback roomCallback=new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsync(instance).execute();
        }
    };

    private static class PopulateAsync extends AsyncTask<Void, Void, Void> {
        private DaoInterface daoInterface;

        public PopulateAsync(UserDatabase userDatabase) {
            this.daoInterface = userDatabase.userDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            daoInterface.addUser(new User("Name 1", "Email 1"));
            daoInterface.addUser(new User("Name 2", "Email 2"));
            daoInterface.addUser(new User("Name 3", "Email 3"));
            return null;
        }
    }

}
