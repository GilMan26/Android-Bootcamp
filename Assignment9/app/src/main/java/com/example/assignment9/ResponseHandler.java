package com.example.assignment9;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ResponseHandler<T extends Post> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

    }
}
