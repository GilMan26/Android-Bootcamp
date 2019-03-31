package com.example.assignment13;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.example.assignment13.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    List<Post> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding= DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        populateData();
        CustomAdapter customAdapter=new CustomAdapter(list);
        binding.recyclerView.setAdapter(customAdapter);

    }

    private void populateData() {
        for(int i=0;i<20;i++){
            Post post=new Post();
            post.setName("Name : "+i);
            post.setMessage("Message : "+i);
            list.add(post);
        }
    }



}
