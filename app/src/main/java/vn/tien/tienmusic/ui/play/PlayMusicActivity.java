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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import vn.tien.tienmusic.R;
import vn.tien.tienmusic.constant.Constant;
import vn.tien.tienmusic.constant.MediaPlayerState;
import vn.tien.tienmusic.data.model.Song;
import vn.tien.tienmusic.data.model.User;
import vn.tien.tienmusic.databinding.ActivityPlayBinding;
import vn.tien.tienmusic.service.PlayMusicService;
import vn.tien.tienmusic.utils.StringUtils;

public class PlayMusicActivity extends AppCompatActivity implements View.OnClickListener {
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
    private FloatingActionButton mBtnPlay;
    private ImageView mBtnNext, mBtnPre, mBtnRandom, mBtnRepeat;
    public static ArrayList<Song> mSongs = new ArrayList<>();
    public int mPosSong;

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
        upDateTime();

    }

    private void initView() {
        mToolbar = mBinding.toolBarPlay;
        mTextDuration = mBinding.textDuration;
        mViewPager = mBinding.viewPagerPlay;
        mBtnPlay = mBinding.btnPlay;
        mBtnPlay.setOnClickListener(this);
        mBtnNext = mBinding.imageNext;
        mBtnNext.setOnClickListener(this);
        mBtnPre = mBinding.imagePre;
        mBtnPre.setOnClickListener(this);
        mTextTime = mBinding.textTime;
        mBtnRandom = mBinding.imageRandom;
        mBtnRandom.setOnClickListener(this);
        mBtnRepeat = mBinding.imageRepeat;
        mBtnRepeat.setOnClickListener(this);
    }

    private void getData() {
        Bundle bundle = getIntent().getExtras();
        mSongs = bundle.getParcelableArrayList(Constant.BUNDLE_LIST);
        mPosSong = bundle.getInt(Constant.POSITION_SONG);
        mSong = bundle.getParcelable(Constant.BUNDLE_SONG);
        mUser = bundle.getParcelable(Constant.BUNDLE_USER);
        mToolbar.setTitle(mSong.getTitle());
        String time = StringUtils.formatDuration(mSong.getDuration());
        mTextDuration.setText(time);
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp);
    }

    private void customViewPager() {
        mAvatarFragment = new AvatarFragment();
        mPlayListFragment = new PlayListFragment();
        sPagerPlayScreen = new ViewPagerPlayScreen(getSupportFragmentManager(), 2);
        sPagerPlayScreen.addFragment(mAvatarFragment);
        sPagerPlayScreen.addFragment(mPlayListFragment);
        mViewPager.setAdapter(sPagerPlayScreen);
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

    private void bindtoService() {
        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                PlayMusicService.MusicBinder musicBinder = (PlayMusicService.MusicBinder) iBinder;
                mMusicService = musicBinder.getService();
                mBound = true;
                mMusicService.setSongs(mSongs);
                mMusicService.setCurrentIndex(mPosSong);
                playOrPause();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                mBound = false;
            }
        };
        Intent intent = new Intent(this, PlayMusicService.class);
        bindService(intent, mServiceConnection, Service.BIND_AUTO_CREATE);
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

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, PlayMusicActivity.class);
        return intent;
    }

    private void upDateTime() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        }, Constant.EVENT_DELAY);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_play:
                playOrPause();
                break;
            case R.id.image_next:
                nextSong();
                break;
            case R.id.image_pre:
                preSong();
                break;
            case R.id.image_random:
                randomSong();
                break;
            case R.id.image_repeat:
                repeatSong();
                break;
            default:
                break;
        }
    }

    private void repeatSong() {
        mMusicService.repeatSong();
        if (mMusicService.isRepeat() == true) {
            mBtnRepeat.setImageResource(R.drawable.ic_loop_accent_24dp);
        } else {
            mBtnRepeat.setImageResource(R.drawable.ic_loop_black_24dp);
        }
    }

    private void randomSong() {
        mMusicService.randomSong();
        if (mMusicService.isRandom() == true) {
            mBtnRandom.setImageResource(R.drawable.ic_shuffle_accent_24dp);
        } else {
            mBtnRandom.setImageResource(R.drawable.ic_shuffle_black_24dp);
        }
    }

    private void preSong() {
        if (!mBound) {
            return;
        }
        mMusicService.preSong();
    }

    private void nextSong() {
        if (!mBound) {
            return;
        }
        mMusicService.nextSong();
    }

    private void playOrPause() {
        if (!mBound) {
            return;
        }
        if (mMusicService.getState() == MediaPlayerState.PLAYING) {
            mBtnPlay.setImageLevel(Constant.IMAGE_LEVEL_PLAY);
        } else
            mBtnPlay.setImageLevel(Constant.IMAGE_LEVEL_PAUSE);
        mMusicService.playSong();
    }

    @Override
    protected void onDestroy() {
        unbindService(mServiceConnection);
        mBound = false;
        super.onDestroy();
    }
}
