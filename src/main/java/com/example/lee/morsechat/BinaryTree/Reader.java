package com.example.lee.morsechat.BinaryTree;

import java.util.Scanner;

public class Reader {
    private Scanner reader;

    public Reader() {
        this.reader = new Scanner(System.in);
    }

    public char getInput() {

        String input = reader.nextLine();

        if (input.isEmpty()) {
            return '\u0000';
        }

        return input.charAt(0);
    }
}