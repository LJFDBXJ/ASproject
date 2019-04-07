package com.ljfdbxj.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.ljfdbxj.broadcastreceiver.com.service.ServiceActivity;
import com.ljfdbxj.broadcastreceiver.lib.SlidingMenu;

public class MainActivity extends AppCompatActivity {
    private SlidingMenu slidingMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        FloatingActionButton fab = findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make( view, "Replace with your own action", Snackbar.LENGTH_LONG )
                        .setAction( "Action", null ).show();
            }
        } );

        slidingMenu=new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        //全屏都可以滑动
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

        //可以滑出100单位
        slidingMenu.setBehindOffsetRes(R.dimen.margin);
        //在当前的activity中滑动
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //左侧的布局
        slidingMenu.setMenu(R.layout.test);
        //右侧的布局
        slidingMenu.setSecondaryMenu(R.layout.test);
    }

//    BroadcastReceiver sdff = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String name = intent.getAction();
//            if (name.equals( "haha" )) {
//                String message=intent.getStringExtra( "zhang" );
//                Toast.makeText( MainActivity.this, message, Toast.LENGTH_SHORT )
//                        .show();
//            }
//        }
//
//    };

    @Override
    protected void onResume() {
        super.onResume();
//        IntentFilter filter = new IntentFilter();
//        filter.addAction( Intent.ACTION_AIRPLANE_MODE_CHANGED );
//        registerReceiver( sdff, filter );
    }

    @Override
    protected void onPause() {
        super.onPause();
//        unregisterReceiver( sdff );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.menu_main, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.service) {
            Intent intent = new Intent(this,ServiceActivity.class );
            startActivity( intent );
            return true;
        } else if (id == R.id.Broadcast) {
            Intent intent = new Intent();
            intent.putExtra( "zhang", "你知道吗？" );
            intent.setAction( "haha" );
            sendBroadcast( intent );
            return true;
        }

        return super.onOptionsItemSelected( item );
    }
}
