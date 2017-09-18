package com.dev.jvh.notificationsexercise;

import android.app.Activity;
import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import static android.R.attr.visibility;

/**
 * Created by JochemVanHespen on 9/18/2017.
 */

public class SettingsActivity extends AppCompatActivity {

    private static final String TAG = "SettingsActivity";
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content,new SettingsFragment())
                .commit();

    }




    public static class SettingsFragment extends PreferenceFragment
            implements SharedPreferences.OnSharedPreferenceChangeListener{

        public static final String KEY_INFO_NOTIFICATION = "info_notification";

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if(sharedPreferences.getBoolean(key,false)) {
                Preference infoPreference = findPreference(key);
                infoPreference.setSummary("Info notification turned on");
            }
            if(!sharedPreferences.getBoolean(key,false)) {
                Preference infoPreference = findPreference(key);
                infoPreference.setSummary("Switching this on will show you a nice piece of information of the application");
            }

        }



        @Override
        public void onPause() {
            super.onPause();
            getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onResume() {
            super.onResume();
            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        }
    }
}


