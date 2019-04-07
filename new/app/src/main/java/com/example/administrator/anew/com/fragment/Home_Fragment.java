package com.example.administrator.anew.com.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.example.administrator.anew.Adapater.ToolPagerAdapter;
import com.example.administrator.anew.R;
import com.example.administrator.anew.com.fragment.local.LocalVideoFragment;

import java.util.ArrayList;
import java.util.List;

public class Home_Fragment extends Fragment {

    View view;
    List<String> tabList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate( R.layout.fragment_home, container, false );
        Toolbar mToolbar = view.findViewById( R.id.about_toolbar );
        mToolbar.setNavigationIcon( R.drawable.drawer );
        mToolbar.setTitleMarginEnd( 50 );
        ((AppCompatActivity) getActivity()).setSupportActionBar( mToolbar );

        TabInfo();
        setupTabs();
        return view;
    }


    private void TabInfo() {
        tabList = new ArrayList<>();
        tabList.add( "国内" );
        tabList.add( "国际" );
        tabList.add( "财经" );
        tabList.add( "基金" );
        tabList.add( "科技" );
        tabList.add( "体育" );
        tabList.add( "NBA" );
    }

    private void setupTabs() {
        TabLayout tabLayout = view.findViewById( R.id.tabs );//TAB指示
        final ViewPager viewPager = view.findViewById( R.id.viewpager );//ViewPage
        ToolPagerAdapter toolPagerAdapter = new ToolPagerAdapter( getChildFragmentManager(), tabList );
        tabLayout.setOnTabSelectedListener( new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int potion =tab.getPosition();
                viewPager.setCurrentItem( potion,true );
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        } );
        toolPagerAdapter.notifyDataSetChanged();
        tabLayout.setupWithViewPager( viewPager, true );
        viewPager.setAdapter( toolPagerAdapter );
        tabLayout.setTabsFromPagerAdapter( toolPagerAdapter );


    }

}
