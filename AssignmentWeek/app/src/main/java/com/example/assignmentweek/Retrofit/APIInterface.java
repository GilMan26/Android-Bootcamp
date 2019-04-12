package com.example.assignmentweek.Retrofit;

import com.example.assignmentweek.Request.UserRequest;
import com.example.assignmentweek.Request.SignRequest;
import com.example.assignmentweek.Response.CreateResponse;
import com.example.assignmentweek.Response.ListReponse;
import com.example.assignmentweek.Response.LoginResponse;
import com.example.assignmentweek.Response.Register;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("users")
    Call<ListReponse> getAllUsers(@Query("page") int page);

    @POST("register")
    Call<Register> register(@Body SignRequest request);

    @POST("loginResponse")
    Call<LoginResponse> login(@Body SignRequest request);

    @POST("users")
    Call<CreateResponse> createUser(@Body UserRequest request);

    @DELETE("users/{id}")
    Call<Void> deleteUser(@Path("id") long id);

    @PUT("users/{id}")
    Call<CreateResponse> updateUser(@Path("id") long id, @Body UserRequest userRequest);

}
