package vn.tien.tienmusic.data.service;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import vn.tien.tienmusic.BuildConfig;
import vn.tien.tienmusic.data.model.Song;

public interface DataService {
    @GET("/tracks?client_id=" + BuildConfig.API_KEY)
    Observable<List<Song>> getAllSongs();
}
