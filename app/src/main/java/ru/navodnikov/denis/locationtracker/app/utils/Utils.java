package ru.navodnikov.denis.locationtracker.app.utils;

import android.widget.EditText;

public class Utils {
    public static String getTextFromView(EditText editText){
        return editText.getText().toString().trim();
    }
}
