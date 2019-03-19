
package com.example.assignment9;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Post {

    @SerializedName("message")
    private String mMessage;
    @SerializedName("name")
    private String mName;
    @SerializedName("profileImage")
    private String mProfileImage;

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getProfileImage() {
        return mProfileImage;
    }

    public void setProfileImage(String profileImage) {
        mProfileImage = profileImage;
    }

}
