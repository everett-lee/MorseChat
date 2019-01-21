package com.example.lee.morsechat.Helpers;

import android.content.Context;
import android.widget.ImageView;
import com.example.lee.morsechat.R;
import java.util.HashMap;
import java.util.List;

public class Helpers {

    private HashMap<Character, String> imageMap;

    public void Helpers() {
        imageMap.put('0', "@drawable/mt_0");
        imageMap.put('1', "@drawable/mt_1");
        imageMap.put('2', "@drawable/mt_2");
        imageMap.put('3', "@drawable/mt_3");
        imageMap.put('4', "@drawable/mt_4");
        imageMap.put('5', "@drawable/mt_5");
        imageMap.put('6', "@drawable/mt_6");
        imageMap.put('7', "@drawable/mt_7");
        imageMap.put('8', "@drawable/mt_8");
        imageMap.put('A', "@drawable/mt_a");
        imageMap.put('B', "@drawable/mt_b");
        imageMap.put('C', "@drawable/mt_c");
        imageMap.put('D', "@drawable/mt_d");
        imageMap.put('E', "@drawable/mt_e");
        imageMap.put('F', "@drawable/mt_f");
        imageMap.put('G', "@drawable/mt_g");
        imageMap.put('H', "@drawable/mt_h");
        imageMap.put('I', "@drawable/mt_i");
        imageMap.put('J', "@drawable/mt_j");
        imageMap.put('K', "@drawable/mt_k");
        imageMap.put('L', "@drawable/mt_l");
        imageMap.put('M', "@drawable/mt_m");
        imageMap.put('N', "@drawable/mt_n");
        imageMap.put('O', "@drawable/mt_o");
        imageMap.put('P', "@drawable/mt_p");
        imageMap.put('Q', "@drawable/mt_q");
        imageMap.put('R', "@drawable/mt_r");
        imageMap.put('S', "@drawable/mt_s");
        imageMap.put('T', "@drawable/mt_t");
        imageMap.put('U', "@drawable/mt_u");
        imageMap.put('V', "@drawable/mt_v");
        imageMap.put('W', "@drawable/mt_w");
        imageMap.put('X', "@drawable/mt_x");
        imageMap.put('Y', "@drawable/mt_y");
    }

    public String concatenateList(List<String> wordList) {
        String outString = "";

        for (int i = 0; i < wordList.size() - 1; i++) {
            outString += wordList.get(i) + " ";
        }
        outString += wordList.get(wordList.size() - 1);

        return outString;
    }

    public void charToPic(char character, ImageView view, Context context) {
        String retStr = imageMap.get(character);
        int id = context.getResources().getIdentifier(retStr, "drawable",context.getPackageName());
        view.setBackgroundResource(R.drawable.mt_a);

    }

}

