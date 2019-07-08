package com.example.lee.morsechat.WordContainers;

public class EnglishWordContainer {
    private String currentWord;
    private char currentLetter;

    public EnglishWordContainer() {
        // English variables initialised to empty strings/char
        this.currentWord = "";
        this.currentLetter = Character.MIN_VALUE;
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }

    public char getCurrentLetter() {
        return currentLetter;
    }

    public void setCurrentLetter(char currentLetter) {
        this.currentLetter = currentLetter;
    }
}
