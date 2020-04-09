package vn.tien.tienmusic.viewmodel;

import android.content.Context;
import android.widget.Toast;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import vn.tien.tienmusic.data.model.Song;
import vn.tien.tienmusic.data.repository.SongRepository;
import vn.tien.tienmusic.data.source.SongRemoteData;

public class SongViewModel extends ViewModel {
    private MutableLiveData<List<Song>> mData;
    private CompositeDisposable mCompositeDisposable;
    private SongRepository mRepository;
    private Context mContext;
    private ObservableList<Song> mSongObservableList = new ObservableArrayList<>();

    public void initViewModel(Context context) {
        mCompositeDisposable = new CompositeDisposable();
        mRepository = SongRepository.getInstance(SongRemoteData.getInstance(mContext));
    }

    public LiveData<List<Song>> getSongs() {
        if (mData == null) {
            mData = new MutableLiveData<List<Song>>();
            loadSongs();
        }
        return mData;
    }

    private void loadSongs() {
        Disposable disposable = mRepository.getAllSongs()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(response -> mSongObservableList.addAll(response),
                        error -> handleError(error));
        mCompositeDisposable.add(disposable);
    }

    private void handleError(Throwable error) {
        Toast.makeText(mContext, error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mCompositeDisposable.dispose();
    }
}
