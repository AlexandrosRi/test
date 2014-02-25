package com.dmstannouncements;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

public class RssService extends IntentService {
	
	public static final String ITEMS = "items";
	public static final String RECEIVER = "receiver";
	
	private static final String TAG = "RssService";
	
	public RssService() {
        super("RssService");
    }

	@Override
	protected void onHandleIntent(Intent intent) {
		
		final String RSS_URL = (String) intent.getExtras().get("RSSURL");
		
		Log.d(TAG,"Service started");
		List<RssItem> rssItems = null;
		
		try{
			RssParser parser = new RssParser();
			rssItems = parser.parse(getInputStream(RSS_URL));
			Log.d(TAG,"got input");
		} catch (XmlPullParserException e) {
			Log.w(e.getMessage(),e);
		} catch (IOException e) {
			Log.w(e.getMessage(),e);
		}
		Bundle bundle = new Bundle();
		Log.d(TAG,"created bundle");
		bundle.putSerializable(ITEMS,  (Serializable) rssItems);
		Log.d(TAG,"put serialazibalbes");
		ResultReceiver receiver = intent.getParcelableExtra(RECEIVER);
		Log.d(TAG,"created receiver");
		receiver.send(0, bundle);
		Log.d(TAG,"sent receiver");
	}
	
	public InputStream getInputStream(String link) {
		try {
			URL url = new URL(link);
			//url.openConnection();
			return url.openConnection().getInputStream();
		} catch (IOException e) {
			Log.w(TAG, "Exception while retrieving the input stream", e);
			return null;
		} 
	}
}
