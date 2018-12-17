package com.example.lee.morsechat.Helpers;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.view.Gravity;
import android.widget.Toast;

import com.example.lee.morsechat.R;

import java.util.List;

public class Helpers {

    public String concatenateList(List<String> wordList) {
        String outString = "";

        for (int i = 0; i < wordList.size() - 1; i++) {
            outString += wordList.get(i) + " ";
        }
        outString += wordList.get(wordList.size() - 1);

        return outString;
    }


}

