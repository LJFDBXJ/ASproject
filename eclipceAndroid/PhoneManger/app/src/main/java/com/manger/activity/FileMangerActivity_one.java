package com.manger.activity;

import java.util.List;

import com.manger.R;
import com.manger.base.FileManger_one;
import com.manger.base_ativity.BaseActivity;
import com.manger.manger.FileManger;
import com.manger.manger.FileTypeManager;
import com.manger.tools.CommonUtil;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class FileMangerActivity_one extends BaseActivity implements
        OnClickListener {
    LinearLayout ALL2, document, video, music, image, packager, install;

    TextView textnum1, ALLnumber, document2, videonumber, musicnumber, imagenumber, packagenumber, installnumber;

    ProgressBar pro_ALL, pro_documen, pro_video, pro_music, pro_image, pro_package, pro_install;

    ImageView imageView_ALL, imageView_document, imageView_video, imageView_music, imageView_image, imageView_package, imageView_install;

    List<FileManger_one> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_filemanger );
        ID();
        loadData();

    }

    private void loadData() {
        fileManger = FileManger.getFilemanger();
        fileManger.setSearchFileListener( searchFileListener );

        thread = new Thread( new Runnable() {

            @Override
            public void run() {
                fileManger.searchSDCardFile();
            }
        } );
        thread.start();
    }

    public void ID() {
        //图片
        imageView_ALL = (ImageView) findViewById( R.id.imageView_ALL );
        imageView_document = (ImageView) findViewById( R.id.imageView_document );
        imageView_video = (ImageView) findViewById( R.id.imageView_video );
        imageView_music = (ImageView) findViewById( R.id.imageView_music );
        imageView_image = (ImageView) findViewById( R.id.imageView_image );
        imageView_package = (ImageView) findViewById( R.id.imageView_package );
        imageView_install = (ImageView) findViewById( R.id.imageView_install );

        //布局
        ALL2 = findViewById( R.id.LinearLayout_ALL2 );
        document = findViewById( R.id.LinearLayout_document );
        video = findViewById( R.id.LinearLayout_video );
        music = findViewById( R.id.LinearLayout_music );
        image = findViewById( R.id.LinearLayout_image );
        packager = findViewById( R.id.LinearLayout_package );
        install = findViewById( R.id.LinearLayout_install );

        //布局监听
        ALL2.setOnClickListener( this );
        document.setOnClickListener( this );
        video.setOnClickListener( this );
        music.setOnClickListener( this );
        image.setOnClickListener( this );
        packager.setOnClickListener( this );
        install.setOnClickListener( this );

        //文本
        textnum1 = findViewById( R.id.file_textnum1 );// 头标签
        ALLnumber = findViewById( R.id.text_ALLnumber );
        document2 = findViewById( R.id.text_document2 );
        videonumber = findViewById( R.id.text_videonumber );
        musicnumber = findViewById( R.id.text_musicnumber );
        imagenumber = findViewById( R.id.text_imagenumber );
        packagenumber = findViewById( R.id.text_packagenumber );
        installnumber = findViewById( R.id.text_installnumber );

        //进度条
        pro_ALL = findViewById( R.id.pro_ALL );// progress
        pro_documen = findViewById( R.id.pro_documen );
        pro_video = findViewById( R.id.pro_video );
        pro_music = findViewById( R.id.pro_music );
        pro_image = findViewById( R.id.pro_image );
        pro_package = findViewById( R.id.pro_package );
        pro_install = findViewById( R.id.pro_install );

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (v.getId()) {
            case R.id.LinearLayout_ALL2:
                startActivity( FillemangerActivity_two.class, id );
                break;
            case R.id.LinearLayout_document:
                startActivity( FillemangerActivity_two.class, id );
                break;
            case R.id.LinearLayout_video:
                startActivity( FillemangerActivity_two.class, id );
                break;
            case R.id.LinearLayout_music:
                startActivity( FillemangerActivity_two.class, id );
                break;
            case R.id.LinearLayout_image:
                startActivity( FillemangerActivity_two.class, id );
                break;
            case R.id.LinearLayout_package:
                startActivity( FillemangerActivity_two.class, id );
                break;
            case R.id.LinearLayout_install:
                startActivity( FillemangerActivity_two.class, id );
                break;
            default:
                break;
        }

    }

