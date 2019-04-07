package edu.feicui.newses.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.EdgeEffectCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import edu.feicui.newses.R;
import edu.feicui.newses.model.dao.DotMark;
import edu.feicui.newses.ui.adapter.Guide_Adapter;
import edu.feicui.newses.ui.base.MyBaseActivity;

/**
 * Created by Administrator on 2016/12/19.
 */

public class Activity_Guide extends MyBaseActivity {
    private ViewPager viewpager;
    private LinearLayout ll_guide;
    private View btn_guide_jump;
    private View btn_guide_start;
    private DotMark dotMark;
    private EdgeEffectCompat rightEdge;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_guide);
        init();
        initData();
        setListener();
    }

    private void init() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        ll_guide = (LinearLayout) findViewById(R.id.ll_guide);
        btn_guide_jump = (Button) findViewById(R.id.btn_guide_jump);
        btn_guide_start = (Button) findViewById(R.id.btn_guide_start);
        try {
            Field leftfield = viewpager.getClass().getDeclaredField("leftEdge");
            Field rightfield = viewpager.getClass().getDeclaredField("rightEdge");
            if (leftfield != null && rightfield != null) {
                rightfield.setAccessible(true);
                rightEdge=(EdgeEffectCompat)rightfield.get(viewpager);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initData() {
        List<View> views = new ArrayList<>();
        LayoutInflater inflater = LayoutInflater.from(this);
        View pager1 = inflater.inflate(R.layout.guide_pager1, null);
        btn_guide_jump = pager1.findViewById(R.id.btn_guide_jump);
        views.add(pager1);
        View pager2 = inflater.inflate(R.layout.guide_pager2, null);
        views.add(pager2);
        View pager3 = inflater.inflate(R.layout.guide_pager3, null);
        views.add(pager3);
        View pager4 = inflater.inflate(R.layout.guide_pager4, null);
        btn_guide_start = pager4.findViewById(R.id.btn_guide_start);
        views.add(pager4);
        dotMark = new DotMark(this, views.size());
        ll_guide.addView(dotMark);
        Guide_Adapter adapter = new Guide_Adapter(views);
        viewpager.setAdapter(adapter);
    }

    private void setListener() {
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                dotMark.currentPostion(position);

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        btn_guide_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Guide.this, Activity_anim.class);
                startActivity(intent);
            }
        });
        btn_guide_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Guide.this, Activity_anim.class);
                startActivity(intent);
            }
        });
    }
}
