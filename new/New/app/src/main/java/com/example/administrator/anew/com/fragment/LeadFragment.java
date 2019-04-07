package com.example.administrator.anew.com.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.administrator.anew.Adapater.Lead_pageAdapter;
import com.example.administrator.anew.R;

public class LeadFragment extends Fragment implements ViewPager.OnPageChangeListener {
    ViewPager viewpager;
    Lead_pageAdapter leadAdapter;

    ImageView[] image = new ImageView[3];
    ImageView[] myimage = new ImageView[3];
    int[] r = {R.mipmap.yin1, R.mipmap.yin2, R.mipmap.yin3
            , R.id.imageView3, R.id.imageView2, R.id.imageView
            , R.drawable.adware_style_default, R.drawable.adware_style_default, R.drawable.adware_style_selected};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lead_fragment, container, false);
        viewpager = (ViewPager) view.findViewById(R.id.Lead_ViewPager);
        leadAdapter = new Lead_pageAdapter();

        for (int i = 0; i < 3; i++) {
            View inflate1 = inflater.inflate(R.layout.inflate_lead_image, container, false);
            image[i] = (ImageView) inflate1.findViewById(R.id.lead_singer_image);
            image[i].setImageResource(r[i]);
            leadAdapter.add(inflate1);
            myimage[i] = (ImageView) view.findViewById(r[r.length - i - 4]);
            myimage[i].setImageResource(r[r.length - i - 1]);
        }
        viewpager.setAdapter(leadAdapter);
        viewpager.setOnPageChangeListener(this);
        return view;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < image.length; i++) {
            image[i].setImageResource(R.drawable.adware_style_default);
        }
        image[position].setImageResource(R.drawable.adware_style_selected);
        if (position == 2) {
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
