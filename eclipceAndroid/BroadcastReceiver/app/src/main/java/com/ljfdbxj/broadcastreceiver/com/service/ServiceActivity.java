package com.ljfdbxj.broadcastreceiver.com.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.ljfdbxj.broadcastreceiver.R;

public class ServiceActivity extends Activity implements OnClickListener {

    private MyService mMyService;
    private TextView mTextView;
    private Button startServiceButton;
    private Button stopServiceButton;
    private Button bindServiceButton;
    private Button unbindServiceButton;
    private Context context;

    // 这里需要用到ServiceConnection在Context.bindService和context.unBindService()里用到
    /**
     * Like many callbacks from the system, the methods on this class are called
     * from the main thread of your process. 像许多回调系统，这类方法是从你的主线程中调用。
     */
    private ServiceConnection mServiceConnection = new ServiceConnection() {

        /**
         * Called when a connection to the Service has been established, with
         * the IBinder of the communication channel to the Service.
         * 建立一个服务连接是调用，与通信到服务的粘合剂*/

        public void onServiceConnected(ComponentName name, IBinder service) {
            mMyService = ((MyService.MyBinder) service).getService();
            // 当我bindService时，让TextView显示MyService里getSystemTime()方法的返回值
            mTextView.setText( "I am frome Service :" + mMyService.getSystemTime() + "\n" + mMyService.getPackageResourcePath() );
        }

        /** Called when a connection to the Service has been lost.
         * This typically happens when the process hosting the service has crashed or been killed.
         *  This does not remove the ServiceConnection itself -- this binding to the service will remain active,
         *  and you will receive a call to onServiceConnected(ComponentName, IBinder) when the Service is next running.
         *  当服务的连接丢失时调用，通常发生在托管服务进程中崩溃或被杀死时，这不会删除服务连接本身，——该绑定到服务奖保持活动，
         *  并且您将服务下一个运行时收到的服务连接组件名称，绑定器的调用。 */

        public void onServiceDisconnected(ComponentName name) {
            //ComponentName name连接已丢失服务的具体组件名称。
            Log.i( "TAG", "链接断开" );

        }
    };


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.myservice );
        setupViews();
    }

    public void setupViews() {
        context = ServiceActivity.this;
        mTextView = findViewById( R.id.text );

        startServiceButton = findViewById( R.id.startservice );
        stopServiceButton = findViewById( R.id.stopservice );
        bindServiceButton = findViewById( R.id.bindservice );
        unbindServiceButton = findViewById( R.id.unbindservice );

        startServiceButton.setOnClickListener( this );
        stopServiceButton.setOnClickListener( this );
        bindServiceButton.setOnClickListener( this );
        unbindServiceButton.setOnClickListener( this );
    }

    public void onClick(View v) {
        if (v == startServiceButton) {
            Intent i = new Intent();
            i.setClass( ServiceActivity.this, MyService.class );
            context.startService( i );
        } else if (v == stopServiceButton) {
            Intent i = new Intent();
            i.setClass( ServiceActivity.this, MyService.class );
            context.stopService( i );
        } else if (v == bindServiceButton) {
            Intent i = new Intent();
            i.setClass( ServiceActivity.this, MyService.class );
            context.bindService( i, mServiceConnection, BIND_AUTO_CREATE );
        } else {
            context.unbindService( mServiceConnection );
            mTextView.setText( "" );
        }
    }

}