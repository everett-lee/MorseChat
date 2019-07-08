package com.example.lee.morsechat;

import com.example.lee.morsechat.BinaryTree.*;
import com.example.lee.morsechat.ClickHandler.ClickHandler;
import com.example.lee.morsechat.Helpers.*;
import com.example.lee.morsechat.WordContainers.EnglishWordContainer;
import com.example.lee.morsechat.WordContainers.MorseCodeWordContainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import ListenerContainer.ListenerContainer;

public class MainActivity extends AppCompatActivity {
    private Button morseButton;
    private Button cancelButton;
    private TextView englishText;
    private TextView morseText;
    private TextView currentWordText;

    private MorseCode morseCode;

    private List<String> codeStrings;

    private AudioPlayer audioPlayer;
    private ClickHandler clickHandler;

    private EnglishWordContainer englishWordContainer;
    private MorseCodeWordContainer morseCodeWordContainer;
    private TextUpdater textUpdater;
    private ListenerContainer listenerContainer;


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

        englishWordContainer = new EnglishWordContainer();
        morseCodeWordContainer = new MorseCodeWordContainer();
        audioPlayer = new AudioPlayer();
        clickHandler = new ClickHandler(englishWordContainer, morseCodeWordContainer, audioPlayer);
        textUpdater = new TextUpdater(englishWordContainer, morseCodeWordContainer);

        listenerContainer = new ListenerContainer(morseButton, cancelButton, clickHandler, textUpdater);
        listenerContainer.setListeners(englishText, currentWordText, morseText, this);
    }
}
