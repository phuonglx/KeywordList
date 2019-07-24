package com.example.keywordlist.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils {

    public static String splitKeyword(String input) {
        if (input == null || input.isEmpty()) return null;

        String result = null;
        String[] splitArray = input.trim().split(" ");
        int splitArrayLength = splitArray.length;

        if (splitArrayLength <= 1) return input;

        if (splitArrayLength == 2) return splitArray[0] + "\n" + splitArray[1];

        int positionFromLeft = 0;
        int positionFromRight = splitArrayLength - 1;

        String subFromLeft = splitArray[positionFromLeft];
        String subFromRight = splitArray[positionFromRight];

        while (positionFromLeft < positionFromRight - 1) {
            if (subFromLeft.length() <= subFromRight.length()) {
                positionFromLeft++;
                subFromLeft = subFromLeft.concat(" " + splitArray[positionFromLeft]);
            } else {
                positionFromRight--;
                subFromRight = splitArray[positionFromRight].concat(" " + subFromRight);
            }
        }

        result = subFromLeft.concat("\n" + subFromRight);

        return result;
    }

    public static boolean hasNetworkConnection(Context context) {
        if (context == null) return false;

        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            if (connectivityManager != null) {
                NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
                return (netInfo != null && netInfo.isConnected());
            }
        } catch (Exception ex) {
        }

        return false;
    }

}
