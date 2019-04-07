package edu.feicui.newses.ui.base;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import edu.feicui.newses.R;

/**
 * Created by Administrator on 2016/12/21.
 */

public class FragmentMenu extends Fragment{
    private LinearLayout[] rls=new LinearLayout[5];
    private View.OnClickListener OnClickListener;

    /*重写onCreateView方法，设置当前的布局*/
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*利用回调中的参数LayoutInflater对象导入布局文件，并发挥此View*/
        View view=inflater.inflate(R.layout.fragment_menu_left, container,false);
        rls[0]=(LinearLayout)view.findViewById(R.id.ll_news);
        rls[1]=(LinearLayout)view.findViewById(R.id.ll_collect);
        rls[2]=(LinearLayout)view.findViewById(R.id.ll_place);
        rls[3]=(LinearLayout)view.findViewById(R.id.ll_follow);
        rls[4]=(LinearLayout)view.findViewById(R.id.ll_image);
        for (int i=0;i<rls.length;i++){
            rls[i].setOnClickListener(OnClickListener);
        }
        return view;
    }
    private View.OnClickListener onClickListener;

    {
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < rls.length; i++) {
                    rls[i].setBackgroundColor(0);
                }
                switch (v.getId()) {
                    case R.id.ll_news:
                        rls[0].setBackgroundColor(0x33c85555);
                        break;
                    case R.id.ll_collect:
                        rls[1].setBackgroundColor(0x33c85555);
                        break;
                    case R.id.ll_place:
                        rls[2].setBackgroundColor(0x33c85555);
                        break;
                    case R.id.ll_follow:
                        rls[3].setBackgroundColor(0x33c85555);
                        break;
                    case R.id.ll_image:
                        rls[4].setBackgroundColor(0x33c85555);
                        break;
                }
            }
        };
    }

}
