package vn.tien.tienmusic.ui.play;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import java.text.SimpleDateFormat;
import java.util.Date;

import vn.tien.tienmusic.R;
import vn.tien.tienmusic.constant.Constant;
import vn.tien.tienmusic.databinding.ActivityPlayBinding;

public class PlayMusicActivity extends AppCompatActivity {
    private ActivityPlayBinding mBinding;
    private Toolbar mToolbar;
    private TextView mTextDuration, mTextTime;
    private Bundle mBundle;
    private String title, artist, genre, trackType, link, avatarLink;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_play);
        initView();
        getData();
        setToolbar();
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp);
    }




    private void getData() {
        mBundle = getIntent().getExtras();
        title = mBundle.getString(Constant.KEY_TITLE);
        artist = mBundle.getString(Constant.KEY_ARTIST);
        link = mBundle.getString(Constant.KEY_LINK);
        int duration = mBundle.getInt(Constant.KEY_DURATION);
        avatarLink = mBundle.getString(Constant.KEY_AVATAR);
        genre = mBundle.getString(Constant.KEY_GENRE);
        trackType = mBundle.getString(Constant.KEY_TRACKTYPE);
        mToolbar.setTitle(title);
        String time = new SimpleDateFormat(Constant.FORMAT_DATE).format(new Date(duration));
        mTextDuration.setText(time);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initView() {
        mToolbar = mBinding.toolBarPlay;
        mTextDuration = mBinding.textDuration;
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, PlayMusicActivity.class);
        return intent;
    }
}
