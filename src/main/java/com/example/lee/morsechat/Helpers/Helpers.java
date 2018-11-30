package com.example.lee.morsechat.Helpers;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.view.Gravity;
import android.widget.Toast;

import com.example.lee.morsechat.R;

import java.util.List;

public class Helpers {
    private SoundPool soundPool;
    private int[] sounds;

    public String concatenateList(List<String> wordList) {
        String outString = "";

        for (int i = 0; i < wordList.size() - 1; i++) {
            outString += wordList.get(i) + " ";
        }
        outString += wordList.get(wordList.size()-1);

        return outString;
    }


    public void initSounds(Context context) {
        sounds = new int[2];
        sounds[0] = R.raw.shortbeep;
        sounds[1] = R.raw.longbeep;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(10)
                    .build();
        } else {
            soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 1);

        }
    }

    public void playBeep(Context context, int soundNum) {
        if (soundPool == null) {
            initSounds(context);
        }

        soundPool.play(sounds[soundNum], 1, 1, 1, 0 , 1f);
    }



}

