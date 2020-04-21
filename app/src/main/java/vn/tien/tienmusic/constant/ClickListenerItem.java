package vn.tien.tienmusic.constant;

import vn.tien.tienmusic.data.model.Song;
import vn.tien.tienmusic.data.model.User;

public interface ClickListenerItem {
    void onClick(Song song,User user);
}
