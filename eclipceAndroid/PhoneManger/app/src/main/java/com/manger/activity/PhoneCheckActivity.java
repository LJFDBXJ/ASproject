package com.manger.activity;

import java.util.ArrayList;
import java.util.List;

import com.manger.R;
import com.manger.base.PhoneCheck;
import com.manger.manger.PhoneManager;
import com.manger.myadapater.PhoneCheckAdapater;
import com.manger.tools.ClickBaseAcitivity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PhoneCheckActivity extends ClickBaseAcitivity implements OnItemClickListener {
    ListView listview;
    PhoneCheckAdapater check;
    List<PhoneCheck> list;
    LayoutInflater inflater;
    TextView Text1, Text2, Text3, Text4, Text5, Text6, textparwer1, textparwer2;
    ProgressBar pro;
    receiver receiver = new receiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_phone_check );

        listview = (ListView) findViewById( R.id.list_view_check1 );
        List<PhoneCheck> list = new ArrayList<PhoneCheck>();
        list.add( new PhoneCheck( "CPU管理" ) );
        list.add( new PhoneCheck( "内存管理" ) );
        list.add( new PhoneCheck( "屏幕管理" ) );
        list.add( new PhoneCheck( "相机管理" ) );
        list.add( new PhoneCheck( "设备管理" ) );
        list.add( new PhoneCheck( "权限管理" ) );
        list.add( new PhoneCheck( "无线管理" ) );
        list.add( new PhoneCheck( "上网管理" ) );

        check = new PhoneCheckAdapater( list, this );
        textparwer1 = findViewById( R.id.textParwer1 );
        textparwer2 = findViewById( R.id.textparwer2 );
        pro = findViewById( R.id.progessview1 );

        listview.setAdapter( check );
        MyOnClickListener();
        listview.setOnItemClickListener( this );
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        inflater = LayoutInflater.from( PhoneCheckActivity.this );
        PhoneManager phone = PhoneManager.getPhoneManager( this );

        AlertDialog.Builder builder = new AlertDialog.Builder(
                PhoneCheckActivity.this );
        View view = inflater.inflate( R.layout.include_system, null );
        Text1 = view.findViewById( R.id.textsystem1 );
        Text2 = view.findViewById( R.id.textsystem2 );
        Text3 = view.findViewById( R.id.textsystem3 );
        Text4 = view.findViewById( R.id.textsystem4 );
        Text5 = view.findViewById( R.id.textsystem5 );
        Text6 = view.findViewById( R.id.textsystem6 );
        builder.setIcon( R.drawable.ic_laun );
        builder.setView( view );// 把自定义的view添加到对话框内

        switch (arg2) {
            case 0:
                builder.setTitle( "CPU信息" );
                builder.setIcon( R.drawable.setting_info_icon_cpu );
                Text1.setText( "CPU名称:   " );
                Text2.setText( phone.getCPUManager().getCPUName() );
                Text3.setText( "CPU数量:   " );
                Text4.setText( String.valueOf( phone.getCPUManager().getCPUNumber() ) );
                builder.setNegativeButton( "取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        } );
                builder.show();
                break;
            case 1:
                builder.setTitle( "内存信息" );
                builder.setIcon( R.drawable.setting_info_icon_space );
                Text1.setText( "总运行内存:   " );
                String text1 = phone.getMemoryManager().getTotalMemory() + "";
                Text2.setText( text1 + "M" );
                Text3.setText( "剩余内存:   " );
                String text2 = phone.getMemoryManager().getFreeMemory() + "";
                Text4.setText( text2 + "M" );
                builder.setNegativeButton( "取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        } );
                builder.show();
                break;
            case 2:
                builder.setTitle( "屏幕信息" );
                builder.setIcon( R.drawable.setting_info_icon_version );
                Text1.setText( "分辨率:   " );
                Text2.setText( phone.getScreenManager().getScreen() );

                builder.setNegativeButton( "取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        } );
                builder.show();
                break;

            case 3:
                builder.setTitle( "相机信息" );
                builder.setIcon( R.drawable.setting_info_icon_camera );
                Text1.setText( "相机最大分辨率:   " );
                Text2.setText( phone.getCameraManager().getCameraPix() );
                builder.setNegativeButton( "取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        } );
                builder.show();
                break;

            case 4:
                builder.setTitle( "设备信息" );
                Text1.setText( "设备名称:   " );
                Text2.setText( phone.getDeviceManager().getDeviceName() );
                Text3.setText( "设备序号:   " );
                Text4.setText( phone.getDeviceManager().getDeviceNumber() );
                Text5.setText( "设备基带:   " );
                Text6.setText( phone.getDeviceManager().getBaseBrand() );
                builder.setNegativeButton( "取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        } );
                builder.show();
                break;

            case 5:
                builder.setTitle( "系统权限" );
                builder.setIcon( R.drawable.setting_info_icon_root );
                Text1.setText( "是否获得最高权限:   " );
                Text2.setText( phone.getSystemManager().isRoot() );
                builder.setNegativeButton( "取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        } );
                builder.show();
                break;

            default:
                break;
        }

    }

    @Override
    protected void onResume() {

        super.onResume();
        IntentFilter filter = new IntentFilter( Intent.ACTION_BATTERY_CHANGED );// 获取系统广播，
        registerReceiver( receiver, filter );// 注册监听
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        unregisterReceiver( receiver );// 在本类中销毁时解除注册
    }

    class receiver extends BroadcastReceiver {

        int batty = 0;

        @Override
        public void onReceive(Context context, Intent intent) {
            String name = intent.getAction();

            if (name.equals( intent.ACTION_BATTERY_CHANGED )) {

                Bundle bundle = intent.getExtras();

                int currt = bundle.getInt( BatteryManager.EXTRA_LEVEL );// 获当前的值
                int scale = bundle.getInt( BatteryManager.EXTRA_SCALE );// 最大值
                if (currt > 0) {

                    int gaa = (int) ((float) currt / (float) scale * 100);

                    pro.setProgress( gaa );
                    textparwer1.setText( gaa + "%" );
                    batty = currt;
                    textparwer2.setBackgroundResource( R.color.white );

                }
                if (currt == scale) {
                    textparwer2.setBackgroundResource( R.color.blue );
                }

            }
        }

    }
}
