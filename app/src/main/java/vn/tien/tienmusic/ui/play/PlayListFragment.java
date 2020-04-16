package vn.tien.tienmusic.ui.play;

import android.content.Intent;
import android.os.Bundle;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPlaylistBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_playlist, container, false);
        initView();
        setUpRecycleView();
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
                mListAdapter.notifyDataSetChanged();
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
            public void onClick(Song song, User user) {
                Intent intent = PlayMusicActivity.getIntent(getContext());
                Bundle bundle = new Bundle();
                bundle.putParcelable(Constant.BUNDLE_SONG, song);
                bundle.putParcelable(Constant.BUNDLE_USER, user);
                intent.putExtras(bundle);
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
