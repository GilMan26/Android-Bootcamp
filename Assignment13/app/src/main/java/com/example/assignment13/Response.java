
package com.example.assignment13;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Response {

    @SerializedName("posts")
    private List<Post> mPosts;

    public List<Post> getPosts() {
        return mPosts;
    }

    public void setPosts(List<Post> posts) {
        mPosts = posts;
    }

}
