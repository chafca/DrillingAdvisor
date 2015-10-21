package com.example.geekadvisor;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterListLivresActivity extends ArrayAdapter<String> {
	private final Context context;
	private final ArrayList<String> web;
	private final ArrayList<String> web2;
	private final ArrayList<String> web3;
	private final ArrayList<Integer> imageId;

	public AdapterListLivresActivity(Context context, ArrayList<String> web, ArrayList<Integer> imageId, ArrayList<String> web2,ArrayList<String> web3) {
		super(context, R.layout.activity_adapter_list_livres, web);
		this.context = context;
		this.web = web;
		this.web2 = web2;
		this.web3 = web3;
		this.imageId = imageId;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				  .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.activity_adapter_list_livres, null, true);
		TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
		TextView txtId = (TextView) rowView.findViewById(R.id.textid);
		TextView txtAut = (TextView) rowView.findViewById(R.id.txtauteur);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
		txtTitle.setText(web.get(position));
		txtId.setText(web2.get(position));
		txtAut.setText(web3.get(position));
		imageView.setImageResource(imageId.get(position));
		return rowView;
	}
}

