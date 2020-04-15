package vn.tien.tienmusic.ui.play;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import vn.tien.tienmusic.R;
import vn.tien.tienmusic.databinding.FragmentPlaylistBinding;

public class PlayListFragment extends Fragment {
    private FragmentPlaylistBinding mPlaylistBinding;
    private RecyclerView mRecyclerPlaylist;
    private TextView mTextTitle, mTextArtist, mTextGenre;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPlaylistBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_playlist, container, false);
        initView();
        return mPlaylistBinding.getRoot();
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
