package com.example.assignmentweek.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.assignmentweek.APIInterface;
import com.example.assignmentweek.FailureAPICallback;
import com.example.assignmentweek.R;
import com.example.assignmentweek.Response.Register;
import com.example.assignmentweek.RetrofitClient;
import com.example.assignmentweek.RetrofitResponseHandler;
import com.example.assignmentweek.SuccessfulAPICallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginFragment extends Fragment {
    EditText usernameET, passwordET;
    Button loginBtn, registerBtn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        usernameET=view.findViewById(R.id.usernameET);
        passwordET=view.findViewById(R.id.passwordET);
        loginBtn=view.findViewById(R.id.loginbtn);
        registerBtn=view.findViewById(R.id.registerbtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIInterface apiInterface= RetrofitClient.getRetrofitInstance().create(APIInterface.class);
                Call<Register> call= apiInterface.register();
                call.enqueue(new Callback<Register>() {
                    @Override
                    public void onResponse(Call<Register> call, Response<Register> response) {

                    }

                    @Override
                    public void onFailure(Call<Register> call, Throwable t) {

                    }
                });
                call.enqueue(new RetrofitResponseHandler<Register>(new SuccessfulAPICallback() {
                    @Override
                    public void onResponse(Object o) {

                    }
                }, new FailureAPICallback() {
                    @Override
                    public void onFailure(Object errorCode, Object errorMessage) {

                    }
                }, v.getContext()));
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
