package com.example.geekadvisor;

import java.net.URL;

import geekAdvisor_pack.Ue;
import geekAdvisor_pack.Utilisateur;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class ListUEActivity extends Activity {

	// On passe a cette variable l'adresse IP du serveur
	static String urlImage = " http://" + Utilisateur.ip + "/ ";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_ue);

		try {
			// On récupére les données de la class "UE" par la méthode
			// "getAllUE"
			String jsResult = new Ue().getAllUE();
			// Les données récupéres son au format JSON on les affectes a
			// l'objet JSON pour les lires
			JSONObject jObject = new JSONObject(jsResult);
			// Tableau de string qui va recevoir une liste d'objet JSON
			String[] web = new String[jObject.length()];
			// Tableau de Bitmap qui va recevoir une liste d'objet JSON sous
			// format d'image qui vas
			// les lires bits à bits
			Bitmap[] imageId = new Bitmap[jObject.length()];

			for (int i = 0; i < jObject.length(); i++) {
				JSONObject json_data = jObject.optJSONObject("" + i);
				// title et src recoivent les "Noms" et les "Images" de chaque
				// UEs
				String title = json_data.getJSONObject("ue").getString("Nom");
				String src = json_data.getJSONObject("ue").getString("Image");

				URL url = new URL(urlImage + "" + src);
				Bitmap image = BitmapFactory.decodeStream(url.openConnection()
						.getInputStream());
				// les deux tableau recoivent le nom et l'image de chaque UE
				web[i] = title;
				imageId[i] = image;
			}
			Log.i("res", "Amine");
			// On envoi les deux tableau à la class "AdapterListUe" qui va se
			// charger d'affiche leurs
			// données dans une liste
			ListView mListView = (ListView) findViewById(R.id.listView2);
			mListView.setAdapter(new AdapterListUe(this, web, imageId));
			// La méthode "OnItemClick" va se génére dès qu'on clik sur un item
			// de la listView
			mListView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					TextView v = (TextView) arg1.findViewById(R.id.txt);
					try {
						// Qu'on on click sur un item, on va passer dans la
						// class "DescriptionUEActivity"
						// qui récupére le nom de l'UE selectionner al'aide du
						// "putExtra"
						Intent i = new Intent(ListUEActivity.this,
								DescriptionUEActivity.class);
						i.putExtra("Nom", v.getText());
						startActivity(i);
					} catch (Exception e) {
						Toast.makeText(getApplicationContext(), e.getMessage(),
								Toast.LENGTH_LONG).show();
					}
					//
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressLint("NewApi")
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.list_ue, menu);

		// Cette méthode permet d'effectuer une recherche on tapant le nom d'une
		// UE
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.search)
				.getActionView();
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));
		searchView.setQueryHint("Chercher une UE..");

		searchView
				.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
					public boolean onQueryTextChange(String newText) {
						return false;
					}

					public boolean onQueryTextSubmit(String query) {
						if (query.length() != 0) {

							try {
								String rs = new Ue().getUEName(query);
								JSONObject jObject = new JSONObject(rs);
								JSONObject js = jObject.optJSONObject("" + 0);
								String nomUe = js.getJSONObject("ue")
										.getString("Nom");
								if (query.equals(nomUe)) {
									Intent intent = new Intent(
											ListUEActivity.this,
											DescriptionUEActivity.class);
									intent.putExtra("Nom", query);
									startActivity(intent);
								} else {
									new AlertDialog.Builder(ListUEActivity.this)
											.setTitle("Alert")
											.setMessage(
													"le nom de l'Ue est incorrect")
											.setNeutralButton("Fermer", null)
											.show();

								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							return true;
						}
						return false;
					}
				});
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
