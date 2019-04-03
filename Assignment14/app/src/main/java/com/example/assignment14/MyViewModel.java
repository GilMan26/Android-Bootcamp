package com.example.assignment14;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    private DataManager dataManager;
    private LiveData<List<User>> list;

    public MyViewModel(@NonNull Application application) {
        super(application);
        dataManager = new DataManager(application);

        list = dataManager.getAll();/*

    private static class PopulateAsync extends AsyncTask<Void, Void, Void> {
        private DaoInterface daoInterface;

        public PopulateAsync(UserDatabase userDatabase) {
            this.daoInterface = userDatabase.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            daoInterface.addUser(new User("Mandeep", "mandeep.gill@tothenew.com"));
            return null;
        }
    }*/
    }

    public void insert(User user) {
        dataManager.insert(user);
    }

//    public void update(User user) {
//        dataManager.update(user);
//    }
//
//    public void delete(User user) {
//        dataManager.delete(user);
//    }

    public void deleteAllUser() {
        dataManager.deleteAll();
    }

    public LiveData<List<User>> getAllUsers() {
        list = dataManager.getAll();
        return list;
    }

//    public static class MyViewModelFactory implements ViewModelProvider.Factory {
//        private Application mApplication;
//
//
//        public MyViewModelFactory(Application application) {
//            mApplication = application;
//        }
//
//
//        @Override
//        public <T extends ViewModel> T create(Class<T> modelClass) {
//            return (T) new MyViewModel(mApplication);
//        }
//    }
}
