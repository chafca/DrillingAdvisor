package com.example.geekadvisor;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

public class AdapterAvis extends ArrayAdapter<String>  {

	private final Activity context;
	private final String[] contenu;
	private final String[] utilisateur;
	private final int[] ratId;
	private final String[] date;
	
	public AdapterAvis(Activity context, String[] contenu,String[] utilisateur ,int[] ratId, String[] date) {
		super(context, R.layout.adapter_avis, contenu);
		this.context = context;
		this.contenu = contenu;
		this.utilisateur = utilisateur;
		this.ratId = ratId;
		this.date = date;
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.adapter_avis, null, true);
		TextView txtUser = (TextView) rowView.findViewById(R.id.txtUser);
		TextView txtDate = (TextView) rowView.findViewById(R.id.txtDate);
		//ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
		TextView txtComment = (TextView) rowView.findViewById(R.id.txtcoment);
		RatingBar ra=(RatingBar) rowView.findViewById(R.id.ratingBarUser);
		ra.setRating(ratId[position]);
		txtComment.setText(contenu[position]);
		txtUser.setText(utilisateur[position]);
		txtDate.setText(date[position]);
		//txtTitle.setText(web[position]);
		//imageView.setImageResource(imageId[position]);
		return rowView;
	}
}
