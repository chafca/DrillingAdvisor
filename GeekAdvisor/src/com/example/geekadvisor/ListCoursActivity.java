package com.example.geekadvisor;

import java.util.ArrayList;

import geekAdvisor_pack.Support;
import geekAdvisor_pack.Ue;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("NewApi") 
public class ListCoursActivity extends Fragment implements TabListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

    	View rootView = inflater.inflate(R.layout.fragment_list_cours, container, false);
    	try {
			String jsResult = new Support().getSupport(MainActivity.title);
			JSONObject jObject = new JSONObject(jsResult);
			
			ArrayList<String> web = new ArrayList<String>();	
			ArrayList<Integer> imageId = new ArrayList<Integer>();

			for (int i = 0; i < jObject.length(); i++) {
				JSONObject json_data = jObject.optJSONObject("" + i);
				String title = json_data.getJSONObject("support")
						.getString("Nom");
				String type = json_data.getJSONObject("support")
						.getString("Type");
				Log.i("resultat", json_data.toString());
				if(type.equals("Cours")){
					//String title = json_data.getJSONObject("support")
							//.getString("Nom");
					web.add(title);
					imageId.add(R.drawable.cours);
				}			
			}	
			
			ListView mListView = (ListView)rootView.findViewById(R.id.listView2);
			mListView.setAdapter(new CustomList(rootView.getContext(), web, imageId));
			
			mListView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					// TODO Auto-generated method stub
					TextView v =(TextView) arg1.findViewById(R.id.txt);
                    Intent i = new Intent(getActivity(),CoursActivity.class);
						i.putExtra("Nom",v.getText());
						startActivity(i);
				}		
			});

		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    	
        return rootView;
    }

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
}
