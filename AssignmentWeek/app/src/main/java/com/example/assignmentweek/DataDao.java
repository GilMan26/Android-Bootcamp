package com.example.assignmentweek;

import android.app.VoiceInteractor;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.assignmentweek.Constants.Contract;
import com.example.assignmentweek.Response.Datum;

import java.util.List;

@Dao
public interface DataDao {

    @Insert
    void insertAll(Datum...datums);

    @Insert
    void insert(Datum datum);

    @Query("SELECT * FROM "+ Contract.TABLE_NAME)
    List<Datum> getAll();

    @Delete
    void deleteUser(Datum datum);

    @Query("DELETE FROM "+Contract.TABLE_NAME)
    void deleteAll();
}
