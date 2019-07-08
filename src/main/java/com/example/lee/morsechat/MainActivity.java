package com.example.lee.morsechat;

import com.example.lee.morsechat.BinaryTree.*;
import com.example.lee.morsechat.ClickHandler.ClickHandler;
import com.example.lee.morsechat.Helpers.*;
import com.example.lee.morsechat.WordContainers.EnglishWordContainer;
import com.example.lee.morsechat.WordContainers.MorseCodeWordContainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button morseButton;
    private Button cancelButton;
    private TextView englishText;
    private TextView morseText;
    private TextView currentWordText;

    private MorseCode morseCode;

    private List<String> codeStrings;

    private CountDownTimer sentenceTimer;
    private CountDownTimer wordTimer;
    private AudioPlayer audioPlayer;
    private ClickHandler clickHandler;

    private EnglishWordContainer englishWordContainer;
    private MorseCodeWordContainer morseCodeWordContainer;
    private TextUpdater textUpdater;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create and initialise binary tree / current node to root
        morseCode = new MorseCode();
        codeStrings = morseCode.getCodeStrings();
        BinaryTree.getInstance().buildTree(codeStrings);

        // create button variables
        morseButton = (Button) findViewById(R.id.morseButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);

        // create text view variables
        englishText = (TextView) findViewById(R.id.englishText);
        morseText = (TextView) findViewById(R.id.morseText);
        currentWordText = (TextView) findViewById(R.id.currentWordText);

        CountDownTimer sentenceTimer = null;
        CountDownTimer wordTimer = null;
        setListeners();

        englishWordContainer = new EnglishWordContainer();
        morseCodeWordContainer = new MorseCodeWordContainer();
        audioPlayer = new AudioPlayer();
        clickHandler = new ClickHandler(englishWordContainer, morseCodeWordContainer, audioPlayer);
        textUpdater = new TextUpdater(englishWordContainer, morseCodeWordContainer);
    }
    
    private void setListeners() {
        morseButton.setOnClickListener((View v) ->
        {
            // start a new timer for sentence concatenation if none exists, or cancel previous timer and create a new one
            if (sentenceTimer == null) {
                sentenceTimer = createSentenceTimer();
            } else {
                sentenceTimer.cancel();
                sentenceTimer = createSentenceTimer();
            }
            // start a new timer for letter concatenation if none exists, or cancel previous timer and create a new one
            if (wordTimer == null) {
                wordTimer = createLetterTimer();
            } else {
                wordTimer.cancel();
                wordTimer = createLetterTimer();
            }

            clickHandler.dotClick(currentWordText, this);
        });

        morseButton.setOnLongClickListener((View v) ->
        {
            if (sentenceTimer == null) {
                sentenceTimer = createSentenceTimer();
            } else {
                sentenceTimer.cancel();
                sentenceTimer = createSentenceTimer();
            }

            if (wordTimer == null) {
                wordTimer = createLetterTimer();
            } else {
                wordTimer.cancel();
                wordTimer = createLetterTimer();
            }

            clickHandler.dashClick(currentWordText, this);
            return true;
        });

        cancelButton.setOnClickListener((View v) ->
                textUpdater.deleteLastWord(englishText, morseText));
    }

    private CountDownTimer createSentenceTimer() {
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

    private CountDownTimer createLetterTimer() {
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
