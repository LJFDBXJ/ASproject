package com.example.administrator.anew.com.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.anew.R;
import com.example.administrator.anew.com.fragment.AnimatorFragment;
import com.example.administrator.anew.com.fragment.LeadFragment;

public class lead_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead_);
        getSupportFragmentManager().beginTransaction().add(R.id.activity_lead,new AnimatorFragment()).commit();

    }
}
