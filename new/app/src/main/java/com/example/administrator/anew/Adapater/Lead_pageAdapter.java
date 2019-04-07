package com.example.administrator.anew.Adapater;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/4.
 */
public class Lead_pageAdapter extends PagerAdapter {
    List<View> list = new ArrayList<>();

    public void add(View view) {
        list.add(view);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }



    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        position %= list.size();
        if (position < 0) {
            position = list.size() + position;
        }
        View view = list.get(position);
        //如果View已经在之前添加了一个父组件，则必须先romove,否则会抛出IllegalStateException.
        ViewParent vp = view.getParent();
        if (vp != null) {
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView(view);
        }
        container.addView(view);
        return view;
    }

}
