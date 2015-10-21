package com.example.geekadvisor;

import java.net.URL;
import java.util.ArrayList;

import geekAdvisor_pack.Support;
import geekAdvisor_pack.Ue;
import geekAdvisor_pack.Utilisateur;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("NewApi") 
public class ListCoursActivity extends Fragment implements TabListener {

	static String  urlImage="http://"+Utilisateur.ip+"/";
	RatingBar ratingBar;
	TextView tvnbAvis;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

    	View rootView = inflater.inflate(R.layout.fragment_list_cours, container, false);
    	try {
			String jsResult = new Support().getSupport(MainActivity.title);
			JSONObject jObject = new JSONObject(jsResult);
			
			ArrayList<String> web = new ArrayList<String>();
			ArrayList<String> web2 = new ArrayList<String>();
			ArrayList<Bitmap> imageId = new ArrayList<Bitmap>();
			ArrayList<Float> moyenne = new ArrayList<Float>();
			ArrayList<String> nbAvis = new ArrayList<String>();

			for (int i = 0; i < jObject.length(); i++) {
				JSONObject json_data = jObject.optJSONObject("" + i);
				String id = ""+ json_data.getJSONObject("support")
						.getInt("Id");
				String title = json_data.getJSONObject("support")
						.getString("Nom");
				String type = json_data.getJSONObject("support")
						.getString("Type");
				String img = json_data.getJSONObject("support")
						.getString("Image");
				Float moyenne_note;
				if(!(json_data.getJSONObject("support").isNull("MoyenneNote"))){
					moyenne_note = (float) json_data.getJSONObject("support")
							.getDouble("MoyenneNote");
				}else{
					moyenne_note = (float) 0.1;
				}
				String nbavis = json_data.getJSONObject("support")
						.getString("NombreAvis");
				Log.i("resultat", json_data.toString());
				
				URL url = new URL(urlImage+""+img);
				Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
				
				if(type.equals("Cours")){
					web.add(title);
					web2.add(id);
					imageId.add(image);	
					moyenne.add(moyenne_note);
					nbAvis.add(nbavis+" Avis");
				}
						
			}	
			
			ListView mListView = (ListView)rootView.findViewById(R.id.listView2);
			mListView.setAdapter(new AdapterListCours(rootView.getContext(), web, imageId, web2, moyenne, nbAvis));
			
			mListView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					TextView v =(TextView) arg1.findViewById(R.id.txt);
					TextView v2 =(TextView) arg1.findViewById(R.id.textid);

                    Intent i = new Intent(getActivity(),CoursActivity.class);
						i.putExtra("Nom",v.getText());
						i.putExtra("id",v2.getText());
						startActivity(i);
				}		
			});

		} catch (Exception e) {
		e.printStackTrace();
	}
    	
        return rootView;
    }

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {	
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}
}
