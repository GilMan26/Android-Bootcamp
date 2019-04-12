
package com.example.assignmentweek.Response;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.assignmentweek.Constants.Contract;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
@Entity(tableName = Contract.TABLE_NAME)
public class Datum {

    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    private Long mId;
    @SerializedName("avatar")
    private String mAvatar;
    @SerializedName("first_name")
    private String mFirstName;
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
