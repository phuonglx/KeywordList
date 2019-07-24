package com.example.keywordlist.utils;

import android.graphics.Color;

public class ColorGenerator {

    private String[] colors = new String[]{"#1abc9c", "#2ecc71", "#3498db", "#9b59b6", "#34495e", "#16a085", "#27ae60", "#2980b9", "#8e44ad", "#2c3e50", "#f1c40f", "#e67e22", "#e74c3c", "#95a5a6", "#f39c12", "#d35400", "#c0392b", "#bdc3c7", "#7f8c8d"};

    private static ColorGenerator instance = null;

    private ColorGenerator() {
    }

    public static ColorGenerator getInstance() {
        if (instance == null) {
            instance = new ColorGenerator();
        }

        return instance;
    }

    public String getColor(String fullname) {
        char[] letters = fullname.replace(" ", "").toCharArray();
        int totalCode = 0;

        for (char character : letters) {
            int code = (byte) character;
            if (code >= 0) totalCode += code;
        }

        return colors[totalCode % colors.length];
    }

    public int getColorInt(String fullname) {
        return Color.parseColor(getColor(fullname));
    }

}
