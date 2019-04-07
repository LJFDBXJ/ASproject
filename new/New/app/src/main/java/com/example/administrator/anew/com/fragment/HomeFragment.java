package com.example.administrator.anew.com.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.anew.Adapater.ToolPagerAdapter;
import com.example.administrator.anew.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    String a = "http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";
    String b = "http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=2&dir=1&nid=1&stamp=20140321&cnt=20";
    String c = "http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=3&dir=1&nid=1&stamp=20140321&cnt=20";
    String d = "http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=4&dir=1&nid=1&stamp=20140321&cnt=20";
    String e = "http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=5&dir=1&nid=1&stamp=20140321&cnt=20";
    String f = "http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=7&dir=1&nid=1&stamp=20140321&cnt=20";
    String g = "http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=8&dir=1&nid=1&stamp=20140321&cnt=20";

    View view;
    List<String> tabList;
    List<AboutFragment> fragmentsList;
    @Override

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        TabList();
        AboutFragmentList();
        setupTabs();
        return view;
    }

    private void AboutFragmentList() {
        fragmentsList =new ArrayList<>();
        fragmentsList.add(AboutFragment.newInstance(a));
        fragmentsList.add(AboutFragment.newInstance(b));
        fragmentsList.add(AboutFragment.newInstance(c));
        fragmentsList.add(AboutFragment.newInstance(d));
        fragmentsList.add(AboutFragment.newInstance(e));
        fragmentsList.add(AboutFragment.newInstance(f));
        fragmentsList.add(AboutFragment.newInstance(g));
    }

    private void TabList() {
        tabList = new ArrayList<>();
        tabList.add("国内");
        tabList.add("国际");
        tabList.add("财经");
        tabList.add("基金");
        tabList.add("科技");
        tabList.add("体育");
        tabList.add("NBA");
    }

    private void setupTabs() {
        final TabLayout tabLayout = view.findViewById(R.id.tabs);//TAB指示
        final ViewPager viewPager = view.findViewById(R.id.viewpager);//ViewPage

        ToolPagerAdapter toolPagerAdapter = new ToolPagerAdapter(getFragmentManager(), tabList, fragmentsList);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(toolPagerAdapter);
        viewPager.setAdapter(toolPagerAdapter);
    }
}
