package com.example.assignmentweek.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.assignmentweek.DataManager;
import com.example.assignmentweek.Interfaces.LoginActivityListener;
import com.example.assignmentweek.R;
import com.example.assignmentweek.Request.LoginRequest;
import com.example.assignmentweek.Response.LoginResponse;
import com.example.assignmentweek.Response.Register;
import com.example.assignmentweek.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements LoginActivityListener {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ActivityLoginBinding binding;
    DataManager dataManager;
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        fixPreferences();
        dataManager = new DataManager(this);
        dataManager.setInstance(this);
        binding.loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.usernameET.getText().toString().equals(sharedPreferences.getString(USERNAME, "def")) && binding.passwordET.getText().toString().equals(sharedPreferences.getString(PASSWORD, "1234"))){
                    Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    return;
                }
                LoginRequest request = new LoginRequest(binding.usernameET.getText().toString(), binding.passwordET.getText().toString());
                dataManager.loginUseer(request);
            }
        });
        binding.registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginRequest request = new LoginRequest(binding.usernameET.getText().toString(), binding.passwordET.getText().toString());
                dataManager.registerUser(request);
            }
        });
    }

    public void fixPreferences() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(USERNAME, "abc");
        editor.putString(PASSWORD, "123");
        editor.apply();
    }

    @Override
    public void login(LoginResponse response) {
        Toast.makeText(this, response.getToken()+"Login", Toast.LENGTH_LONG).show();
    }

    @Override
    public void register(Register response) {
        Toast.makeText(this, response.getToken(), Toast.LENGTH_LONG).show();
    }
}
