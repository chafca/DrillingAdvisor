package com.example.geekadvisor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


@SuppressLint("NewApi") public class AccueilActivity extends Activity {
	
	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().hide();
		setContentView(R.layout.activity_accueil);
		//On prend l'image d'acceuil et on la passe au Thread Compteur qui va l'afficher pendant 3s
		// et qui va nous direger vers l'activity "ListUEActivity" qui affiche la listes des UEs
		Thread compteur = new Thread (){
			@Override
			public void run(){
				try{
					sleep(3000);
				}catch(InterruptedException e){
				e.printStackTrace();	
				}finally {
					Intent intent=new Intent(AccueilActivity.this,ListUEActivity.class);
					startActivity(intent);
				}
			}
		};
		compteur.start();
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	public void open(View view){
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
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

	private void openSetting() {
		// TODO Auto-generated method stub
		
	}

	private void openSearch() {
		// TODO Auto-generated method stub
		
	}

	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_accueil, container,
					false);
			return rootView;
		}
	}

}
