package com.example.lee.morsechat.ClickHandler;

import android.app.Application;
import android.widget.TextView;

import com.example.lee.morsechat.BinaryTree.BinaryTree;
import com.example.lee.morsechat.Helpers.AudioPlayer;
import com.example.lee.morsechat.MainActivity;
import com.example.lee.morsechat.R;
import com.example.lee.morsechat.WordContainers.EnglishWordContainer;
import com.example.lee.morsechat.WordContainers.MorseCodeWordContainer;

public class ClickHandler {
    EnglishWordContainer englishWordContainer;
    MorseCodeWordContainer morseCodeWordContainer;
    AudioPlayer audioPlayer;

    public ClickHandler(EnglishWordContainer englishWordContainer,
                        MorseCodeWordContainer morseCodeWordContainer
                        , AudioPlayer audioPlayer) {
        this.englishWordContainer = englishWordContainer;
        this.morseCodeWordContainer = morseCodeWordContainer;
        this.audioPlayer = audioPlayer;
    }

    // update current letter on dot
    public void dotClick(TextView currentWordText, MainActivity app) {
        audioPlayer.play(app, R.raw.shortbeep);
        char returnChar = BinaryTree.getInstance().traverseTreeDot();
        englishWordContainer.setCurrentLetter(returnChar);
        currentWordText.setText(englishWordContainer.getCurrentWord()  + String.valueOf(returnChar));

        morseCodeWordContainer.resetMorseCode();
        String runningMorseCodeLetter = morseCodeWordContainer.getRunningMorseCodeLetter();
        morseCodeWordContainer.setRunningMorseCodeLetter(runningMorseCodeLetter += String.valueOf('\u22C5'));
    }

    // update current letter on dash
    public void dashClick(TextView currentWordText, MainActivity app) {
        audioPlayer.play(app, R.raw.longbeep);
        char returnChar = BinaryTree.getInstance().traverseTreeDash();
        englishWordContainer.setCurrentLetter(returnChar);
        currentWordText.setText(englishWordContainer.getCurrentWord()  + String.valueOf(returnChar));

        morseCodeWordContainer.resetMorseCode();
        String runningMorseCodeLetter = morseCodeWordContainer.getRunningMorseCodeLetter();
        morseCodeWordContainer.setRunningMorseCodeLetter(runningMorseCodeLetter += String.valueOf('\u2012'));
    }

}
