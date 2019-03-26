package com.example.assignment13;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("posts.json")
    Call<Response> getUsers();
}
