package com.example.myapplication.futuresmsactivity;

// Made with the help of https://github.com/pfeuffer/SMSloc/tree/bd3edbaaafcc1f593049e4d76bc4f28f47a5de9c/src/de/pfeufferweb/android/whereru

import java.util.Locale;

// Formats the integers into degrees

public class SMSDegreeFormatter {

    public String format(double number) {
        int integerValue = (int) number;
        double minutes = Math.abs(number - integerValue) * 60;
        int minutesIntegerValue = (int) minutes;
        double seconds = (minutes - minutesIntegerValue) * 60;

        return String.format(Locale.ENGLISH, "%d°+%d'+%.2f\"", integerValue,
                minutesIntegerValue, seconds);
    }
}