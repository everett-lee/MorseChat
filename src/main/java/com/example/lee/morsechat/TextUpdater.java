package com.example.lee.morsechat;

import android.media.midi.MidiOutputPort;
import android.widget.TextView;

import com.example.lee.morsechat.BinaryTree.BinaryTree;
import com.example.lee.morsechat.Helpers.Helpers;
import com.example.lee.morsechat.WordContainers.EnglishWordContainer;
import com.example.lee.morsechat.WordContainers.MorseCodeWordContainer;

import java.util.ArrayList;
import java.util.List;

public class TextUpdater {
    private EnglishWordContainer englishWordContainer;
    private MorseCodeWordContainer morseCodeWordContainer;
    private List<String> sentenceArray;
    private List<String> morseCodeArray;

    public TextUpdater(EnglishWordContainer englishWordContainer,
                       MorseCodeWordContainer morseCodeWordContainer) {
        this.englishWordContainer = englishWordContainer;
        this.morseCodeWordContainer = morseCodeWordContainer;
        sentenceArray = new ArrayList<String>();
        morseCodeArray = new ArrayList<String>();

    }

    public void appendToSentence(TextView englishText, TextView currentWordText,
                                  TextView morseText) {
        // add current word to array and reset it
        sentenceArray.add(englishWordContainer.getCurrentWord());
        englishWordContainer.setCurrentWord("");
        englishText.setText(Helpers.concatenateList(sentenceArray));
        currentWordText.setText("");

        // add Morse Code for current word to array and reset it
        morseCodeArray.add(morseCodeWordContainer.getMorseCodeWord());
        morseText.setText(Helpers.concatenateList(morseCodeArray));
        morseCodeWordContainer.setMorseCodeWord("");
    }

    public void appendToWord(TextView currentWordText) {
        String currentWord = englishWordContainer.getCurrentWord();
        currentWord += String.valueOf(englishWordContainer.getCurrentLetter());
        englishWordContainer.setCurrentWord(currentWord);
        currentWordText.setText(currentWord + "_");

        String morseCodeWord = this.morseCodeWordContainer.getMorseCodeWord();
        morseCodeWord += this.morseCodeWordContainer.getRunningMorseCodeLetter();
        this.morseCodeWordContainer.setMorseCodeWord(morseCodeWord);

        // reset node to the root
        BinaryTree.getInstance().reset();
        this.morseCodeWordContainer.setRunningMorseCodeLetter("");
    }


    // removes last item in the array of words and Morse Code
    public void deleteLastWord(TextView englishText,
                                TextView morseText) {
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

}
