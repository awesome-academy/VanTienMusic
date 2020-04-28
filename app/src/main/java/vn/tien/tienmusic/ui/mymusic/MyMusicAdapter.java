package vn.tien.tienmusic.ui.mymusic;

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

public class MyMusicAdapter extends RecyclerView.Adapter<MyMusicAdapter.MyMusicHolder> {
    private List<Song> mMySongs;
    private static ClickListenerItem sListenerItem;
    private ItemTrackBinding mTrackBinding;

    public void setData(List<Song> mySongs) {
        mMySongs = mySongs;
        notifyDataSetChanged();
    }

    public void setListenerItem(ClickListenerItem listenerItem) {
        sListenerItem = listenerItem;
    }

    @NonNull
    @Override
    public MyMusicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        mTrackBinding = DataBindingUtil.inflate(layoutInflater,
                R.layout.item_track, parent, false);
        return new MyMusicHolder(mTrackBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyMusicHolder holder, int position) {
        Song song = mMySongs.get(position);
        holder.bind(song);
        mTrackBinding.getRoot().setTag(position);
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
            mMysongBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (sListenerItem != null) {
                        sListenerItem.onClick(
                                mMysongBinding.getSong(),
                                mMysongBinding.getSong().getUser(),
                                position);
                    }
                }
            });
        }

        public void bind(Song song) {
            mMysongBinding.setSong(song);
            mMysongBinding.executePendingBindings();
        }
    }
}
