package com.example.geekadvisor;

import geekAdvisor_pack.Avis;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.geekadvisor.MainActivity.PlaceholderFragment;

public class RatingActivity extends Activity {

	EditText txtcoment;
	RatingBar rate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rating);
		float ra=getIntent().getExtras().getFloat("rate"); 
		
		rate=(RatingBar)findViewById(R.id.rating);
		rate.setRating(ra);
		Button btnpub=(Button) findViewById(R.id.btnpublier);
		txtcoment=(EditText)findViewById(R.id.txtco);
		btnpub.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			/*
				// TODO Auto-generated method stub
				SharedPreferences sharedpreferences = getSharedPreferences
					      (LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
				 String membre = sharedpreferences.getString(LoginActivity.name, null);
				 
				 String contenu=txtcoment.getText().toString();
				try {
					String rs=new Avis().Coment(membre, contenu, idLieu, rate.getRating());
					Log.i("result",idLieu+""+rate.getRating());
					txtcoment.setText("");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//Toast.makeText(RatingActivity.this, "kkkk", Toast.LENGTH_LONG).show();
			 */
			}
		});
		
	}
}
