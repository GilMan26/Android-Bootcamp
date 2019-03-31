package com.example.assignment13;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.assignment13.databinding.ActivityViewModelBinding;

import java.util.Random;

public class ViewModelActivity extends AppCompatActivity {
    MyViewModel model;
    ActivityViewModelBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_model);
        model = ViewModelProviders.of(this).get(MyViewModel.class);
        binding.linearLayout.setBackgroundColor(model.colorCode);
    }

    public void getColor(View view){
        Random random=new Random();
        int color= Color.argb(255,random.nextInt(256),random.nextInt(256),random.nextInt(256));
        model.colorCode=color;
        binding.linearLayout.setBackgroundColor(color);
    }


}
