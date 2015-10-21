package com.example.geekadvisor;

import geekAdvisor_pack.Support;
import java.util.ArrayList;
import org.json.JSONObject;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class ListLivresActivity extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_list_livres,
				container, false);
		try {
			String jsResult = new Support().getSupport(MainActivity.title);
			JSONObject jObject = new JSONObject(jsResult);

			ArrayList<String> web = new ArrayList<String>();
			ArrayList<String> web2 = new ArrayList<String>();
			ArrayList<String> web3 = new ArrayList<String>();
			ArrayList<Integer> imageId = new ArrayList<Integer>();

			for (int i = 0; i < jObject.length(); i++) {
				JSONObject json_data = jObject.optJSONObject("" + i);
				String id = ""
						+ json_data.getJSONObject("support").getInt("Id");
				String title = json_data.getJSONObject("support").getString(
						"Nom");
				String aut = json_data.getJSONObject("support").getString(
						"Auteur");
				String type = json_data.getJSONObject("support").getString(
						"Type");
				Log.i("resultat", json_data.toString());

				if (type.equals("Livre")) {
					web.add(title);
					web2.add(id);
					web3.add(aut);
					imageId.add(R.drawable.xml);
				}

			}

			ListView mListView = (ListView) rootView
					.findViewById(R.id.listView2);
			mListView.setAdapter(new AdapterListLivresActivity(rootView.getContext(), web,
					imageId, web2, web3));

			mListView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					TextView v = (TextView) arg1.findViewById(R.id.txt);
					TextView v2 = (TextView) arg1.findViewById(R.id.textid);

					Intent i = new Intent(getActivity(), LivresActivity.class);
					i.putExtra("Nom", v.getText());
					i.putExtra("id", v2.getText());
					startActivity(i);
				}
			});

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rootView;
	}

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}
}