package de.ur.wi2_intern.ur_place.urplace.Settings;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.widget.Switch;
import android.widget.Toast;

import de.ur.wi2_intern.ur_place.urplace.AboutActivity;
import de.ur.wi2_intern.ur_place.urplace.R;


public class SettingsFragment extends PreferenceFragment implements
        SharedPreferences.OnSharedPreferenceChangeListener{

    public static final String KEY_GET_LANGUAGE_SETTING = "languageSettings";
    public static final String KEY_GET_UPDATE_LOGIN_SETTING = "updateLoginSetting";
    public static final String KEY_GET_UPDATE_NOTIFICATION_SETTING = "updateNotificationSetting";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getPreferenceManager().setSharedPreferencesName("preference");
        getPreferenceManager().setSharedPreferencesMode(Context.MODE_PRIVATE);

        addPreferencesFromResource(R.xml.preferences);


    }


    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();
        if (key.equals(KEY_GET_LANGUAGE_SETTING)) {
            // Do something with language
        }
        if (key.equals(KEY_GET_UPDATE_LOGIN_SETTING)) {
            String loginSwitch = preferences.getString("loginSwitch", "");
            if(loginSwitch.equals("true")){
                editor.putString("loginSwitch", "false");
                editor.putString("login", "false");
            }
            else{
                editor.putString("loginSwitch", "true");
                editor.putString("login", "true");
            }
        }
        if (key.equals(KEY_GET_UPDATE_NOTIFICATION_SETTING)) {
            // Do something with notifications
        }
        editor.commit();
    }

}