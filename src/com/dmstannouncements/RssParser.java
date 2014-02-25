package com.dmstannouncements;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;
import android.util.Xml;

public class RssParser {
	
	// No namespaces
	private final String ns = null;
	private final String TAG = "parser";
	
	public List<RssItem> parse(InputStream inputStream) throws XmlPullParserException, IOException {
		try {
			Log.i(TAG,"intoParser");
			XmlPullParser parser = Xml.newPullParser();
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			parser.setInput(inputStream, null);
			parser.nextTag();
			Log.i(TAG,"b4return");
			return readFeed(parser);
		}
		catch (Exception e) {
			return null;
		}
		finally {
			Log.i(TAG,"outParser");
			if (inputStream !=null) {
				inputStream.close();
			}
		}
	}
	
	private List<RssItem> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, null, "rss");
		String title = null;
		String link = null;
		List<RssItem> items = new ArrayList<RssItem>();
		while (parser.next() != XmlPullParser.END_DOCUMENT) {
			
			
			if (parser.getEventType() !=XmlPullParser.START_TAG){
				continue;
			}
			
			
			String name = parser.getName();
			
			if (name.equals("title")) {
				title = readTitle(parser);
				Log.e("y0", title);
			} else if (name.equals("link")) {
				link = readLink(parser);
				Log.w("y0", link);
			}
			
			if (title != null && link != null){		
				Log.w("y0", "parsarw");
				RssItem item = new RssItem(title, link);				
				items.add(item);
				title = null;
				link = null;
			}
		}
		return items;
	}
		
	private String readTitle (XmlPullParser parser) throws XmlPullParserException, IOException {
		
		parser.require(XmlPullParser.START_TAG, ns, "title");
		String title = readText(parser);
		
		parser.require(XmlPullParser.END_TAG, ns , "title");
		
		return title;
	}

	private String readLink (XmlPullParser parser) throws XmlPullParserException, IOException {
		
		parser.require(XmlPullParser.START_TAG, ns, "link");
		String link = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns , "link");
		
		return link;
	}
	
	private String readText (XmlPullParser parser) throws XmlPullParserException, IOException {
		
		String resultText = "";
		if (parser.next() == XmlPullParser.TEXT) {
			resultText = parser.getText();
			parser.nextTag();
		}
		
		return resultText;
	}	
}