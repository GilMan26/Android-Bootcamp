
package com.example.assignmentweek.Response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Datum {

    @SerializedName("avatar")
    private String mAvatar;
    @SerializedName("first_name")
    private String mFirstName;
    @SerializedName("id")
    private Long mId;
    @SerializedName("last_name")
    private String mLastName;

    public Datum(String mAvatar, String mFirstName, Long mId, String mLastName) {
        this.mAvatar = mAvatar;
        this.mFirstName = mFirstName;
        this.mId = mId;
        this.mLastName = mLastName;
    }

    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String avatar) {
        mAvatar = avatar;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

}
