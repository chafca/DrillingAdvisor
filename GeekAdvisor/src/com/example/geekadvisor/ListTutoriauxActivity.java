package com.example.geekadvisor;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListTutoriauxActivity extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tutoriaux, container, false);

        // Demonstration of a collection-browsing activity.
        rootView.findViewById(R.id.imageButtonVJava);
        rootView.findViewById(R.id.imageButtonVXml);
               

        return rootView;
    }
}
