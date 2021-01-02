package com.naman14.timber.cast;

import android.net.Uri;
import android.util.Log;

import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.images.WebImage;
import com.naman14.timber.models.Song;
import com.naman14.timber.utils.Constants;
import com.naman14.timber.utils.TimberUtils;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by naman on 2/12/17.
 */

public class TimberCastHelper  {

    public static void startCasting(CastSession castSession, Song song) {

        String ipAddress = TimberUtils.getIPAddress(true);//get ip adress
        URL baseUrl;//URL
        try {
            baseUrl = new URL("http", ipAddress, Constants.CAST_SERVER_PORT, "" );//得到URL
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return;
        }

        String songUrl = baseUrl.toString() + "/song?id=" + song.id;
        String albumArtUrl = baseUrl.toString() + "/albumart?id=" + song.listId;

        MediaMetadata musicMetadata = new MediaMetadata(MediaMetadata.MEDIA_TYPE_MUSIC_TRACK);

        musicMetadata.putString(MediaMetadata.KEY_TITLE, song.songName);
        musicMetadata.putString(MediaMetadata.KEY_ALBUM_TITLE, song.songName);
        musicMetadata.putInt(MediaMetadata.KEY_TRACK_NUMBER, song.duration);
        musicMetadata.addImage(new WebImage(Uri.parse(albumArtUrl)));

        try {
            MediaInfo mediaInfo = new MediaInfo.Builder(songUrl)
                    .setStreamType(MediaInfo.STREAM_TYPE_BUFFERED)
                    .setContentType("audio/mpeg")
                    .setMetadata(musicMetadata)
                    .setStreamDuration(song.duration)
                    .build();
            RemoteMediaClient remoteMediaClient = castSession.getRemoteMediaClient();
            remoteMediaClient.load(mediaInfo, true, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
