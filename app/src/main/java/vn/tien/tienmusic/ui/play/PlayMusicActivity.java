package vn.tien.tienmusic.ui.play;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.Menu;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import vn.tien.tienmusic.R;
import vn.tien.tienmusic.Utils.StringUtils;
import vn.tien.tienmusic.constant.Constant;
import vn.tien.tienmusic.data.model.Song;
import vn.tien.tienmusic.data.model.User;
import vn.tien.tienmusic.databinding.ActivityPlayBinding;
import vn.tien.tienmusic.service.PlayMusicService;

public class PlayMusicActivity extends AppCompatActivity {
    private ActivityPlayBinding mBinding;
    private Toolbar mToolbar;
    private TextView mTextDuration, mTextTime;
    private Song mSong;
    private User mUser;
    private static ViewPagerPlayScreen sPagerPlayScreen;
    private AvatarFragment mAvatarFragment;
    private PlayListFragment mPlayListFragment;
    private ViewPager mViewPager;
    private ServiceConnection mServiceConnection;
    private Boolean mBound;
    private PlayMusicService mMusicService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_play);
        initView();
        getData();
        setToolbar();
        customViewPager();
        setDatatoViewPager();
        bindtoService();
    }

    private void bindtoService() {
        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                PlayMusicService.MusicBinder musicBinder = (PlayMusicService.MusicBinder) iBinder;
                mMusicService = musicBinder.getService();
                mBound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                mBound = false;
            }
        };
        Intent intent = new Intent(this, PlayMusicService.class);
        bindService(intent, mServiceConnection, Service.BIND_AUTO_CREATE);
    }

    private void setDatatoViewPager() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sPagerPlayScreen.getItem(0) != null) {
                    mAvatarFragment.setAvatar(mUser.getAvatarUrl());
                    handler.removeCallbacks(this);
                }
                if (sPagerPlayScreen.getItem(1) != null) {
                    mPlayListFragment.setDatatoView(
                            mSong.getTitle(),
                            mUser.getUserName(),
                            mSong.getGenre(),
                            mSong.getTrackType());
                    handler.removeCallbacks(this);
                }
            }
        }, Constant.EVENT_DELAY);
    }

    private void customViewPager() {
        mAvatarFragment = new AvatarFragment();
        mPlayListFragment = new PlayListFragment();
        sPagerPlayScreen = new ViewPagerPlayScreen(getSupportFragmentManager(), 2);
        sPagerPlayScreen.addFragment(mAvatarFragment);
        sPagerPlayScreen.addFragment(mPlayListFragment);
        mViewPager.setAdapter(sPagerPlayScreen);
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp);
    }

    private void getData() {
        Bundle bundle = getIntent().getExtras();
        mSong = bundle.getParcelable(Constant.BUNDLE_SONG);
        mUser = bundle.getParcelable(Constant.BUNDLE_USER);
        mToolbar.setTitle(mSong.getTitle());
        String time = StringUtils.formatDuration(mSong.getDuration());
        mTextDuration.setText(time);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.play_music_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initView() {
        mToolbar = mBinding.toolBarPlay;
        mTextDuration = mBinding.textDuration;
        mViewPager = mBinding.viewPagerPlay;
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, PlayMusicActivity.class);
        return intent;
    }

    @Override
    protected void onDestroy() {
        unbindService(mServiceConnection);
        mBound = false;
        super.onDestroy();
    }
}
