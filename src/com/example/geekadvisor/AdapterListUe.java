package com.example.geekadvisor;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterListUe extends ArrayAdapter<String>  {

	private final Activity context;
	private final String[] web;
	private final Bitmap[] imageId;

	public AdapterListUe(Activity context, String[] web, Bitmap[] imageId) {
		super(context, R.layout.adapter_list_ue, web);
		this.context = context;
		this.web = web;
		this.imageId = imageId;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.adapter_list_ue, null, true);
		TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
		txtTitle.setText(web[position]);
		imageView.setImageBitmap(imageId[position]);
		return rowView;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imageId.length;
	}

}

