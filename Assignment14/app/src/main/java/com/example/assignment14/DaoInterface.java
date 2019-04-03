package com.example.assignment14;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DaoInterface {

    @Insert
    void addUser(User user);

    @Query("SELECT * FROM User")
    LiveData<List<User>> getAll();


    @Query("DELETE FROM User")
    void deleteAll();

}
