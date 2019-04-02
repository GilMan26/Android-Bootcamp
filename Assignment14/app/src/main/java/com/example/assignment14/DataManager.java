package com.example.assignment14;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class DataManager {

    private DaoInterface daoInterface;
    private LiveData<List<User>> list;
    public DataManager(Application application){
        UserDatabase database = UserDatabase.getInstance(application);
        daoInterface =database.userDao;
        list= daoInterface.getAll();
    }
    public void insert(User user){
        new AddUserAsyncTask(daoInterface).execute(user);

    }
    public void update(User user){
        new UpdateUserAsyncTask(daoInterface).execute(user);
    }
    public void delete(User user){
        new DeleteUserAsyncTask(daoInterface).execute(user);

    }
    public void deleteAll(){
        new DeleteAllUserAsyncTask(daoInterface).execute();
    }

    public LiveData<List<User>> getAll() {
        return list;
    }

    private static class AddUserAsyncTask extends AsyncTask<User, Void, Void>{
        private DaoInterface daoInterface;
        private AddUserAsyncTask(DaoInterface userDao){
            this.daoInterface = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            daoInterface.addUser(users[0]);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void>{
        private DaoInterface daoInterface;
        private DeleteUserAsyncTask(DaoInterface userDao){
            this.daoInterface = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            daoInterface.deleteUser(users[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void>{
        private DaoInterface daoInterface;
        private UpdateUserAsyncTask(DaoInterface userDao){
            this.daoInterface = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            daoInterface.updateUser(users[0]);
            return null;
        }
    }

    private static class DeleteAllUserAsyncTask extends AsyncTask<Void, Void, Void>{
        private DaoInterface daoInterface;
        private DeleteAllUserAsyncTask(DaoInterface userDao){
            this.daoInterface = userDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            daoInterface.deleteAll();
            return null;
        }
    }
}
