package com.example.assignmentweek.Interfaces;

import com.example.assignmentweek.Response.LoginResponse;
import com.example.assignmentweek.Response.Register;

public interface LoginActivityListener {

    public void login(LoginResponse response);

    public void register(Register response);
}
