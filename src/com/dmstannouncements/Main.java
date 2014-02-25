package com.dmstannouncements;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main extends Activity  {
	
	private final String TAG = "main";
	
	//private static final int SETTINGS_RESULT = 1;
	public static final String PREFS_NAME = "custom_rss";
		  
	private static final String URL1 = "http://my.dmst.aueb.gr/rssfeed.php";
	private static final String URL2 = "http://www.dmst.aueb.gr/index.php/el/eventslist?format=feed&type=rss";
	private static final String URL3 = "http://www.dmst.aueb.gr/index.php/el/newslist?format=feed&type=rss";
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
    	SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
    	
        final Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i(TAG,"1");
            	Intent intent = new Intent(Main.this, RssScreen.class);
            	Log.i(TAG,"2");
                intent.putExtra("RSS_URL", URL1);
                Log.i(TAG,"3");
                startActivity(intent);
                Log.i(TAG,"4");
            }
        });

        final Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, RssScreen.class);
                intent.putExtra("RSS_URL", URL2);
                startActivity(intent);
            }
        });

        final Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(Main.this, RssScreen.class);
                intent.putExtra("RSS_URL", URL3);
                startActivity(intent);
            }
        });

        final Button button4 = (Button) findViewById(R.id.button4);
        button4.setText(sharedPrefs.getString("rssName1", "Custom RSS"));
        
        final String URL4 = sharedPrefs.getString("rssLink1", "http://my.dmst.aueb.gr/rssfeed.php");
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(Main.this, RssScreen.class);
                intent.putExtra("RSS_URL", URL4);
                startActivity(intent);
            }
        });

        final Button button5 = (Button) findViewById(R.id.button5);
        button5.setText(sharedPrefs.getString("rssName2", "Custom RSS"));
        
        final String URL5 = sharedPrefs.getString("rssLink2", "http://my.dmst.aueb.gr/rssfeed.php");
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(Main.this, RssScreen.class);
                intent.putExtra("RSS_URL", URL5);
                startActivity(intent);
            }
        });

        final Button button6 = (Button) findViewById(R.id.button6);
        button6.setText(sharedPrefs.getString("rssName3", "Custom RSS"));
        
        final String URL6 = sharedPrefs.getString("rssLink3", "http://my.dmst.aueb.gr/rssfeed.php");
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(Main.this, RssScreen.class);
                intent.putExtra("RSS_URL", URL6);
                startActivity(intent);
            }
        });

        final Button button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(Main.this, SettingsScreen.class);
                startActivity(intent);
            	//startActivityForResult(intent, SETTINGS_RESULT);
            }
        });


    }   
}