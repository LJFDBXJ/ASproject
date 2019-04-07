package edu.feicui.newses.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import edu.feicui.newses.R;
import edu.feicui.newses.service.HorizontalListView;
import edu.feicui.newses.ui.adapter.HorizontalListViewAdapter;
import edu.feicui.newses.ui.base.Activity_fragment;
import edu.feicui.newses.ui.base.FragmentMenu;
import edu.feicui.newses.ui.base.FragmentMenuRight;
import edu.feicui.newses.ui.base.MyBaseActivity;

public class MainActivity extends MyBaseActivity{

    String path = "http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private FragmentMenu fragmentleft;
    private FragmentMenuRight fragmentMenuRight;
    private SlidingMenu menu;
    private FrameLayout fl_fragment;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;
    private HorizontalListView horizon_listview;
    private HorizontalListViewAdapter adapter;
    private ImageView iv_left;
    private ImageView iv_right;

    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_main);
        actions(R.drawable.btn_homeasup_default, "新闻",null);
        fl_fragment=(FrameLayout)findViewById(R.id.fl_fragment);
        fragment=new Activity_fragment();
        fragmentleft=new FragmentMenu();
        fragmentMenuRight=new FragmentMenuRight();
        getFragmentManager().beginTransaction().replace(R.id.fl_fragment,fragment).commit();
        init();
        initData();
        fragmentleft();
    }


    private void init() {
        horizon_listview=(HorizontalListView)findViewById(R.id.horizon_listview);
        String[] titles={"社会","军事"};
        adapter=new HorizontalListViewAdapter(this,titles);
        iv_left=(ImageView)findViewById(R.id.iv_left);
        iv_right=(ImageView)findViewById(R.id.iv_right);
        horizon_listview.setAdapter(adapter);
        horizon_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.notifyDataSetChanged();
            }
        });

    }
    private void initData() {

        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (menu != null && menu.isMenuShowing()) {
                    menu.showContent();
                } else if (menu != null) {
                    menu.showMenu();
                }

            }
        });

        iv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (menu != null && menu.isMenuShowing()) {
                    menu.showContent();
                } else if (menu != null) {
                    menu.showSecondaryMenu();
                }

            }
        });

    }

    private void fragmentleft() {
        menu=new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT_RIGHT);
        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        // 设置滑动菜单视图的宽度
        menu.setBehindOffsetRes(R.dimen.left_width);
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.5f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //为侧滑菜单设置布局
        menu.setMenu(R.layout.fragment_menu_left);
        menu.setSecondaryMenu(R.layout.fragment_menu_right);
        menu.showContent();
        menu.showSecondaryMenu();
        menu.isMenuShowing();

    }

}
