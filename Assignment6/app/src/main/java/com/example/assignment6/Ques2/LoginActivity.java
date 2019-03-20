package com.example.assignment6.Ques2;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment6.R;

public class LoginActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText usernameET,passwordET;
    Button loginBtn,logoutBtn;
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences=getPreferences(MODE_PRIVATE);

        usernameET=findViewById(R.id.username);
        passwordET=findViewById(R.id.password);
        loginBtn=findViewById(R.id.loginbtn);
        logoutBtn=findViewById(R.id.logoutbtn);

        if(sharedPreferences.contains(USERNAME)){
            usernameET.setText(sharedPreferences.getString(USERNAME, ""));
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!usernameET.getText().toString().equals("") && !passwordET.getText().toString().equals("")) {
                    editor = sharedPreferences.edit();
                    editor.putString(USERNAME, usernameET.getText().toString());
                    editor.putString(PASSWORD, passwordET.getText().toString());
                    editor.apply();
                    loginBtn.setEnabled(false);
                }
                Toast.makeText(v.getContext(), "User Details Saved", Toast.LENGTH_SHORT).show();
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Toast.makeText(v.getContext(), "Cleared User Details", Toast.LENGTH_SHORT).show();
                loginBtn.setEnabled(true);
            }
        });
    }
}
