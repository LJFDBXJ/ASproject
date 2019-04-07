package edu.feicui.newses.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import edu.feicui.newses.R;
import edu.feicui.newses.ui.base.MyBaseActivity;

/**
 * Created by Administrator on 2016/12/17.
 */

public class Activity_show extends MyBaseActivity {
    private ProgressBar pb_web;
    private WebView wv_web;
    private Intent intent;
    private WebViewClient viewClient;
    private WebChromeClient chromeClient;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        init();
        initData();
        initLisner();
    }

    private void init() {
        intent=this.getIntent();
        pb_web=(ProgressBar)findViewById(R.id.pb_web);
        wv_web=(WebView)findViewById(R.id.wv_web);
    }

    private void initData() {
        /**缩小放大方法*/
        viewClient=new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                wv_web.loadUrl(url);
                return true;
            }
        };
        /**进度条设置；。*/
        chromeClient=new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                pb_web.setProgress(newProgress);
                if (pb_web.getProgress()==100){
                    pb_web.setVisibility(View.INVISIBLE);
                }
            }
        };

    }

    private void initLisner() {
//        设置 webview 的属性缓存模式离线
        wv_web.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        wv_web.getSettings().setBuiltInZoomControls(true);
//        设置加载全部后的监听
        wv_web.setWebViewClient(viewClient);
//        设置当加载时的监听
        wv_web.setWebChromeClient(chromeClient);
//        设置路径
        wv_web.loadUrl(intent.getStringExtra("link"));

        wv_web.getSettings().setJavaScriptEnabled(true);
    }
}
