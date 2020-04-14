package vn.tien.tienmusic.data.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.SerializedName;

public class Song extends BaseObservable {
    @SerializedName("id")
    private long mId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("duration")
    private int mDuration;

    @SerializedName("permalink_url")
    private String mPermalinkUrl;

    @SerializedName("user")
    private User mUser;

    @SerializedName("genre")
    private String mGenre;

    @SerializedName("track_type")
    private String mTrackType;

    public Song() {
    }

    @Bindable
    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    @Bindable
    public String getTitle() {
        return mTitle;

    }

    public void setTitle(String title) {
        mTitle = title;
    }

    @Bindable
    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    @Bindable
    public String getPermalinkUrl() {
        return mPermalinkUrl;
    }

    public void setPermalinkUrl(String permalinkUrl) {
        mPermalinkUrl = permalinkUrl;
    }

    @Bindable
    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public String getGenre() {
        return mGenre;
    }

    public void setGenre(String genre) {
        mGenre = genre;
    }

    public String getTrackType() {
        return mTrackType;
    }

    public void setTrackType(String trackType) {
        mTrackType = trackType;
    }

    public Song(long id, String title, int duration, String permalinkUrl,
                User user, String genre, String trackType) {
        mId = id;
        mTitle = title;
        mDuration = duration;
        mPermalinkUrl = permalinkUrl;
        mUser = user;
        mGenre = genre;
        mTrackType = trackType;
    }
}
