package com.example.assignment14;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    int id;

    String name;

    String email;

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
