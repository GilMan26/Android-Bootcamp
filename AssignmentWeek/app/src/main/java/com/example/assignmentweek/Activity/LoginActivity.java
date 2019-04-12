package com.example.assignmentweek.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.assignmentweek.Constants.Contract;
import com.example.assignmentweek.DataManager;
import com.example.assignmentweek.Fragments.SplashFragment;
import com.example.assignmentweek.Interfaces.LoginActivityListener;
import com.example.assignmentweek.R;
import com.example.assignmentweek.Request.SignRequest;
import com.example.assignmentweek.Response.LoginResponse;
import com.example.assignmentweek.Response.Register;
import com.example.assignmentweek.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements LoginActivityListener {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ActivityLoginBinding binding;
    DataManager dataManager;
    SplashFragment fragment;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        fixPreferences();
        fragment=new SplashFragment();
        fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.add(R.id.loginFrame, fragment);
        transaction.addToBackStack("splash");
        transaction.commit();
        showSplash();
        dataManager = new DataManager(this);
        dataManager.setLoginListener(this);
        binding.loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isValidEmail(binding.usernameET.getText().toString())){
                    Toast.makeText(LoginActivity.this, "Invalid Email", Toast.LENGTH_LONG).show();
                    return;
                }
                else if(binding.usernameET.getText().toString().equals(sharedPreferences.getString(Contract.USERNAME, "def")) && binding.passwordET.getText().toString().equals(sharedPreferences.getString(Contract.PASSWORD, "1234"))){
                    Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    return;
                }
                else{
                    SignRequest request = new SignRequest(binding.usernameET.getText().toString(), binding.passwordET.getText().toString());
                    dataManager.loginUseer(request);
                }

            }
        });
        binding.registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidEmail(binding.usernameET.getText())){
                    Toast.makeText(LoginActivity.this, "Invalid Email", Toast.LENGTH_LONG).show();
                    return;
                }
                SignRequest request = new SignRequest(binding.usernameET.getText().toString(), binding.passwordET.getText().toString());
                dataManager.registerUser(request);
            }
        });
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public void fixPreferences() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(Contract.USERNAME, "abc@123.com");
        editor.putString(Contract.PASSWORD, "123");
        editor.putBoolean(Contract.FIRST_LAUNCH, true);
        editor.putBoolean(Contract.SKIP_LOGIN, true);
        editor.apply();
    }

    public void showSplash(){
        if(sharedPreferences.getBoolean(Contract.FIRST_LAUNCH, true)){
            Handler handler = new Handler();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
//                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentManager.popBackStack();
                }
            };
            handler.postDelayed(runnable, 1500);
        }
        editor.putBoolean(Contract.FIRST_LAUNCH, false);

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
