package com.example.assignmentweek;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.assignmentweek.Response.Datum;
import com.example.assignmentweek.Response.ListReponse;
import com.example.assignmentweek.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private static int page = 1;
    ActivityMainBinding binding;
    CustomAdapter adapter;
    List<Datum> list;
    LinearLayoutManager linearLayoutManager;
    Retrofit retrofit;
    private boolean isScrolling = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        linearLayoutManager = new LinearLayoutManager(this);
        list=new ArrayList<>();
        adapter=new CustomAdapter(list);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(adapter);
        retrofit = RetrofitClient.getRetrofitInstance();
        callApi();
        binding.recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
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
                Log.d("shit", totalItems + "" + visibleItems + "" + scrolledOutItems);
                if (isScrolling && (visibleItems + scrolledOutItems) == totalItems) {
                    Log.d("test", "im here");
                    if(page<4){
                        page++;
                        Toast.makeText(MainActivity.this, "loading more...", Toast.LENGTH_SHORT).show();
                        isScrolling = false;
                        callApi();
//                adapter.notifyDataSetChanged();
                        Log.d("test2", "still here");
                    }

                }
            }
        });
    }


//            @Override
//            public void onResponse(Call<DummyData> call, Response<DummyData> response) {
//                if(!response.isSuccessful()){
//                    Toast.makeText(getApplicationContext(), response.code()+"",Toast.LENGTH_LONG).show();
//                }
//                DummyData data=response.body();
//                Log.d("test", data.toString());
//                list.addAll(data.getPosts());
//                customAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<DummyData> call, Throwable t) {
////                httpButton.setText(t.getMessage());
//                Log.d("fail", "failed");
//            }
//        });

        public void callApi() {
            APIInterface apiInterface = retrofit.create(APIInterface.class);
            Call<ListReponse> call = apiInterface.getAllUsers(page);
            call.enqueue(new Callback<ListReponse>() {
                @Override
                public void onResponse(Call<ListReponse> call, Response<ListReponse> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), response.code() + "", Toast.LENGTH_LONG).show();
                    }
                    ListReponse listReponse = response.body();
                    list.addAll(listReponse.getData());
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<ListReponse> call, Throwable t) {

                }

            });
        }


    }
