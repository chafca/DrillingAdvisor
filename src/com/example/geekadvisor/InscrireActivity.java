package com.example.geekadvisor;

import geekAdvisor_pack.Utilisateur;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class InscrireActivity extends Activity {

	EditText txtemail, txtpassword, txtnom, txtprenom;
	SharedPreferences sharedpreferences;
	public static final String name = "nameKey";
	public static final String pass = "passwordKey";
	boolean cancel = false;
	View focusView = null;
	String result;
	static float ra;
	static int idSupport;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inscrire);
		this.setTitle("Inscription");
		ra = getIntent().getExtras().getFloat("rate");
		idSupport = getIntent().getExtras().getInt("id");
	}
	/* ******************************INSCRIPTION************************************************* */
// Méthode pour récupérer les données du formulaire de l'inscription et les enregistres dans la BDD
	public void enregistrer(View view) {
		txtemail = (EditText) findViewById(R.id.txtemail);
		txtpassword = (EditText) findViewById(R.id.txtpassword);
		txtnom = (EditText) findViewById(R.id.txtNom);
		txtprenom = (EditText) findViewById(R.id.txtPrenom);

		try {

			Utilisateur util = new Utilisateur(
					txtpassword.getText().toString(), txtemail.getText()
							.toString(), txtnom.getText().toString(), txtprenom
							.getText().toString());
			result = new Utilisateur().inscrire(util);
			Log.i("result", result);
			AlertDialog a = new AlertDialog.Builder(this).setTitle("Alert")
					.setMessage("l'inscription est bien effectuée")
					.setNeutralButton("Fermer", new OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							sharedpreferences = getSharedPreferences(
									MyPREFERENCES, Context.MODE_PRIVATE);
							Editor editor = sharedpreferences.edit();
							String u = txtemail.getText().toString();
							String p = txtpassword.getText().toString();
							editor.putString(name, u);
							editor.putString(pass, p);
							editor.commit();

							Intent i = new Intent(InscrireActivity.this,
									RatingActivity.class);
							i.putExtra("result", result);
							finish();
							startActivity(i);
							i.putExtra("rate", ra);
							i.putExtra("id", idSupport);
						}
					}).show();
		} catch (Exception ex) {
			Log.e("tag", ex.getMessage());
		}

	}
/* ***************************************************************************************************** */
	public static final String MyPREFERENCES = "MyPrefs";

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.inscrire_menu, menu);
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
