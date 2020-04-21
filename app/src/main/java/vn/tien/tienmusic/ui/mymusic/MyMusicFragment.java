package vn.tien.tienmusic.ui.mymusic;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import vn.tien.tienmusic.R;
import vn.tien.tienmusic.constant.Constant;
import vn.tien.tienmusic.data.model.Song;
import vn.tien.tienmusic.data.model.User;
import vn.tien.tienmusic.databinding.FragmentMymusicBinding;

public class MyMusicFragment extends Fragment {
    private List<Song> mMyMusics = new ArrayList<>();
    private User mUser;
    private FragmentMymusicBinding mMymusicBinding;
    private MyMusicAdapter mMusicAdapter;
    private RecyclerView mRecyclerMusics;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mMymusicBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_mymusic, container, false);
        getSongs();
        initView();
        setRecycleView();
        return mMymusicBinding.getRoot();
    }

    private void setRecycleView() {
        mRecyclerMusics.setAdapter(mMusicAdapter);
        mRecyclerMusics.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerMusics.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        mRecyclerMusics.addItemDecoration(dividerItemDecoration);
        mRecyclerMusics.setItemViewCacheSize(Constant.CACHE_SIZE);
        Collections.sort(mMyMusics, new Comparator<Song>() {
            @Override
            public int compare(Song myMusic, Song t1) {
                return myMusic.getTitle().compareTo(t1.getTitle());
            }
        });
    }

    private void initView() {
        mRecyclerMusics = mMymusicBinding.recycleMyMusic;
        mMusicAdapter = new MyMusicAdapter();
        mMusicAdapter.setData(mMyMusics);
    }

    private void getSongs() {
        ContentResolver resolver = getActivity().getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = resolver.query(musicUri,
                null,
                null,
                null,
                null);

        if (cursor != null && cursor.moveToFirst()) {
            int titleColumn = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int idColumn = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int artistColumn = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int durationColumn = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
            do {
                Long id = cursor.getLong(idColumn);
                String title = cursor.getString(titleColumn);
                String artist = cursor.getString(artistColumn);
                int duration = cursor.getInt(durationColumn);
                mUser = new User(artist);
                mMyMusics.add(new Song(id, title, duration, mUser));
            }
            while (cursor.moveToNext());
        }
    }
}
