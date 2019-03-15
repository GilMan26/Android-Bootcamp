package com.example.assignment5.Ques2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment5.R;

public class Fragment2 extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("Lifecycle", "In onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Lifecycle", "In onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("Lifecycle", "In onCreateView");
        return inflater.inflate(R.layout.fragment1_ques2, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("Lifecycle", "In onActivityCreated");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Lifecycle", "In onStart");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Lifecycle", "In onResume");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Lifecycle", "In onPause");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("Lifecycle", "In onStop");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Lifecycle", "In onDestroyView");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Lifecycle", "In onDestroy");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("Lifecycle", "In onDetach");

    }

}
