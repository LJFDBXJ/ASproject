package com.example.administrator.anew.com.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.anew.R;
import com.example.administrator.anew.Utils_Tool.Piano_Sound;

public class Piano_Fragment extends Fragment implements View.OnClickListener{
    Button button1;
    Button Button1;
    Button Button2;
    Button Button3;
    Button Button4;
    Button Button5;
    Button Button6;

    public static Piano_Fragment newInstance(String param1, String param2) {
        Piano_Fragment fragment = new Piano_Fragment();
        Bundle args = new Bundle();
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate( R.layout.fragment_piano, container, false );
        int[] resIds={R.raw.dao,R.raw.re,R.raw.mi,R.raw.fa,R.raw.sol,R.raw.la,R.raw.si};
        Piano_Sound.initSound(7, resIds);
        Piano_Sound.setSoundOnOff(true);
        button1=view. findViewById(R.id.button1);
        Button1=view. findViewById(R.id.Button01);
        Button2=view. findViewById(R.id.Button02);
        Button3=view. findViewById(R.id.Button03);
        Button4=view. findViewById(R.id.Button04);
        Button5=view. findViewById(R.id.Button05);
        Button6=view. findViewById(R.id.Button06);

        button1.setOnClickListener(this);
        Button1.setOnClickListener(this);
        Button2.setOnClickListener(this);
        Button3.setOnClickListener(this);
        Button4.setOnClickListener(this);
        Button5.setOnClickListener(this);
        Button6.setOnClickListener(this);
        return view ;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                Piano_Sound.playSound(0);
                break;
            case R.id.Button01:
                Piano_Sound.playSound(1);
                break;
            case R.id.Button02:
                Piano_Sound.playSound(2);
                break;
            case R.id.Button03:
                Piano_Sound.playSound(3);
                break;
            case R.id.Button04:
                Piano_Sound.playSound(4);
                break;
            case R.id.Button05:
                Piano_Sound.playSound(5);
                break;
            case R.id.Button06:
                Piano_Sound.playSound(6);
                break;
        }
    }
}
