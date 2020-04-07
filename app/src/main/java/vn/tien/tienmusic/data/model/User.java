package vn.tien.tienmusic.data.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private int mId;

    @SerializedName("username")
    private String mUserName;

    @SerializedName("avatar_url")
    private String mAvatarUrl;

    public User(int id, String userName, String avatarUrl) {
        mId = id;
        mUserName = userName;
        mAvatarUrl = avatarUrl;
    }

    public User() {
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        mAvatarUrl = avatarUrl;
    }
}
