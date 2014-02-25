package com.dmstannouncements;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class RssScreen extends Activity implements OnItemClickListener{

	private ProgressBar progressBar;
	private ListView listView;
	private final String TAG = "rssscreen";
	private String rssurl;
	
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    Bundle extras = getIntent().getExtras();
		rssurl = extras.getString("RSS_URL");
	    Log.i(TAG,"1");
	    // TODO Auto-generated method stub
	    setContentView(R.layout.rssscreen);
	    progressBar = (ProgressBar) findViewById(R.id.progressBar1);
	    listView = (ListView) findViewById(R.id.listView1);
	    listView.setOnItemClickListener(this);
	    startRssService();
	}
	
	private void startRssService(){
		Log.i(TAG,"startRssService");
		Intent intent = new Intent(this, RssService.class);
		intent.putExtra(RssService.RECEIVER, resultReceiver);
		intent.putExtra("RSSURL", rssurl);
		Log.i(TAG,"extra");
		startService(intent);
		Log.i(TAG,"startService");
	}
	
	private ResultReceiver resultReceiver = new ResultReceiver(new Handler()) {	
		@SuppressWarnings("unchecked")
		@Override
		public void onReceiveResult(int resultCode, Bundle resultData){
			Log.d(TAG,"into onreceive");
			List<RssItem> items = (List<RssItem>) resultData.getSerializable(RssService.ITEMS);
			
			Log.d(TAG,"got input");
			if (items != null) {
				RssAdapter adapter = new RssAdapter(RssScreen.this, items);
				Log.d(TAG,"new adapter");
				listView.setAdapter(adapter);
			} else {
				Toast.makeText(RssScreen.this, "An error occured while downloading the rss feed",
						Toast.LENGTH_LONG).show();
			}
			progressBar.setVisibility(View.GONE);
			listView.setVisibility(View.VISIBLE);
			Log.d(TAG,"listview visible");
			Log.i(TAG, "visibility: " + listView.getVisibility());
		}
	};
	
	//@Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        RssAdapter adapter = (RssAdapter) parent.getAdapter();
        RssItem item = (RssItem) adapter.getItem(position);
        Uri uri = Uri.parse(item.getLink());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
	}	
}