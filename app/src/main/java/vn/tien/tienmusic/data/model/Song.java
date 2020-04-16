package vn.tien.tienmusic.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.SerializedName;

public class Song extends BaseObservable implements Parcelable {
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

    public Song(long id, String title, int duration, String permalinkUrl, User user, String genre, String trackType) {
        mId = id;
        mTitle = title;
        mDuration = duration;
        mPermalinkUrl = permalinkUrl;
        mUser = user;
        mGenre = genre;
        mTrackType = trackType;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTitle);
        parcel.writeString(mGenre);
        parcel.writeString(mPermalinkUrl);
        parcel.writeString(mTrackType);
        parcel.writeInt(mDuration);
    }

    public static final Parcelable.Creator<Song> CREATOR = new Parcelable.Creator<Song>() {

        @Override
        public Song createFromParcel(Parcel parcel) {
            return new Song(parcel);
        }

        @Override
        public Song[] newArray(int i) {
            return new Song[i];
        }
    };

    //thu tu doc va viet phai giong nhau
    private Song(Parcel in) {
        mTitle = in.readString();
        mGenre = in.readString();
        mPermalinkUrl = in.readString();
        mTrackType = in.readString();
        mDuration = in.readInt();
    }

}
