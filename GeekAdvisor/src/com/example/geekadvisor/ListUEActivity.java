package com.example.geekadvisor;

import geekAdvisor_pack.Ue;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListUEActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_ue);
		
		try {
				String jsResult = new Ue().getAllUE();
				JSONObject jObject = new JSONObject(jsResult);
				String[] web = new String[jObject.length()];
				Integer[] imageId = new Integer[jObject.length()];
				for (int i = 0; i < jObject.length(); i++) {
					JSONObject json_data = jObject.optJSONObject("" + i);
					String title = json_data.getJSONObject("ue")
							.getString("Nom");
					web[i] = title;
					imageId[i] = R.drawable.cours;			
				}	
				Log.i("res", "Amine");
				ListView mListView = (ListView)findViewById(R.id.listView2);
				mListView.setAdapter(new ListAdapter(this, web, imageId));
				
				mListView.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
							long arg3) {
						// TODO Auto-generated method stub
						TextView v =(TextView) arg1.findViewById(R.id.txt);
						try {
							Intent i=new Intent(ListUEActivity.this,MainActivity.class);
							i.putExtra("Nom",v.getText());
							startActivity(i);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
						}
						//
					}		
				});
		
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_ue, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
