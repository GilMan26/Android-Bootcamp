package com.example.assignment3.Ques2;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.Toast;

import com.example.assignment3.R;

import java.util.ArrayList;
import java.util.List;

public class PaginationActivity extends AppCompatActivity {
    List<String> paginList = new ArrayList<>();
    PaginationAdapter adapter;
    private boolean isScrolling = false;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagination);

        RecyclerView pagingRecycler = findViewById(R.id.pagingRecycler);
        linearLayoutManager = new LinearLayoutManager(this);
        pagingRecycler.setLayoutManager(linearLayoutManager);

        initializeData(paginList);

        adapter = new PaginationAdapter(paginList);
        pagingRecycler.setAdapter(adapter);

//        adapter.notifyDataSetChanged();

        pagingRecycler.setOnScrollListener(scrollListener);

    }

    private RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                Log.d("isScrolling", "Hello there");
                isScrolling = true;

            }

        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int totalItems = linearLayoutManager.getItemCount();
            int visibleItems = linearLayoutManager.getChildCount();
            int scrolledOutItems = linearLayoutManager.findFirstVisibleItemPosition();
            Log.d("shit", totalItems+""+ visibleItems+""+ scrolledOutItems);
            if (isScrolling && (visibleItems + scrolledOutItems) == totalItems) {
                Log.d("test", "im here");
                Toast.makeText(PaginationActivity.this, "loading more...", Toast.LENGTH_SHORT).show();
                isScrolling = false;
                createData();
                adapter.notifyDataSetChanged();
                Log.d("test2", "still here");
            }
        }
    };


    private void initializeData(List<String> paginList) {
        for (int i = 0; i < 20; i++) {
            paginList.add(Math.floor(Math.random() * 100) + "");
        }
    }


    public void createData() {
//        for(int i=0;i<20;i++){
//            paginList.add("Lucky Number : "+ Math.floor(Math.random()*100));
//        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    paginList.add(Math.floor(Math.random() * 100) + "");
                }
            }
        }, 2000);
    }
}
