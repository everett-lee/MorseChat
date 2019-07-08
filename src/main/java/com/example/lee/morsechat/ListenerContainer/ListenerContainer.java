package com.example.lee.morsechat.ListenerContainer;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lee.morsechat.ClickHandler.ClickHandler;
import com.example.lee.morsechat.MainActivity;
import com.example.lee.morsechat.TextUpdater;

public class ListenerContainer {
    private Button morseButton;
    private Button cancelButton;
    private CountDownTimer sentenceTimer;
    private CountDownTimer wordTimer;
    private ClickHandler clickHandler;
    private TextUpdater textUpdater;

    public ListenerContainer(Button morseButton, Button cancelButton, ClickHandler clickHandler,
                             TextUpdater textUpdater) {
        this.morseButton = morseButton;
        this.cancelButton = cancelButton;
        this.clickHandler = clickHandler;
        this.textUpdater = textUpdater;
    }

    public void setListeners(TextView englishText, TextView currentWordText,
                              TextView morseText, MainActivity app) {
        morseButton.setOnClickListener((View v) ->
        {
            // start a new timer for sentence concatenation if none exists, or cancel previous timer and create a new one
            if (sentenceTimer == null) {
                sentenceTimer = createSentenceTimer(englishText, currentWordText,
                        morseText);
            } else {
                sentenceTimer.cancel();
                sentenceTimer = createSentenceTimer(englishText, currentWordText,
                        morseText);
            }
            // start a new timer for letter concatenation if none exists, or cancel previous timer and create a new one
            if (wordTimer == null) {
                wordTimer = createLetterTimer(currentWordText);
            } else {
                wordTimer.cancel();
                wordTimer = createLetterTimer(currentWordText);
            }

            clickHandler.dotClick(currentWordText, app);
        });

        morseButton.setOnLongClickListener((View v) ->
        {
            if (sentenceTimer == null) {
                sentenceTimer = createSentenceTimer(englishText, currentWordText,
                        morseText);
            } else {
                sentenceTimer.cancel();
                sentenceTimer = createSentenceTimer(englishText, currentWordText,
                        morseText);
            }

            if (wordTimer == null) {
                wordTimer = createLetterTimer(currentWordText);
            } else {
                wordTimer.cancel();
                wordTimer = createLetterTimer(currentWordText);
            }

            clickHandler.dashClick(currentWordText, app);
            return true;
        });

        cancelButton.setOnClickListener((View v) ->
                textUpdater.deleteLastWord(englishText, morseText));
    }

    private CountDownTimer createSentenceTimer(TextView englishText, TextView currentWordText,
                                               TextView morseText) {
        CountDownTimer timer = new CountDownTimer(3500, 1000) {

            public void onTick(long millisUntilFinished) {
                ;
            }

            public void onFinish() {
                textUpdater.appendToSentence(englishText, currentWordText, morseText);
            }
        }.start();

        return timer;
    }

    private CountDownTimer createLetterTimer(TextView currentWordText) {
        CountDownTimer timer = new CountDownTimer(2000, 1000) {

            public void onTick(long millisUntilFinished) {
                ;
            }

            public void onFinish() {
                textUpdater.appendToWord(currentWordText);
            }
        }.start();

        return timer;
    }
}
