package vn.tien.tienmusic.ui.play;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.tien.tienmusic.R;
import vn.tien.tienmusic.constant.ClickListenerItem;
import vn.tien.tienmusic.constant.Constant;
import vn.tien.tienmusic.data.model.Song;
import vn.tien.tienmusic.data.model.User;
import vn.tien.tienmusic.databinding.FragmentPlaylistBinding;
import vn.tien.tienmusic.viewmodel.SongViewModel;

public class PlayListFragment extends Fragment {
    private FragmentPlaylistBinding mPlaylistBinding;
    private RecyclerView mRecyclerPlaylist;
    private TextView mTextTitle, mTextArtist, mTextGenre;
    private PlayListAdapter mListAdapter;
    private SongViewModel mSongViewModel;
    private Bundle mBundle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPlaylistBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_playlist, container, false);
        initView();
        setUpRecycleView();
        mBundle = new Bundle();
        return mPlaylistBinding.getRoot();
    }

    private void setUpRecycleView() {
        mListAdapter = new PlayListAdapter();
        mSongViewModel = ViewModelProviders.of(getActivity()).get(SongViewModel.class);
        mSongViewModel.initViewModel(getContext());
        mSongViewModel.getSongs().observe(this, new Observer<List<Song>>() {
            @Override
            public void onChanged(List<Song> songs) {
                mListAdapter.setData(songs);
                mBundle.putParcelableArrayList(Constant.BUNDLE_LIST,
                        (ArrayList<? extends Parcelable>) songs);
            }
        });
        mRecyclerPlaylist.setAdapter(mListAdapter);
        mRecyclerPlaylist.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerPlaylist.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        mRecyclerPlaylist.addItemDecoration(dividerItemDecoration);
        mRecyclerPlaylist.setItemViewCacheSize(Constant.CACHE_SIZE);
        mListAdapter.setListenerItem(new ClickListenerItem() {
            @Override
            public void onClick(Song song, User user, int position) {
                Intent intent = PlayMusicActivity.getIntent(getContext());
                mBundle.putParcelable(Constant.BUNDLE_SONG, song);
                mBundle.putParcelable(Constant.BUNDLE_USER, user);
                mBundle.putInt(Constant.POSITION_SONG, position);
                intent.putExtras(mBundle);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
    }

    public void setDatatoView(String title,
                              String artist,
                              String genre,
                              String trackType) {
        mTextTitle.setText(title);
        mTextArtist.setText(artist);
        if (genre != null) {
            mTextGenre.setText(genre);
        }
        if (trackType != null) {
            mTextGenre.setText(trackType);
        }
    }

    private void initView() {
        mRecyclerPlaylist = mPlaylistBinding.recyclePlaylist;
        mTextTitle = mPlaylistBinding.titleSong;
        mTextArtist = mPlaylistBinding.artist;
        mTextGenre = mPlaylistBinding.genre;
    }
}