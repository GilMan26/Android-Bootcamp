
package com.example.assignment13;

import android.content.Context;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Post {

    @SerializedName("message")
    private String mMessage;
    @SerializedName("name")
    private String mName;
//    @SerializedName("profileImage")
//    private String mProfileImage;

    public Post(String mMessage, String mName) {
        this.mMessage = mMessage;
        this.mName = mName;
//        this.mProfileImage="http://storage.googleapis.com/network-security-conf-codelab.appspot.com/images/2.png";
    }

//    public Post() {
//        this.mProfileImage="http://storage.googleapis.com/network-security-conf-codelab.appspot.com/images/2.png";
//
//    }

//    @BindingAdapter("profileImage")
//    public static void loadImage(ImageView view, String imageUrl){
//        Context context = view.getContext();
//        if (context != null) {

    public Post() {
    }
//            Glide.with(context)
//                    .load(imageUrl)
//                    .into(view);
//        }
//    }


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

//    public String getProfileImage() {
//        return mProfileImage;
//    }

//    public void setProfileImage(String profileImage) {
//        mProfileImage = profileImage;
//    }

}
