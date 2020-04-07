package vn.tien.tienmusic.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import vn.tien.tienmusic.R;
import vn.tien.tienmusic.data.model.Song;
import vn.tien.tienmusic.data.service.DataService;
import vn.tien.tienmusic.data.service.SongClient;

public class MainActivity extends AppCompatActivity {
    private CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCompositeDisposable = new CompositeDisposable();
        DataService service = SongClient.getInstance().create(DataService.class);

        Disposable disposable = service.getAllSongs()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(response -> handleResponse(response), error -> handleError(error));
        mCompositeDisposable.add(disposable);
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    private void handleError(Throwable error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }


    private void handleResponse(List<Song> songs) {
        Toast.makeText(this, String.valueOf(songs.size()), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }
}
