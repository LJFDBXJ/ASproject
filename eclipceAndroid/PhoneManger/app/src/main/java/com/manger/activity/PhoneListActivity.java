package com.manger.activity;

import java.util.List;

import com.manger.R;
import com.manger.base.PhoneClass;
import com.manger.db.DbReader;
import com.manger.myadapater.BookAdapter_list;
import com.manger.tools.ClickBaseAcitivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

public class PhoneListActivity extends ClickBaseAcitivity implements
        OnItemClickListener {

    ListView bookview_list;
    BookAdapter_list bookadapter_list;
    List<PhoneClass> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_book_list );

        bookview_list = (ListView) findViewById( R.id.listView_booklist );

        Intent intent = getIntent();

        int faa = intent.getIntExtra( "index", 0 );

        if (faa == 0) {
            // startActivity(Intent.ACTION_DIAL);
            Intent intent1 = new Intent( Intent.ACTION_DIAL );
            startActivity( intent1 );
        } else {
            list = DbReader.getTwoDate( faa ); // 只获得a值对应的这个表，放到这个几个
            bookadapter_list = new BookAdapter_list( list, this );// 适配器存集合
            bookview_list.setAdapter( bookadapter_list );

        }
        MyOnClickListener();
        bookview_list.setOnItemClickListener( this );
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

        PhoneClass phoneclass = list.get( arg2 );
        String name = phoneclass.getName();
        final String number = phoneclass.getNumber();

        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setIcon( R.drawable.ic_launcher );
        builder.setTitle( "请求拨打电话" );
        builder.setMessage( "公司名称：" + name + "\n归属号：" + number );
        builder.setPositiveButton( "确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Uri uri = Uri.parse( "tel:" + number );
                Intent intent = new Intent( Intent.ACTION_CALL, uri );
                startActivity( intent );

            }
        } );
        builder.setNegativeButton( "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        } );
        builder.show();
    }
}
