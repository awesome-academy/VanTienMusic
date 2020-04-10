package vn.tien.tienmusic.data.model;

import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;

public class User extends BaseObservable {
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
        Glide.with(view.getContext()).load(imagUrl).into(view);
    }

    public void setAvatarUrl(String avatarUrl) {
        mAvatarUrl = avatarUrl;
    }
}
