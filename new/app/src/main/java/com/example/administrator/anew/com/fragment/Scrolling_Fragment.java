package com.example.administrator.anew.com.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.anew.R;

public class Scrolling_Fragment extends Fragment {
    View view;

    public Scrolling_Fragment ScrollFragment() {
        Scrolling_Fragment ScrollFragment = new Scrolling_Fragment();
        return ScrollFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_scrolling, container, false);
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Just Click", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getContext(),"**_**",Toast.LENGTH_LONG).show();
                            }
                        }).show();
            }
        });
        return view;
    }

}