package com.example.administrator.anew.Adapater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.example.administrator.anew.com.fragment.AboutFragment;

import java.util.ArrayList;
import java.util.List;

public class ToolPagerAdapter extends FragmentPagerAdapter {
    private List<String> tags;
    List<String> list;
    List<AboutFragment> aboutFragmentList;
    private FragmentManager fragmentManager;

    public ToolPagerAdapter(FragmentManager fm, List mlist, List<AboutFragment> aboutlist) {
        super(fm);
        this.list = mlist;
        this.fragmentManager = fm;
        this.tags = new ArrayList<>();
        aboutFragmentList = aboutlist;
    }
    //http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20
    @Override
    public Fragment getItem(int position) {

        return aboutFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);

    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }


    public void setNewFragments(List<AboutFragment> fragments) {
        if (this.tags != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            for (int i = 0; i < tags.size(); i++) {
                fragmentTransaction.remove(fragmentManager.findFragmentByTag(tags.get(i)));
            }
            fragmentTransaction.commit();
            fragmentManager.executePendingTransactions();
            tags.clear();
        }
        this.aboutFragmentList = fragments;
        notifyDataSetChanged();
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        tags.add(makeFragmentName(container.getId(), getItemId(position)));
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        this.fragmentManager.beginTransaction().show(fragment).commit();
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Fragment fragment = aboutFragmentList.get(position);
        fragmentManager.beginTransaction().hide(fragment).commit();
    }

    private static String makeFragmentName(int viewId, long id) {
        return "android:switcher:" + viewId + ":" + id;
    }
}
