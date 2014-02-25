package com.dmstannouncements;



import android.os.Bundle;
import android.preference.PreferenceActivity;

public class SettingsScreen extends PreferenceActivity {
	
	public static final String KEY_PREF_SYNC_CONN = "pref_syncConnectionType";

	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.custom_rss);
    }
}