//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		// TODO Auto-generated method stub
//		super.onActivityResult(requestCode, resultCode, data);
//		if (requestCode == 1) {
//
//		}
//	}

    @Override
    protected void myHandleMessage(Message msg) {

        if (msg.what == 1) {
            textnum1.setText( CommonUtil.getFileSize( fileManger.getAnyFileSize() ) );
            ALLnumber.setText( CommonUtil.getFileSize( fileManger
                    .getAnyFileSize() ) );

            String typename = (String) msg.obj;
            if (typename.equals( FileTypeManager.TYPE_APK )) {
                installnumber.setText( CommonUtil.getFileSize( fileManger
                        .getApkFileSize() ) );
            } else if (typename.equals( FileTypeManager.TYPE_AUDIO )) {
                musicnumber.setText( CommonUtil.getFileSize( fileManger
                        .getAudioFileSize() ) );
            } else if (typename.equals( FileTypeManager.TYPE_IMAGE )) {
                imagenumber.setText( CommonUtil.getFileSize( fileManger
                        .getImageFileSize() ) );
            } else if (typename.equals( FileTypeManager.TYPE_TXT )) {
                document2.setText( CommonUtil.getFileSize( fileManger
                        .getTxtFileSize() ) );
            } else if (typename.equals( FileTypeManager.TYPE_VIDEO )) {
                videonumber.setText( CommonUtil.getFileSize( fileManger
                        .getVideoFileSize() ) );
            } else if (typename.equals( FileTypeManager.TYPE_ZIP )) {
                packagenumber.setText( CommonUtil.getFileSize( fileManger
                        .getZipFileSize() ) );
            }
        }
        if (msg.what == 2) {
            textnum1.setText( CommonUtil.getFileSize( fileManger.getAnyFileSize() ) );
            ALLnumber.setText( CommonUtil.getFileSize( fileManger
                    .getAnyFileSize() ) );
            document2.setText( CommonUtil.getFileSize( fileManger
                    .getTxtFileSize() ) );
            videonumber.setText( CommonUtil.getFileSize( fileManger
                    .getVideoFileSize() ) );
            musicnumber.setText( CommonUtil.getFileSize( fileManger
                    .getAudioFileSize() ) );
            imagenumber.setText( CommonUtil.getFileSize( fileManger
                    .getImageFileSize() ) );
            packagenumber.setText( CommonUtil.getFileSize( fileManger
                    .getZipFileSize() ) );
            installnumber.setText( CommonUtil.getFileSize( fileManger
                    .getApkFileSize() ) );

            pro_ALL.setVisibility( View.GONE );
            pro_documen.setVisibility( View.GONE );
            pro_video.setVisibility( View.GONE );
            pro_music.setVisibility( View.GONE );
            pro_image.setVisibility( View.GONE );
            pro_package.setVisibility( View.GONE );
            pro_install.setVisibility( View.GONE );

            imageView_ALL.setVisibility( View.VISIBLE );
            imageView_document.setVisibility( View.VISIBLE );
            imageView_video.setVisibility( View.VISIBLE );
            imageView_music.setVisibility( View.VISIBLE );
            imageView_image.setVisibility( View.VISIBLE );
            imageView_package.setVisibility( View.VISIBLE );
            imageView_install.setVisibility( View.VISIBLE );

        }

    }

    private FileManger.SearchFileListener searchFileListener = new FileManger.SearchFileListener() {

        @Override
        public void searching(String typeName) {
            // TODO Auto-generated method stub
            Message message = mainHandler.obtainMessage();
            message.what = 1;
            message.obj = typeName;
            mainHandler.sendMessage( message );

        }

        @Override
        public void end(boolean isExceptionEnd) {
            // TODO Auto-generated method stub
            mainHandler.sendEmptyMessage( 2 );
        }
    };

    private Thread thread;
    private FileManger fileManger;

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        fileManger.setStopSearch( false );// 停止搜索
        thread.interrupt();// interrupt中断
        thread = null;
    }

}
