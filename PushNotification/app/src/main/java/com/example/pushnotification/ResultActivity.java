package com.example.pushnotification;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.pushnotification.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {
    ActivityResultBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_result);
        Intent intent=getIntent();
        binding.textView.setText(intent.getStringExtra("value"));
        Glide.with(this).load(intent.getStringExtra("url")).into(binding.imageView);

    }
}
