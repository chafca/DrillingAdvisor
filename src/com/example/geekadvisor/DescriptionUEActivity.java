package com.example.geekadvisor;

import geekAdvisor_pack.Support;
import geekAdvisor_pack.Ue;
import geekAdvisor_pack.Utilisateur;

import java.net.URL;
import java.util.ArrayList;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class DescriptionUEActivity extends Activity {

	static String urlImage = "http://" + Utilisateur.ip + "/";
	String title = "K";
	int idSupport;
	TextView tvContenu;
	Button btnVoirCours;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_description_ue);

		// on récupére le nom de l'UE sélectionner
		title = getIntent().getExtras().get("Nom").toString();
		this.setTitle(title);
		tvContenu = (TextView) findViewById(R.id.tvContenu);

		try {
			String jsResult = new Ue().getUEName(title);
			JSONObject jObject = new JSONObject(jsResult);
			JSONObject json_data = jObject.optJSONObject("" + 0);
			String desc = json_data.getJSONObject("ue").getString("Contenu");
			tvContenu.setText(desc);

			Log.i("resultat", json_data.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			String jsResult = new Support().getSupportDescription(title);
			JSONObject jObject = new JSONObject(jsResult);

			ArrayList<String> web = new ArrayList<String>();
			ArrayList<String> web2 = new ArrayList<String>();
			ArrayList<Bitmap> imageId = new ArrayList<Bitmap>();
			ArrayList<Float> moyenne = new ArrayList<Float>();
			ArrayList<String> nbAvis = new ArrayList<String>();

			for (int i = 0; i < jObject.length(); i++) {
				JSONObject json_data = jObject.optJSONObject("" + i);
				String id = ""
						+ json_data.getJSONObject("support").getInt("Id");
				String title = json_data.getJSONObject("support").getString(
						"Nom");
				String type = json_data.getJSONObject("support").getString(
						"Type");
				String img = json_data.getJSONObject("support").getString(
						"Image");
				Float moyenne_note;
				if (!(json_data.getJSONObject("support").isNull("MoyenneNote"))) {
					moyenne_note = (float) json_data.getJSONObject("support")
							.getDouble("MoyenneNote");
				} else {
					moyenne_note = (float) 0.1;
				}
				String nbavis = json_data.getJSONObject("support").getString(
						"NombreAvis");
				Log.i("resultat", json_data.toString());

				URL url = new URL(urlImage + "" + img);
				Bitmap image = BitmapFactory.decodeStream(url.openConnection()
						.getInputStream());

				if (type.equals("Cours")) {
					web.add(title);
					web2.add(id);
					imageId.add(image);
					moyenne.add(moyenne_note);
					nbAvis.add(nbavis + " Avis");
				}

			}

			ListView mListView = (ListView) findViewById(R.id.listViewAvis);
			mListView.setAdapter(new AdapterListCours(this, web, imageId, web2,
					moyenne, nbAvis));

			mListView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					TextView v = (TextView) arg1.findViewById(R.id.txt);
					TextView v2 = (TextView) arg1.findViewById(R.id.textid);

					Intent i = new Intent(DescriptionUEActivity.this,
							CoursActivity.class);
					i.putExtra("Nom", v.getText());
					i.putExtra("id", v2.getText());
					startActivity(i);
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void voirCours(View view) {
		Intent i = new Intent(DescriptionUEActivity.this, MainActivity.class);
		i.putExtra("Nom", title);
		startActivity(i);

	}

}
