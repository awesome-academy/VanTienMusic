package vn.tien.tienmusic.ui.track;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.tien.tienmusic.R;
import vn.tien.tienmusic.data.model.Song;
import vn.tien.tienmusic.databinding.FragmentTrackBinding;
import vn.tien.tienmusic.viewmodel.SongViewModel;

public class TrackFragment extends Fragment {
    private RecyclerView mRecyclerTrack;
    private TrackAdapter mTrackAdapter;
    private FragmentTrackBinding mBinding;
    private SongViewModel mSongViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_track, container, false);
        mBinding.setBinding(this);
        setUpRecycler();
        return mBinding.getRoot();
    }


    private void setUpRecycler() {
        mRecyclerTrack = mBinding.recycleSongs;
        mTrackAdapter = new TrackAdapter();
        mSongViewModel = ViewModelProviders.of(getActivity()).get(SongViewModel.class);
        mSongViewModel.initViewModel(getContext());
        mSongViewModel.getSongs().observe(this, new Observer<List<Song>>() {
            @Override
            public void onChanged(List<Song> songs) {
                mTrackAdapter.setData(songs);
            }
        });
        mRecyclerTrack.setAdapter(mTrackAdapter);
        mRecyclerTrack.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}
