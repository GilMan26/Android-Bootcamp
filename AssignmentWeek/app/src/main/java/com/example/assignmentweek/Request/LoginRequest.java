
package com.example.assignmentweek.Request;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class LoginRequest {

    @SerializedName("email")
    private String mEmail;
    @SerializedName("password")
    private String mPassword;

    public LoginRequest(String mEmail, String mPassword) {
        this.mEmail = mEmail;
        this.mPassword = mPassword;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

}
