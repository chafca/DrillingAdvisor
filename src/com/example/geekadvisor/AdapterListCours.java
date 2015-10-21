package com.example.geekadvisor;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class AdapterListCours extends ArrayAdapter<String> {
	private final Context context;
	private final ArrayList<String> web;
	private final ArrayList<String> web2;
	private final ArrayList<Bitmap> imageId;
	private final ArrayList<Float> moyenne ;
	private final ArrayList<String> nbAvis ;

	public AdapterListCours(Context context, ArrayList<String> web, ArrayList<Bitmap> imageId, ArrayList<String> web2, ArrayList<Float> moyenne, ArrayList<String> nbAvis) {
		super(context, R.layout.adapter_list_cours, web);
		this.context = context;
		this.web = web;
		this.web2 = web2;
		this.imageId = imageId;
		this.moyenne = moyenne;
		this.nbAvis = nbAvis;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				  .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.adapter_list_cours, null, true);
		TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
		TextView txtId = (TextView) rowView.findViewById(R.id.textid);
		TextView nombreAvis = (TextView) rowView.findViewById(R.id.nbavis);
		RatingBar moyenneNote = (RatingBar) rowView.findViewById(R.id.moyenne);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
		txtTitle.setText(web.get(position));
		txtId.setText(web2.get(position));
		nombreAvis.setText(nbAvis.get(position));
		//if(moyenne.get(position) != 0){
			moyenneNote.setRating(moyenne.get(position));
		//}

		imageView.setImageBitmap(imageId.get(position));
		return rowView;
	}
}
