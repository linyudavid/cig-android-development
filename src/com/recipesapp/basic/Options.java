package com.recipesapp.basic;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Options extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.options);
    }
}
