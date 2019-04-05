package com.example.assignmentweek;

import com.example.assignmentweek.Response.ListReponse;
import com.example.assignmentweek.Response.Register;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("users")
    Call<ListReponse> getAllUsers(@Query("page") int page);

    @POST("register")
    Call<Register> register();

    @POST("login")
    Call<BaseResponseModel> login();


}
