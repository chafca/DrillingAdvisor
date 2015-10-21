package com.example.geekadvisor;

import org.json.JSONObject;
import geekAdvisor_pack.Utilisateur;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity {

	private EditText mEmailView;
	private EditText mPasswordView;
	public static final String name = "nameKey";
	public static final String pass = "passwordKey";

	String result;
	SharedPreferences sharedpreferences;
	static float ra;
	static int idSupport;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		this.setTitle("Authentification");
		mEmailView = (EditText) findViewById(R.id.txtemail);
		mPasswordView = (EditText) findViewById(R.id.txtpassword);
		ra = getIntent().getExtras().getFloat("rate");
		idSupport = getIntent().getExtras().getInt("id");
	}

	/*
	 * ****************************************Connection************************
	 * ********************
	 */
	public void connect(View view) {
		mEmailView = (EditText) findViewById(R.id.txtemail);
		mPasswordView = (EditText) findViewById(R.id.txtpassword);
		Log.i("kk", "" + mEmailView.getText().toString() + ""
				+ MD5(mPasswordView.getText().toString()));
		try {
			result = new Utilisateur().login(mEmailView.getText().toString(),
					MD5(mPasswordView.getText().toString()));
			JSONObject js = new JSONObject(result);
			if (mEmailView.getText().toString()
					.equals(js.getJSONObject("utilisateur").getString("email"))
					&& MD5(mPasswordView.getText().toString()).equals(
							js.getJSONObject("utilisateur").getString(
									"password"))) {
				Log.i("result", result);
				sharedpreferences = getSharedPreferences(MyPREFERENCES,
						Context.MODE_PRIVATE);
				Editor editor = sharedpreferences.edit();
				String u = mEmailView.getText().toString();
				String p = mPasswordView.getText().toString();
				editor.putString(name, u);
				editor.putString(pass, p);
				editor.commit();
				Intent i = new Intent(LoginActivity.this, RatingActivity.class);
				i.putExtra("rate", ra);
				i.putExtra("id", idSupport);
				finish();
				startActivity(i);
			} else {
				new AlertDialog.Builder(LoginActivity.this)
						.setTitle("Alert")
						.setMessage(
								"Email d'utilisateur ou mot de passe est incorrect")
						.setNeutralButton("Fermer", null).show();

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Log.d("LL", "" + result);
	}

	/* ************************************************************************************************* */
	public static final String MyPREFERENCES = "MyPrefs";

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login_menu, menu);
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

	/* *****Méthode Pour Crypter le MDP***** */
	public String MD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
						.substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}
}
