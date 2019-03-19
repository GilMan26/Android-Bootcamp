package com.example.assignment9;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("posts.json")
    Call<DummyData> getUsers();




}
