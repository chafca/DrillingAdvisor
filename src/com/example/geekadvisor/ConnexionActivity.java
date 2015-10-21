package com.example.geekadvisor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ConnexionActivity extends Activity {

	static float ra;
	static int idSupport;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_connexion);
		this.setTitle("Connexion");
		
		ra = getIntent().getExtras().getFloat("rate");
		idSupport = getIntent().getExtras().getInt("id");
		
	}
	
	public void connecter(View view){
		ra = getIntent().getExtras().getFloat("rate");
		idSupport = getIntent().getExtras().getInt("id");
		Intent intent = new Intent(this,LoginActivity.class);
		intent.putExtra("rate", ra);
		intent.putExtra("id", idSupport);
		finish();
		startActivity(intent);
		
	}
	public void inscrire(View view){
		ra = getIntent().getExtras().getFloat("rate");
		idSupport = getIntent().getExtras().getInt("id");
		Intent intent = new Intent(this,InscrireActivity.class);
		intent.putExtra("rate", ra);
		intent.putExtra("id", idSupport);
		finish();
		startActivity(intent);
		
	}
	public static final String MyPREFERENCES = "MyPrefs" ;
	SharedPreferences sharedpreferences;
	 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.connexion_menu, menu);
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
