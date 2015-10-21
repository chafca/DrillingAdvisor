package com.example.geekadvisor;

import java.io.File;
import java.io.IOException;
import geekAdvisor_pack.Avis;
import geekAdvisor_pack.Support;
import geekAdvisor_pack.Utilisateur;
import org.json.JSONObject;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RatingBar.OnRatingBarChangeListener;

public class LivresActivity extends Activity {
	String title,id;
	int idSupport;
	TextView tvDescription;
	static String lien,pdfName;
	RatingBar ratingBar;
	String[] contenu;
	String[] utilisateur;
	String[] date;
	int[] rateId;
	public static final String MyPREFERENCES = "MyPrefs" ;
	SharedPreferences sharedpreferences;
	String jsResultAvis;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_livres);
		
		idSupport = Integer.parseInt(getIntent().getExtras().get("id").toString());
		title = getIntent().getExtras().get("Nom").toString();
		this.setTitle(title);
		tvDescription = (TextView)findViewById(R.id.tvDescription);
		ratingBar = (RatingBar)findViewById(R.id.rating);
		try {
			String jsResult = new Support().getSupportName(title);
			JSONObject jObject = new JSONObject(jsResult);
			JSONObject json_data = jObject.optJSONObject(""+ 0);
			String desc = json_data.getJSONObject("support").getString("Description");	
			lien = json_data.getJSONObject("support").getString("Lien");
			String s[] = lien.split("/");
			pdfName = s[2];
			tvDescription.setText(desc);
			
			Log.i("resultat", json_data.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			sharedpreferences=getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);
			ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
				@Override
				public void onRatingChanged(RatingBar arg0, float arg1, boolean arg2) {
					
					float ra = ratingBar.getRating();
				 	if (sharedpreferences.contains(LoginActivity.name) && sharedpreferences.contains(LoginActivity.pass)){
				 	 
					 	Intent i = new Intent(LivresActivity.this,RatingActivity.class);	 
					 	i.putExtra("rate", ra);
					 	i.putExtra("id", idSupport);
					 	startActivity(i);
				 	 
				 	}else{
					 	Intent i =new Intent(LivresActivity.this,ConnexionActivity.class);
					 	i.putExtra("rate", ra);
					 	i.putExtra("id", idSupport);
					 	startActivity(i);
				 	}
					
					/*
					 * float ra = ratingBar.getRating();
					Intent i =new Intent(CoursActivity.this,ConnexionActivity.class);
					i.putExtra("rate", ra);
					i.putExtra("id", idSupport);
					startActivity(i);
					*/
				}
			});
			
			/* ************************** */
			try {
				this.remplir();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/* ************************** */
	}
	
	public void remplir() throws Exception{
		
		jsResultAvis = new Avis().listAvis(idSupport);
		Log.i("res", jsResultAvis);
		JSONObject jObjec = new JSONObject(jsResultAvis);
		contenu = new String[jObjec.length()];
		utilisateur = new String[jObjec.length()];
		rateId = new int[jObjec.length()];
		date = new String[jObjec.length()];
		
		for (int i = 0; i < jObjec.length(); i++) {
			JSONObject json_dataa = jObjec.optJSONObject("" + i);
			String util = json_dataa.getJSONObject("avis")
					.getString("utilisateur");
			String contenus = json_dataa.getJSONObject("avis")
					.getString("contenu");
			int rate = json_dataa.getJSONObject("avis").getInt("rate");
			String datee = json_dataa.getJSONObject("avis")
					.getString("date");
			
			contenu[i]=contenus;
			utilisateur[i]=util;
			rateId[i]=rate;
			date[i] = datee;
		}
		ListView mListView = (ListView)findViewById(R.id.listViewAvis);
		mListView.setAdapter(new AdapterAvis(LivresActivity.this, contenu, utilisateur, rateId, date));
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		try {
			this.remplir();
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.onResume();
	}
	
    public void download(View v)
    {  
    	new DownloadFile().execute("http://"+Utilisateur.ip+"/"+lien, pdfName);
        this.view(v);
    }

    public void view(View v)
    {
        File pdfFile = new File(Environment.getExternalStorageDirectory() + "/testthreepdf/" + pdfName);  // -> filename = maven.pdf
        Uri path = Uri.fromFile(pdfFile);
        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(path, "application/pdf");
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        try{
            startActivity(pdfIntent);
        }catch(ActivityNotFoundException e){
            Toast.makeText(LivresActivity.this, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
        }
    }
    private class DownloadFile extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            String fileUrl = strings[0];  
            String fileName = strings[1];  
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(extStorageDirectory, "testthreepdf");
            folder.mkdir();

            File pdfFile = new File(folder, fileName);

            try{
                pdfFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
            FileDownloader.downloadFile(fileUrl, pdfFile);
            return null;
        }
    }

}
