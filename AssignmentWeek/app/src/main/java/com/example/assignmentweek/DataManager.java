package com.example.assignmentweek;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.assignmentweek.Interfaces.IApiReponseListener;
import com.example.assignmentweek.Interfaces.LoginActivityListener;
import com.example.assignmentweek.Request.CreateRequest;
import com.example.assignmentweek.Request.LoginRequest;
import com.example.assignmentweek.Response.CreateResponse;
import com.example.assignmentweek.Response.ListReponse;
import com.example.assignmentweek.Response.LoginResponse;
import com.example.assignmentweek.Response.Register;
import com.example.assignmentweek.Retrofit.APIInterface;
import com.example.assignmentweek.Retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataManager {
    Context context;
    IApiReponseListener reponseListener;
    LoginActivityListener loginListener;

    public DataManager(Context context) {
        this.context = context;
    }

    public void setInstance(IApiReponseListener instance) {
        reponseListener = instance;
    }

    public void setInstance(LoginActivityListener listener) {
        loginListener = listener;
    }

    APIInterface apiInterface = RetrofitClient.getRetrofitInstance().create(APIInterface.class);

    public void getUserList(int page) {
        Call<ListReponse> call = apiInterface.getAllUsers(page);
        call.enqueue(new Callback<ListReponse>() {
            @Override
            public void onResponse(Call<ListReponse> call, Response<ListReponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, response.code() + "", Toast.LENGTH_LONG).show();
                }
                ListReponse listReponse = response.body();
                if (reponseListener != null) {
                    reponseListener.dataListResponse(listReponse.getData());
                }

            }

            @Override
            public void onFailure(Call<ListReponse> call, Throwable t) {

            }
        });
    }

    public void loginUseer(LoginRequest loginRequest) {
        Call<LoginResponse> call = apiInterface.login(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.d("test", "in onrsesponse");
                if (!response.isSuccessful()) {
                    Log.d("test", "response not successf");
                    Toast.makeText(context, response.code() + "", Toast.LENGTH_LONG).show();
                }
                LoginResponse loginResponse = response.body();
                if (loginListener != null)
                    loginListener.login(loginResponse);

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("test", "loginfail");
            }
        });
    }

    public void registerUser(LoginRequest request) {
        Call<Register> call = apiInterface.register(request);
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, response.code() + "", Toast.LENGTH_LONG).show();
                }
                Register registerResponse = response.body();
                if (loginListener != null)
                    loginListener.register(registerResponse);

            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {

            }
        });
    }

    public void addUsser(CreateRequest request) {
        Call<CreateResponse> call = apiInterface.createUser(request);
        call.enqueue(new Callback<CreateResponse>() {
            @Override
            public void onResponse(Call<CreateResponse> call, Response<CreateResponse> response) {
                Log.d("data", "in onresponse");
                if (!response.isSuccessful()) {
                    Toast.makeText(context, response.code() + "", Toast.LENGTH_LONG).show();
                }
                CreateResponse createResponse = response.body();
                if (reponseListener != null)
                    reponseListener.addUsserResponse(createResponse);

            }

            @Override
            public void onFailure(Call<CreateResponse> call, Throwable t) {
                Log.d("data", "failed");
            }
        });
    }

    public void deleteUser(long id) {
        Call<Void> call = apiInterface.deleteUser(id);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful())
                    Toast.makeText(context, "Delete Successful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

        /*call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("delete", response.body().toString());
                if (!response.isSuccessful()) {
                    Toast.makeText(context, response.code() + "", Toast.LENGTH_LONG).show();
                }
                String responseCode = response.body().toString();
                if (reponseListener != null)
                    reponseListener.deleteUserResponse(responseCode);

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });*/
    }
}

