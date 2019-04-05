
package com.example.assignmentweek.Response;

import com.example.assignmentweek.BaseResponseModel;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Register extends BaseResponseModel {

    @SerializedName("token")
    private String mToken;

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
    }

}
