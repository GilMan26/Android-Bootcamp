package com.example.assignmentweek.Interfaces;

import com.example.assignmentweek.Response.Datum;

public interface IUserTouchListener {

    void getClickedUser(Datum datum);

    void deleteUser(long id);

    void updateUser(long id);
}
