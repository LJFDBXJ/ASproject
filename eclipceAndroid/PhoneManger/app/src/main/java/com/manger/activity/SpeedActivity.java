package com.manger.activity;

import java.util.ArrayList;
import java.util.List;

import com.manger.R;
import com.manger.base.Speed;
import com.manger.manger.APPManger;
import com.manger.manger.PhoneManager;
import com.manger.myadapater.Speed_adapate;
import com.manger.tools.ClickBaseAcitivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SpeedActivity extends ClickBaseAcitivity {

    ListView listview;// ListView
    Button butspeed;// 一件清理
    TextView phone, versions, text;// TEXT的ID
    List<Speed> list;// List集合
    Speed_adapate adapate;// Speed,适配器
    CheckBox checkAll;// 全选框
    ProgressBar protop, probtm;// 进度条
    PhoneManager phoneManger;// 管理类
    APPManger appmanger;// 软件管理类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_phonespead );
        SpeedId();// 注册的ID
        threadd();

        MyOnClickListener();// Actionbar
        getMemory();// 调取系统信息。

        butseep();

    }

    public void SpeedId() {

        listview = findViewById( R.id.listView_speed1 );// listview的ID
        butspeed = findViewById( R.id.button_speed1 );// 一键加速
        phone = findViewById( R.id.textview_speed_phone );// 手机
        versions = findViewById( R.id.textview_speed_versions );// 版本
        text = findViewById( R.id.textview_speed_text );// 包名
        checkAll = findViewById( R.id.checkBox_speed1 );// 全选框
        probtm = findViewById( R.id.progressBar_btoom1 );// 底进度条
        protop = findViewById( R.id.progressBar_top1 );// 顶进度条
        list = new ArrayList<Speed>();
        appmanger = APPManger.getAPPManger( this );
        checkAll.setOnCheckedChangeListener( listenerCheck );
        adapate = new Speed_adapate( this );

    }

    public void butseep() {
        butspeed.setOnClickListener( new OnClickListener() {
            boolean isCheck = false;

            public void onClick(View v) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get( i ).isCheck()) {
                        appmanger.killMyRunApp( list.get( i ).getPackagename() );
                        isCheck = true;
                    }
                }
                if (isCheck == false) {
                    return;
                }
                checkAll.setChecked( false );
                threadd();
                getMemory();
            }

        } );
        ;
    }

    /**
     * 加载数据——将获取的管理类集合给listview
     */
    public void threadd() {
        new Thread( new Runnable() {
            @Override
            public void run() {
                runOnUiThread( new Runnable() { // 跟新主界面的方法
                    @Override
                    public void run() {
                        list = appmanger.getRunAllApp();
                        adapate.addDatas( list );
                        listview.setAdapter( adapate );
                        adapate.notifyDataSetChanged();
                        probtm.setVisibility( View.GONE );
                    }
                } );
            }
        } ).start();
    }

    /**
     * checkbox的监听
     */
    OnCheckedChangeListener listenerCheck = new OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            List<Speed> list2 = adapate.getDatas();

            if (isChecked) {
                for (int i = 0; i < list2.size(); i++) {
                    list2.get( i ).setSCheck( isChecked );
                    adapate.notifyDataSetChanged();
                }
            } else {
                for (int i = 0; i < list2.size(); i++) {
                    list2.get( i ).setSCheck( isChecked );
                    adapate.notifyDataSetChanged();
                }
            }

        }
    };

    private void getMemory() {// 获取内存，设置标签
        phoneManger = PhoneManager.getPhoneManager( this );
        long fre = phoneManger.getMemoryManager().getFreeMemory();
        long total = phoneManger.getMemoryManager().getTotalMemory();
        int in = (int) ((double) fre / total * 100);
        protop.setMax( 100 );
        protop.setProgress( in );
        text.setText( "可用内存     " + in + "%" );
        phone.setText( "设备名     "
                + phoneManger.getDeviceManager().getDeviceName() );
        versions.setText( "设备号     "
                + phoneManger.getDeviceManager().getDeviceNumber() );
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode,
                                       Bundle options) {
        super.startActivityForResult( intent, requestCode, options );
        if (requestCode == 110) {
            threadd();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) { // 重新加载listview
        if (requestCode == 110) {
            threadd();
        }
    }

}
