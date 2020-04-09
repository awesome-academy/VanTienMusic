package vn.tien.tienmusic.data.source;

import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import vn.tien.tienmusic.data.model.Song;
import vn.tien.tienmusic.data.service.DataService;
import vn.tien.tienmusic.data.service.NetworkService;

public class SongRemoteData implements SongDataSource.remote {
    private static SongRemoteData sSongRemoteData;
    private DataService mService;

    private SongRemoteData(DataService service) {
        mService = service;
    }

    public static SongRemoteData getInstance(Context context) {
        if (sSongRemoteData == null) {
            sSongRemoteData = new SongRemoteData(NetworkService.getInstance(context));
        }
        return sSongRemoteData;
    }

    @Override
    public Observable<List<Song>> getAllSongs() {
        return mService.getAllSongs();
    }
}
