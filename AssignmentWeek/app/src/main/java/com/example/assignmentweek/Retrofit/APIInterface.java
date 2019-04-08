package com.example.assignmentweek.Retrofit;

import com.example.assignmentweek.Request.CreateRequest;
import com.example.assignmentweek.Request.LoginRequest;
import com.example.assignmentweek.Response.CreateResponse;
import com.example.assignmentweek.Response.ListReponse;
import com.example.assignmentweek.Response.LoginResponse;
import com.example.assignmentweek.Response.Register;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("users")
    Call<ListReponse> getAllUsers(@Query("page") int page);

    @POST("register")
    Call<Register> register(@Body LoginRequest request);

    @POST("loginResponse")
    Call<LoginResponse> login(@Body LoginRequest request);

    @POST("users")
    Call<CreateResponse> createUser(@Body CreateRequest request);

    @DELETE("users/{id}")
    Call<Response> deleteUser(@Path("id") long id);

}
