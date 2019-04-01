package com.example.assignment14;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    private DataManager dataManager;
    private LiveData<List<User>> list;

    public MyViewModel(@NonNull Application application) {
        super(application);
        dataManager=new DataManager(application);
        list=dataManager.getAllNote();

    }
    public void insert(User user){
        dataManager.insert(user);
    }public void update(User user){
        dataManager.update(user);
    }public void delete(User user){
        dataManager.delete(user);
    }public void deleteAll(){
        dataManager.deleteAllNotes();
    }

    public LiveData<List<User>> getAllNotes() {
        return list;
    }
}
