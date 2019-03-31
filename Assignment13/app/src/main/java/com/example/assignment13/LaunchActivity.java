package com.example.assignment13;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
    }

    public void startQues1(View view){
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void startQues2(View view){
        Intent intent=new Intent(this, ViewModelActivity.class);
        startActivity(intent);
    }
}
