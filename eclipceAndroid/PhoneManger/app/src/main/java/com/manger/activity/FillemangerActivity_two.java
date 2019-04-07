package com.manger.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.manger.R;
import com.manger.base.FileManger_two;
import com.manger.manger.FileManger;
import com.manger.myadapater.FileAdapate_two;
import com.manger.tools.ClickBaseAcitivity;
import com.manger.tools.CommonUtil;

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

public class FillemangerActivity_two extends ClickBaseAcitivity {
    ListView listview;
    TextView textnumber, filesize;
    CheckBox check;
    Button button;
    List<FileManger_two> list;
    FileAdapate_two adapate;
    ProgressBar pro;
    FileManger filemanger;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_filletwo_manger );
        MyOnClickListener();
        ID();// 注册的ID

        show();

        check.setOnCheckedChangeListener( new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                adapate.setAllChexk( isChecked );
            }
        } );
    }

    public void ID() {
        // 注册项
        textnumber = findViewById( R.id.textnumber_two2 );
        filesize = findViewById( R.id.textView_file1 );
        check = findViewById( R.id.filecheckBox_two2 );
        button = findViewById( R.id.filebutton_two2 );
        listview = findViewById( R.id.listview_two1 );
        pro = findViewById( R.id.progressBar_filetwo1 );

        // 操作项
        list = new ArrayList<FileManger_two>();
        adapate = new FileAdapate_two( this, listview );
        filemanger = FileManger.getFilemanger();
        button.setOnClickListener( listen );
        adapate.addDatas( list );
        listview.setAdapter( adapate );

    }

    public void show() {

        Intent intent = getIntent();
        int id = intent.getIntExtra( "index", 0 );

        switch (id) {
            case R.id.LinearLayout_ALL2:
                adapate.addDatas( filemanger.getAnyFileList() );
                textnumber.setText( adapate.getCount() + "" );
                filesize.setText( CommonUtil.getFileSize( filemanger.getAnyFileSize() ) );
                adapate.notifyDataSetChanged();

                pro.setVisibility( View.GONE );
                break;
            case R.id.LinearLayout_document:
                adapate.addDatas( filemanger.getTxtFileList() );
                textnumber.setText( adapate.getCount() + "" );
                filesize.setText( CommonUtil.getFileSize( filemanger.getTxtFileSize() ) );
                adapate.notifyDataSetChanged();

                pro.setVisibility( View.GONE );
                break;
            case R.id.LinearLayout_video:
                adapate.addDatas( filemanger.getVideoFileList() );
                textnumber.setText( adapate.getCount() + "" );
                filesize.setText( CommonUtil.getFileSize( filemanger
                        .getVideoFileSize() ) );
                adapate.notifyDataSetChanged();

                pro.setVisibility( View.GONE );
                break;
            case R.id.LinearLayout_music:
                adapate.addDatas( filemanger.getAudioFileList() );
                textnumber.setText( adapate.getCount() );
                filesize.setText( CommonUtil.getFileSize( filemanger
                        .getAudioFileSize() ) );
                adapate.notifyDataSetChanged();
                pro.setVisibility( View.GONE );
                break;

            case R.id.LinearLayout_image:
                adapate.addDatas( filemanger.getImageFileList() );
                textnumber.setText( adapate.getCount() + "" );
                filesize.setText( CommonUtil.getFileSize( filemanger
                        .getImageFileSize() ) );
                adapate.notifyDataSetChanged();

                pro.setVisibility( View.GONE );
                break;
            case R.id.LinearLayout_package:
                adapate.addDatas( filemanger.getZipFileList() );
                textnumber.setText( adapate.getCount() + "" );
                filesize.setText( CommonUtil.getFileSize( filemanger.getZipFileSize() ) );
                adapate.notifyDataSetChanged();
                pro.setVisibility( View.GONE );
                break;
            case R.id.LinearLayout_install:
                adapate.addDatas( filemanger.getApkFileList() );
                textnumber.setText( adapate.getCount() + "" );
                filesize.setText( CommonUtil.getFileSize( filemanger.getApkFileSize() ) );
                adapate.notifyDataSetChanged();
                pro.setVisibility( View.GONE );
                break;

            default:
                break;
        }
    }

    OnClickListener listen = new OnClickListener() {


        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            ArrayList<FileManger_two> delFileInfos = new ArrayList<FileManger_two>();

            for (int i = 0; i < adapate.getDatas().size(); i++) {
                FileManger_two fileInfo = list.get( i );
                if (fileInfo.isFilecheck()) {
                    delFileInfos.add( fileInfo );
                }

            }
            for (int i = 0; i < delFileInfos.size(); i++) {
                FileManger_two fileInfol = delFileInfos.get( i );
                File file = fileInfol.getTargetFile();
                long size = file.length();

                adapate.getDatas().remove( fileInfol );
                filemanger.getAnyFileList().remove( fileInfol );
                filemanger.setAnyFileSize( filemanger.getAnyFileSize() - size );
                switch (id) {
                    case R.id.LinearLayout_document:
                        filemanger.getTxtFileList().remove( fileInfol );
                        filemanger.setTxtFileSize( filemanger.getTxtFileSize() - size );
                        break;
                    case R.id.LinearLayout_video:
                        filemanger.getVideoFileList().remove( fileInfol );
                        filemanger.setVideoFileSize( filemanger.getVideoFileSize() - size );
                        break;
                    case R.id.LinearLayout_music:
                        filemanger.getAudioFileList().remove( fileInfol );
                        filemanger.setAudioFileSize( filemanger.getAudioFileSize() - size );
                        break;
                    case R.id.LinearLayout_image:
                        filemanger.getImageFileList().remove( fileInfol );
                        filemanger.setImageFileSize( filemanger.getImageFileSize() - size );
                        break;
                    case R.id.LinearLayout_package:
                        filemanger.getApkFileList().remove( fileInfol );
                        filemanger.setApkFileSize( filemanger.getApkFileSize() - size );
                        break;
                    case R.id.LinearLayout_install:
                        filemanger.getZipFileList().remove( fileInfol );
                        filemanger.setZipFileSize( filemanger.getZipFileSize() - size );
                        break;
                }
            }
            //更新列表
            adapate.notifyDataSetChanged();
            //获取文件数量
            //显示
            textnumber.setText( adapate.getDatas().size() + "个" );

            System.gc();
            //放弃线程当前执行权
            Thread.yield();

        }
    };
}