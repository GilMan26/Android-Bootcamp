package com.example.assignmentweek.Retrofit;

import android.content.Context;
import android.util.Log;

import java.net.ConnectException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Response;

public class RetrofitResponseHandler <T> implements retrofit2.Callback<T> {
    private SuccessfulAPICallback successfulAPICallback = null;
    private FailureAPICallback failureAPICallback = null;
    Context context;
    private Call<T> mCall;


    public RetrofitResponseHandler(SuccessfulAPICallback successfulAPICallback, FailureAPICallback failureAPICallback, Context context) {
        this.successfulAPICallback = successfulAPICallback;
        this.failureAPICallback = failureAPICallback;
        this.context = context;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            if (successfulAPICallback != null && response.isSuccessful())
                successfulAPICallback.onResponse(response.body());
        } else {
            if (failureAPICallback != null && response.isSuccessful()) {
                failureAPICallback.onFailure(response.body(), response.body());
            }
        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        String errorMessage = t.toString();
        if (t instanceof UnknownHostException || t instanceof ConnectException) {
            Log.d("test", "in onFailure");
        }
    }
}
