package vn.tien.tienmusic.ui.play;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.tien.tienmusic.R;
import vn.tien.tienmusic.constant.ClickListenerItem;
import vn.tien.tienmusic.data.model.Song;
import vn.tien.tienmusic.databinding.ItemTrackBinding;

public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.PlayListHolder> {
    private List<Song> mSongs;
    private static ClickListenerItem sListenerItem;

    public void setData(List<Song> songs) {
        mSongs = songs;
        notifyDataSetChanged();
    }

    public void setListenerItem(ClickListenerItem listenerItem) {
        sListenerItem = listenerItem;
    }

    @NonNull
    @Override
    public PlayListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemTrackBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.item_track, parent, false);
        return new PlayListHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayListHolder holder, int position) {
        Song song = mSongs.get(position);
        holder.bind(song);
    }

    @Override
    public int getItemCount() {
        return mSongs == null ? 0 : mSongs.size();
    }

    class PlayListHolder extends RecyclerView.ViewHolder {
        private ItemTrackBinding mTrackBinding;

        public PlayListHolder(@NonNull ItemTrackBinding binding) {
            super(binding.getRoot());
            mTrackBinding = binding;
            mTrackBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (sListenerItem != null) {
                        sListenerItem.onClick(mTrackBinding.getSong(),
                                mTrackBinding.getSong().getUser());
                    }
                }
            });
        }

        void bind(Song song) {
            mTrackBinding.setSong(song);
            mTrackBinding.executePendingBindings();
        }
    }
}
