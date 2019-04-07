package edu.feicui.newses.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import edu.feicui.newses.R;
import edu.feicui.newses.ui.base.MyBaseActivity;

/**
 * Created by Administrator on 2016/12/19.
 */

public class Activity_anim extends MyBaseActivity {
    private ImageView iv_anim;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_anim);
        init();
        initData();
    }

    private void init() {
        iv_anim=(ImageView)findViewById(R.id.iv_anim);
    }

    private void initData() {
        Animation animation= new AnimationUtils().loadAnimation(
                Activity_anim.this, R.anim.anim_set);
        iv_anim.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent=new Intent(Activity_anim.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
