package com.example.geekadvisor;

import geekAdvisor_pack.Avis;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class RatingActivity extends Activity {

	EditText txtcoment;
	RatingBar rate;
	int idSupport;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rating);
		
		idSupport = getIntent().getExtras().getInt("id");
		Log.i("result",idSupport+"");
		
		try {
			float ra=getIntent().getExtras().getFloat("rate"); 
			rate=(RatingBar)findViewById(R.id.rating);
			rate.setRating(ra);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Button btnpub=(Button) findViewById(R.id.btnpublier);
		Button btnannuler=(Button) findViewById(R.id.btnretour);
		txtcoment=(EditText)findViewById(R.id.txtco);
		
		btnpub.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences sharedpreferences = getSharedPreferences
					      (LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
				 String utilisateur = sharedpreferences.getString(LoginActivity.name, null);
				 String contenu=txtcoment.getText().toString();
				try {
					String rs=new Avis().Coment(utilisateur, contenu, idSupport, rate.getRating());
					Log.i("result",idSupport+" "+rate.getRating()+" "+contenu+" "+utilisateur);
					txtcoment.setText("");
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
	}
	
	public void retour(View v){
		this.finish();
	}
}
