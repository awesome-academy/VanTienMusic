package vn.tien.tienmusic.data.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.google.gson.annotations.SerializedName;

public class Song extends BaseObservable {
    @SerializedName("id")
    private long mId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("duration")
    private long mDuration;

    @SerializedName("permalink_url")
    private String mPermalinkUrl;

    @SerializedName("waveform_url")
    private String mWaveFormUrl;

    @SerializedName("user")
    private User mUser;

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
    public long getDuration() {
        return mDuration;
    }

    public void setDuration(long duration) {
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
    public String getWaveFormUrl() {
        return mWaveFormUrl;
    }

    public void setWaveFormUrl(String waveFormUrl) {
        mWaveFormUrl = waveFormUrl;
    }

    @Bindable
    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public Song(long id, String title, long duration, String permalinkUrl, String waveFormUrl, User user) {
        mId = id;
        mTitle = title;
        mDuration = duration;
        mPermalinkUrl = permalinkUrl;
        mWaveFormUrl = waveFormUrl;
        mUser = user;
    }
}
