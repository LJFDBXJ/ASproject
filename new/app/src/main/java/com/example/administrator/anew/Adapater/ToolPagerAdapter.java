package com.example.administrator.anew.Adapater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.example.administrator.anew.com.fragment.About_Fragment;

import java.util.List;

public class ToolPagerAdapter extends FragmentPagerAdapter {
    private List<String> list;
    private boolean flagg = true;


    public ToolPagerAdapter(FragmentManager fm, List mlist) {
        super( fm );
        this.list = mlist;
    }

//    @Override
//    public int getItemPosition(Object object) {
//        return POSITION_NONE;
//    }

    @Override
    public Fragment getItem(int position) {
//        switch (position) {
//            case 0:
//                return About_Fragment.newInstance( a );
//            default:

        return About_Fragment.newInstance( position );

//        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        String TAG = makeFragmentName( container.getId(), position );
        Fragment fragment = (Fragment) super.instantiateItem( container, position );
//
//        if (getItem( position ) == fragment)
//            return fragment;
//        FragmentTransaction mft = fragmentManager.beginTransaction();
//        if (position == 0 && flagg) {
//            mft.remove( fragment );
//            fragment = maboutList.get( 0 );
//            mft.add( container.getId(), fragment, TAG );
//            flagg = false;
//            mft.attach( fragment );
//        }
//        mft.show( fragment ).commit();
        return fragment;
    }
//
//    public void setNewFragments(List<About_Fragment> fragments) {
//        if (this.tags != null) {
//            FragmentTransaction ft = fragmentManager.beginTransaction();
//            for (int i = 0; i < tags.size(); i++) {
//                ft.remove( fragmentManager.findFragmentByTag( tags.get( i ) ) );
//            }
//            ft.commit();
//            fragmentManager.executePendingTransactions();
//            tags.clear();
//        }
//        this.maboutList = fragments;
//        notifyDataSetChanged();
//    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get( position );

    }

//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        Fragment fragment = (Fragment) object;
//        fragmentManager.beginTransaction().hide( fragment ).commit();
//        super.destroyItem( container, position, object );
//    }
//    private static String makeFragmentName(int viewId, long id) {
//        return "android:switcher:" + viewId + ":" + id;
//    }
}
