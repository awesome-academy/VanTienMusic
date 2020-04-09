package vn.tien.tienmusic.data.service;

import android.content.Context;

public class NetworkService {
    private static DataService sDataService;

    public static DataService getInstance(Context context) {
        if (sDataService == null) {
            sDataService = SongClient.getInstance(context).create(DataService.class);
        }
        return sDataService;
    }
}
