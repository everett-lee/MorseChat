package com.example.lee.morsechat.Helpers;
import android.content.Context;
import android.media.MediaPlayer;

public class AudioPlayer {

    private MediaPlayer mMediaPlayer;

    public void stop() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public void play(Context c, int rid) {
        stop();

        mMediaPlayer = MediaPlayer.create(c, rid);
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            public void onCompletion(MediaPlayer mediaPlayer) {
                stop();
            }
        });

        mMediaPlayer.start();
    }

}