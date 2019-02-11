package com.example.lee.morsechat;

import com.example.lee.morsechat.BinaryTree.*;
import com.example.lee.morsechat.Helpers.*;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button morseButton;
    private Button cancelButton;
    private TextView englishText;
    private TextView morseText;
    private TextView currentWordText;

    private MorseCode morseCode;

    private List<String> codeStrings;
    private List<String> sentenceArray;
    private List<String> morseCodeArray;

    private String currentWord;
    private char currentLetter;
    private String morseCodeWord;
    private String runningMorseCodeLetter;

    private CountDownTimer sentenceTimer;
    private CountDownTimer wordTimer;
    private AudioPlayer audioPlayer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // English and Morse Code Arrays
        sentenceArray = new ArrayList<String>();
        morseCodeArray = new ArrayList<String>();

        // English variables initialised to empty strings/char
        currentWord = "";
        currentLetter = Character.MIN_VALUE;

        // Morse Code variables initialised to empty strings
        morseCodeWord = "";
        runningMorseCodeLetter = "";

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

        audioPlayer = new AudioPlayer();

    }

    // resets Morse Code word string current letter when position wraps around the tree
    private void resetMorseCode() {
        if (runningMorseCodeLetter.length() > 5) {
            runningMorseCodeLetter = "";
        }
    }

    // update current letter on dot
    private void dotClick() {
        audioPlayer.play(this, R.raw.shortbeep);
        char returnChar = BinaryTree.getInstance().traverseTreeDot();
        currentLetter = returnChar;
        currentWordText.setText(currentWord + String.valueOf(returnChar));

        resetMorseCode();
        runningMorseCodeLetter += String.valueOf('\u22C5');
    }

    // update current letter on dash
    private void dashClick() {
        audioPlayer.play(this, R.raw.longbeep);
        char returnChar = BinaryTree.getInstance().traverseTreeDash();
        currentLetter = returnChar;
        currentWordText.setText(currentWord + String.valueOf(returnChar));

        resetMorseCode();
        runningMorseCodeLetter += String.valueOf('\u2012');
    }

    private void appendToSentence() {
        // add current word to array and reset it
        sentenceArray.add(currentWord);
        currentWord = "";
        englishText.setText(Helpers.concatenateList(sentenceArray));
        currentWordText.setText("");

        // add Morse Code for current word to array and reset it
        morseCodeArray.add(morseCodeWord);
        morseText.setText(Helpers.concatenateList(morseCodeArray));
        morseCodeWord = "";
    }

    private void appendToWord() {
        currentWord += String.valueOf(currentLetter);
        currentWordText.setText(currentWord + "_");

        morseCodeWord += runningMorseCodeLetter;

        // reset node to the root
        BinaryTree.getInstance().reset();
        runningMorseCodeLetter = "";
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

            dotClick();
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

            dashClick();
            return true;
        });

        cancelButton.setOnClickListener((View v) ->
                deleteLastWord());
    }

    // removes last item in the array of words and Morse Code
    private void deleteLastWord() {
        if (sentenceArray.size() > 0) {
            sentenceArray.remove(sentenceArray.size() - 1);
            morseCodeArray.remove(morseCodeArray.size() - 1);
        }
        if (sentenceArray.size() > 0) {
            englishText.setText(Helpers.concatenateList(sentenceArray));
            morseText.setText(Helpers.concatenateList(morseCodeArray));
        } else {
            englishText.setText("");
            morseText.setText("");
        }
    }

    private CountDownTimer createSentenceTimer() {
        CountDownTimer timer = new CountDownTimer(3500, 1000) {

            public void onTick(long millisUntilFinished) {
                ;
            }

            public void onFinish() {
                appendToSentence();
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
                appendToWord();
            }
        }.start();

        return timer;
    }
}
