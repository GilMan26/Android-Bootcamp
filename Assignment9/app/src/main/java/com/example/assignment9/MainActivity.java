package com.example.assignment9;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    Button httpButton, retroButton;
    RecyclerView recyclerView;
    Retrofit retrofit;
    CustomAdapter customAdapter;
    List<Post> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        httpButton=findViewById(R.id.btnhttp);
        retroButton=findViewById(R.id.btnretrofit);
        recyclerView=findViewById(R.id.recyclerView);
        retrofit=RetrofitClient.getRetrofitInstance();
        customAdapter=new CustomAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(customAdapter);
        retroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIInterface apiInterface=retrofit.create(APIInterface.class);

                Call<DummyData> call=apiInterface.getUsers();
                call.enqueue(new Callback<DummyData>() {
                    @Override
                    public void onResponse(Call<DummyData> call, Response<DummyData> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(getApplicationContext(), response.code()+"",Toast.LENGTH_LONG).show();
                        }
                        DummyData data=response.body();
                        Log.d("test", data.toString());
                        list.addAll(data.getPosts());
                        customAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<DummyData> call, Throwable t) {
//                httpButton.setText(t.getMessage());
                        Log.d("fail", "failed");
                    }
                });
            }
        });


        httpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String json="", url="https://storage.googleapis.com/network-security-conf-codelab.appspot.com/v2/posts.json";
                HTTPAsyncTask asyncTask=new HTTPAsyncTask();
                try {
                    json = asyncTask.execute(url).get();
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jsonArray=jsonObject.getJSONArray("posts");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        Post post=new Post();
                        post.setName(jsonObject1.getString("name"));
                        post.setMessage(jsonObject1.getString("message"));
                        post.setProfileImage(jsonObject1.getString("profileImage"));
                        list.add(post);
                        customAdapter.notifyDataSetChanged();

                    }
                    Log.d("test", json);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });



    }
}
