package vn.tien.tienmusic.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class PlayMusicService extends Service {
    private IBinder mIBinder;

    @Override
    public void onCreate() {
        super.onCreate();
        mIBinder = new MusicBinder();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mIBinder;
    }

    public class MusicBinder extends Binder {
        public PlayMusicService getService() {
            return PlayMusicService.this;
        }
    }

}
