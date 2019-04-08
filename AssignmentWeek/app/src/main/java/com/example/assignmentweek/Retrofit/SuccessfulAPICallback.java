package com.example.assignmentweek.Retrofit;

public interface SuccessfulAPICallback<T> {
    void onResponse(T t);
}
