package edu.feicui.newses.ui.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.feicui.newses.R;

/**
 * Created by Administrator on 2016/12/22.
 */

public class FragmentMenuRight extends Fragment{
    private LinearLayout ll_unlogin;
    private ImageView iv_right;
    private TextView tv_right1;
    private TextView tv_right2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_menu_right,container,false);
        ll_unlogin=(LinearLayout)view.findViewById(R.id.ll_unlogin);
        iv_right=(ImageView)view.findViewById(R.id.iv_right);
        tv_right1=(TextView)view.findViewById(R.id.tv_right1);
        tv_right2=(TextView)view.findViewById(R.id.tv_right2);
        return view;
    }
}
