package com.example.lee.morsechat.WordContainers;

public class MorseCodeWordContainer {
    private String morseCodeWord;
    private String runningMorseCodeLetter;

    public MorseCodeWordContainer() {
        // Morse Code variables initialised to empty strings
        this.morseCodeWord = "";
        this.runningMorseCodeLetter = "";
    }

    // resets Morse Code word string current letter when position wraps around the tree
    public void resetMorseCode() {
        if (runningMorseCodeLetter.length() > 5) {
            runningMorseCodeLetter = "";
        }
    }

    public String getMorseCodeWord() {
        return this.morseCodeWord;
    }

    public void setMorseCodeWord(String morseCodeWord) {
        this.morseCodeWord = morseCodeWord;
    }

    public String getRunningMorseCodeLetter() {
        return runningMorseCodeLetter;
    }

    public void setRunningMorseCodeLetter(String runningMorseCodeLetter) {
        this.runningMorseCodeLetter = runningMorseCodeLetter;
    }
}
