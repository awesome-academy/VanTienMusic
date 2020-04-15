package vn.tien.tienmusic.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import vn.tien.tienmusic.R;
import vn.tien.tienmusic.databinding.ActivityMainBinding;
import vn.tien.tienmusic.ui.favorite.FavoriteFragment;
import vn.tien.tienmusic.ui.personal.PersonalFragment;
import vn.tien.tienmusic.ui.track.TrackFragment;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNavView;
    private ActivityMainBinding mMainBinding;
    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initView();
        setSeclectBotNavView();
    }

    private void initView() {
        mBottomNavView = mMainBinding.botNav;
        loadFragment(new TrackFragment());
        mActionBar = getSupportActionBar();
        mActionBar.setTitle(R.string.title_track);
        mActionBar.setDisplayShowHomeEnabled(true);
        mActionBar.setLogo(R.drawable.ic_music_note_black_24dp);
        mActionBar.setDisplayUseLogoEnabled(true);
    }

    private void setSeclectBotNavView() {
        mBottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.item_track:
                        fragment = new TrackFragment();
                        mActionBar.setTitle(R.string.title_track);
                        loadFragment(fragment);
                        return true;
                    case R.id.item_personal:
                        fragment = new PersonalFragment();
                        mActionBar.setTitle(R.string.title_my_music);
                        loadFragment(fragment);
                        return true;
                    case R.id.item_favorite:
                        fragment = new FavoriteFragment();
                        mActionBar.setTitle(R.string.title_favorite);
                        loadFragment(fragment);
                        return true;
                }
                return false;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

}
