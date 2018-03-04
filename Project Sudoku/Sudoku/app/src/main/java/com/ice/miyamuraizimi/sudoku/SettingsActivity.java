package com.ice.miyamuraizimi.sudoku;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.content.Context;

public class SettingsActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }

    // ��ҹ��һѨ�غѹ�ͧ������͡ "����� (Music)"
    public static boolean getOptionMusic(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean("music", true);
    }

    // ��ҹ��һѨ�غѹ�ͧ������͡ "��Ǫ��� (Hints)"
    public static boolean getOptionHints(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean("hints", true);
    }
}
