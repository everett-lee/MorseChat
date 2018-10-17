package com.example.lee.morsechat;
import com.example.lee.morsechat.BinaryTree.*;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button morseButton;
    private TextView englishText;
    private TextView morseText;
    private TextView currentWordText;
    private TextView currentLetterText;
    private MorseCode morseCode;
    private BinaryTree morseTree;
    private List<String> codeStrings;
    private String sentenceString;
    private String currentWord;
    private char currentLetter;
    private String morseCodeWord;
    private String morseCodeString;
    private String runningMorseCodeLetter;
    private CountDownTimer sentenceTimer;
    private CountDownTimer wordTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // English variables initialised to empty strings/char
        sentenceString = "";
        currentWord = "";
        currentLetter = Character.MIN_VALUE;

        // Morse Code variables initialised to empty strings
        morseCodeWord = "";
        morseCodeString = "";
        runningMorseCodeLetter = "";

        // create and initialise binary tree / current node to root
        morseCode = new MorseCode();
        morseTree = new BinaryTree();
        codeStrings = morseCode.getCodeStrings();
        morseTree.buildTree(codeStrings);

        // create button variables
        morseButton = (Button) findViewById(R.id.morseButton);

        //create text view variables
        englishText = (TextView) findViewById(R.id.englishText);
        morseText = (TextView) findViewById(R.id.morseText);
        currentWordText = (TextView) findViewById(R.id.currentWordText);
        currentLetterText = (TextView) findViewById(R.id.currentLetterText);

        CountDownTimer sentenceTimer = null;
        CountDownTimer wordTimer = null;
        setListeners();

    }

    // resets Morse Code word string current letter when position wraps around the tree
    private void resetMorseCode() {
        if (runningMorseCodeLetter.length() > 5) {
            runningMorseCodeLetter = "";
        }
    }

    // update current letter on dot
    private void dotClick() {
        char returnChar = morseTree.traverseTreeDot();
        currentLetter = returnChar;
        currentLetterText.setText(String.valueOf(returnChar));

        resetMorseCode();
        runningMorseCodeLetter += String.valueOf('\u22C5');
    }

    // update current letter on dash
    private void dashClick() {
        char returnChar = morseTree.traverseTreeDash();
        currentLetter = returnChar;
        currentLetterText.setText(String.valueOf(returnChar));

        resetMorseCode();
        runningMorseCodeLetter += String.valueOf('\u2012');
    }

    private void appendToSentence() {
        sentenceString += currentWord + " ";
        currentWord = "";
        englishText.setText(sentenceString);
        currentWordText.setText("");

        // append Morse Code to string and reset
        morseCodeString += morseCodeWord + " ";
        morseText.setText(morseCodeString);
        morseCodeWord = "";

    }

    private void appendToWord() {
        currentWord += String.valueOf(currentLetter);
        currentWordText.setText(currentWord);
        currentLetterText.setText("");

        morseCodeWord += runningMorseCodeLetter;

        // reset node to the root
        morseTree.reset();
        runningMorseCodeLetter = "";
    }

    private void setListeners() {
        morseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });

        morseButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
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
            }
        });
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
