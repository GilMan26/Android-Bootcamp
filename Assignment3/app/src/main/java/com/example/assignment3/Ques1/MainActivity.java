package com.example.assignment3.Ques1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.assignment3.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<RowPojo> list=new ArrayList<>();
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initData();
        adapter=new CustomAdapter(list);
        recyclerView.setAdapter(adapter);

    }


    public void initData(){
        for(int i=0;i<100;i++){
            RowPojo element=new RowPojo();
            element.setName("Ice Cream Sundae");
            element.setDesc("Chocolates and Brownies");
            element.setPrice("Rs 350");
            element.setDate("Some Date");
            element.setReview("500 reviews");
            element.setRating(4.1f);
            list.add(element)  ;
        }

    }
}
