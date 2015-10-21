package com.example.geekadvisor;

import java.io.File;
import java.io.IOException;
import geekAdvisor_pack.Support;
import geekAdvisor_pack.Utilisateur;

import org.json.JSONObject;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RatingBar.OnRatingBarChangeListener;

public class CoursActivity extends Activity {
	String title;
	TextView tvDescription;
	static String lien,pdfName;
	RatingBar ratingBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_cours);
		
		title=getIntent().getExtras().get("Nom").toString();
		this.setTitle(title);
		tvDescription = (TextView)findViewById(R.id.tvDescription);
		ratingBar=(RatingBar)findViewById(R.id.rating);
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
		ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar arg0, float arg1, boolean arg2) {
				// TODO Auto-generated method stub
				float ra=ratingBar.getRating();
				Intent i =new Intent(CoursActivity.this,RatingActivity.class);
				i.putExtra("rate", ra);
				startActivity(i);
			}
		});
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
            Toast.makeText(CoursActivity.this, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
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
