package vn.tien.tienmusic.ui.mymusic;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.tien.tienmusic.R;
import vn.tien.tienmusic.data.model.Song;
import vn.tien.tienmusic.databinding.ItemTrackBinding;

public class MyMusicAdapter extends RecyclerView.Adapter<MyMusicAdapter.MyMusicHolder> {
    private List<Song> mMySongs;

    public void setData(List<Song> mySongs) {
        mMySongs = mySongs;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyMusicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemTrackBinding binding = DataBindingUtil.inflate(layoutInflater,
                R.layout.item_track, parent, false);
        return new MyMusicHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyMusicHolder holder, int position) {
        Song song = mMySongs.get(position);
        holder.bind(song);
    }

    @Override
    public int getItemCount() {
        return mMySongs == null ? 0 : mMySongs.size();
    }


    public class MyMusicHolder extends RecyclerView.ViewHolder {
        private ItemTrackBinding mMysongBinding;

        public MyMusicHolder(final ItemTrackBinding binding) {
            super(binding.getRoot());
            mMysongBinding = binding;
        }

        public void bind(Song song) {
            mMysongBinding.setSong(song);
            mMysongBinding.executePendingBindings();
        }
    }
}
