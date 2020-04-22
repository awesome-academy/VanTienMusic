package vn.tien.tienmusic.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;

import vn.tien.tienmusic.R;

public class User extends BaseObservable implements Parcelable {
    @SerializedName("id")
    private long mId;

    @SerializedName("username")
    private String mUserName;

    @SerializedName("avatar_url")
    private String mAvatarUrl;

    public User(long id, String userName, String avatarUrl) {
        mId = id;
        mUserName = userName;
        mAvatarUrl = avatarUrl;
    }

    public User(String userName) {
        mUserName = userName;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    @Bindable
    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    @Bindable
    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    @BindingAdapter({"mAvatarUrl"})
    public static void loadImage(ImageView view, String imagUrl) {
        Glide.with(view.getContext()).load(imagUrl).placeholder(R.drawable.cd).into(view);
    }

    public void setAvatarUrl(String avatarUrl) {
        mAvatarUrl = avatarUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mAvatarUrl);
        parcel.writeString(mUserName);
        parcel.writeLong(mId);
    }

    private User(Parcel in) {
        mAvatarUrl = in.readString();
        mUserName = in.readString();
        mId = in.readLong();
    }
}
