package vn.tien.tienmusic.data.model;

import com.google.gson.annotations.SerializedName;

public class Song {
    @SerializedName("id")
    private int mId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("duration")
    private int mDuration;

    @SerializedName("permalink_url")
    private String mPermalinkUrl;

    @SerializedName("user_id")
    private int mUserId;

    @SerializedName("user")
    private User mUser;

    public Song(int id, String title, int duration, String permalinkUrl, int userId, User user) {
        mId = id;
        mTitle = title;
        mDuration = duration;
        mPermalinkUrl = permalinkUrl;
        mUserId = userId;
        mUser = user;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    public Song() {
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    public String getPermalinkUrl() {
        return mPermalinkUrl;
    }

    public void setPermalinkUrl(String permalinkUrl) {
        mPermalinkUrl = permalinkUrl;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }
}
