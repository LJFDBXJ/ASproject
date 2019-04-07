package com.example.administrator.anew.com.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.example.administrator.anew.R;
public class VebView_Fragment extends AppCompatActivity {
    WebView webView;
    SwipeRefreshLayout refresh;
    public VebView_Fragment() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_veb_view);
        webView =  findViewById(R.id.news_Web);
        refresh = findViewById(R.id.Web_fresh);
        refresh.setColorSchemeResources(R.color.colorPrimary);
        Intent intent=getIntent();
        String mParam1=intent.getStringExtra("kk");
        webView.loadUrl(mParam1);

//        //第二种方法——放大缩小的按钮
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setJavaScriptEnabled(true);
        //第一种——固定缩小多少
//        webView.setInitialScale(55);
        //第三种——右边和下边都有滚动条
//     webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        //第四种——有比例的缩放——很好看
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
//         //右边和下边有滚动条
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.reload();
                refresh.setRefreshing(false);
            }
        });
        refresh.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                return true;
            }
        });
    }
}

