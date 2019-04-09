
package com.example.assignmentweek.Request;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class UserRequest {

    @SerializedName("job")
    private String mJob;
    @SerializedName("name")
    private String mName;

    public UserRequest(String mJob, String mName) {
        this.mJob = mJob;
        this.mName = mName;
    }

    public String getJob() {
        return mJob;
    }

    public void setJob(String job) {
        mJob = job;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

}
