package edu.feicui.newses.model.dao;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import edu.feicui.newses.R;

/**
 * Created by Administrator on 2016/12/19.
 */

public class DotMark extends LinearLayout {
    private Context context;
    private int pagercount;

    public DotMark(Context context, int pagercount) {
        super(context);
        this.context=context;
        this.pagercount=pagercount;
        initDot();
    }
    public void initDot(){
        LayoutParams lp=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,1f);
        lp.setMargins(2,2,2,2);
        for (int i=0;i<pagercount;i++){
            ImageView iv=new ImageView(context);
            iv.setLayoutParams(lp);
            if (i==0){
                iv.setImageResource(R.drawable.a4);
            }else {
                iv.setImageResource(R.drawable.userbg);
            }
            this.addView(iv);
        }
    }
    public void currentPostion(int pagercount){
        for (int i=0;i<this.getChildCount();i++){
            ImageView imageView=(ImageView)this.getChildAt(i);
            if (pagercount==i){
                imageView.setImageResource(R.drawable.a4);
            }else {
                imageView.setImageResource(R.drawable.userbg);
            }
        }
    }
}
