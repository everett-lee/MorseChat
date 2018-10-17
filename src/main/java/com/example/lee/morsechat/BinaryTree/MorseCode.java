package com.example.lee.morsechat.BinaryTree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MorseCode {
    private Map<String, Character> translateCode;
    private List<String> codeStrings;
    private List<Character> values;

    public MorseCode() {
        this.translateCode = new HashMap<String, Character>();
        this.codeStrings
                = Arrays
                .asList(".", "-", "..", ".-", "-.", "--", "...", "..-", ".-.", ".--", "-..", "-.-", "--.", "---", "....", "...-", "..-.", "..--", ".-..", ".-.-", ".--.", ".---", "-...", "-..-", "-.-.", "-.--", "--..", "--.-", "---.", "----", ".....", "....-", "...--", "..---", ".----", "-....", "--...", "---..", "----.", "-----");
        this.values
                = Arrays
                .asList('E', 'T', 'I', 'A', 'N', 'M', 'S', 'U', 'R', 'W', 'D', 'K', 'G', 'O', 'H', 'V', 'F', '\u0000', 'L', '\u0000', 'P', 'J', 'B','X', 'C', 'Y', 'Z', 'Q', '\u0000', '\u0000', '5', '4', '3', '2', '1', '6', '7', '8', '9', '0');

        fillTranslator();
    }

    private void fillTranslator() {
        for (int i = 0; i < codeStrings.size(); i++) {
            translateCode.put(codeStrings.get(i), values.get(i));
        }
    }

    public char translate(String code) {
        return translateCode.get(code);
    }

    public List<String> getCodeStrings() {
        return this.codeStrings;
    }

}