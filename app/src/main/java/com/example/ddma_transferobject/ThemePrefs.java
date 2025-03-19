package com.example.ddma_transferobject;

import android.content.Context;
import android.content.SharedPreferences;

public class ThemePrefs{
    private static final String KEY_THEME = "dark_theme";
    private static final String PREF_NAME = "theme_settings";

    public static void saveTheme(Context context, boolean isDark) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().putBoolean(KEY_THEME, isDark).apply();
    }

    public static boolean isDarkTheme(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getBoolean(KEY_THEME, false);
    }
}