package com.example.assignment3.Ques3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.example.assignment3.R;

import java.util.ArrayList;
import java.util.List;

public class MultipleViewActivity extends AppCompatActivity {
    MultipleViewAdapter multipleViewAdapter;
    List<Model> list = new ArrayList<>();

    private final static int IMAGE_VIEW = 1;
    private final static int TEXT_VIEW = 0;
    private final static int TEXT_IMAGE_VIEW = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_view);
        RecyclerView recyclerView = findViewById(R.id.multipleViewRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initData();
        multipleViewAdapter = new MultipleViewAdapter(list);
        recyclerView.setAdapter(multipleViewAdapter);
    }

    public void initData() {

        for (int i = 0; i < 10; i++) {
            Model model = new Model("Number" + i, R.drawable.sundae);
            model.setType(TEXT_IMAGE_VIEW);
            list.add(model);
        }

        for (int i = 0; i < 10; i++) {
            Model imageModel = new Model(R.drawable.sundae);
            imageModel.setType(IMAGE_VIEW);
            list.add(imageModel);
        }

        for (int i = 1; i <= 10; i++) {
            Model textMddel = new Model("Number" + i);
            textMddel.setType(TEXT_VIEW);
            list.add(textMddel);
        }

    }


}
