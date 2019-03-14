package com.example.assignment5.Ques3;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.example.assignment5.R;

public class PrefFragment extends PreferenceFragment {
    public PrefFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference_frag);
    }
}
