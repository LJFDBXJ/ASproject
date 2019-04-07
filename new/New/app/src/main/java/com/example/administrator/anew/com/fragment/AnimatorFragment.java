package com.example.administrator.anew.com.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.anew.R;
import com.example.administrator.anew.Utils_Tool.MyContext;
import com.example.administrator.anew.Utils_Tool.MyService;
import com.example.administrator.anew.com.activity.MainActivity;

public class AnimatorFragment extends Fragment {
    private Button textview;
    private ImageView Animation_image;

    public AnimatorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContext.getContext().startService(new Intent(MyContext.getContext(), MyService.class));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_animator, container, false);
        textview = (Button) view.findViewById(R.id.animator_View);
        Animation_image = (ImageView) view.findViewById(R.id.new_Animation);
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        animatorStar();
        return view;
    }

    public void animatorStar() {
        ObjectAnimator rotate = ObjectAnimator.ofFloat(Animation_image, "rotation", 0f, 360f);
        ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(Animation_image, "alpha", 1f, 0f, 1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(fadeInOut).with(rotate);
        animSet.setDuration(4000);
        animSet.start();
        animSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        MyContext.getContext().stopService(new Intent(MyContext.getContext(), MyService.class));
    }
}
