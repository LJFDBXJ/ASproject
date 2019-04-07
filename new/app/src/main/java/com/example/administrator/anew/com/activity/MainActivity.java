package com.example.administrator.anew.com.activity;

import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.anew.R;
import com.example.administrator.anew.com.fragment.Home_Fragment;
import com.example.administrator.anew.com.fragment.Piano_Fragment;
import com.example.administrator.anew.com.fragment.Scrolling_Fragment;
import com.example.administrator.anew.com.fragment.VebView_Fragment;
import com.example.administrator.anew.com.fragment.local.LocalVideoFragment;
import com.xys.libzxing.zxing.activity.CaptureActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static int RES_1 = 1;
    private static int RES_2 = 2;
    private ImageView imageView;

    FragmentManager fragmentManager = getSupportFragmentManager();
    private Piano_Fragment pianoFragmentFragment = new Piano_Fragment();
    private Home_Fragment homeFragment = new Home_Fragment();
    private Scrolling_Fragment scrollingFragment = new Scrolling_Fragment();
    private LocalVideoFragment localVideoFragment = new LocalVideoFragment();

    private DrawerLayout mDrawerLayout;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    myShow( homeFragment );
                    return true;
                case R.id.find:
                    myShow( localVideoFragment );
                    return true;
                case R.id.seting:
                    myShow( pianoFragmentFragment );
                    return true;
                case R.id.Video:
                    myShow( homeFragment );
                    return true;
                case R.id.My:
                    myShow( scrollingFragment );
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        setNavigationView();
        fragmentManager.beginTransaction().add( R.id.main_contaner, homeFragment ).
                add( R.id.main_contaner, scrollingFragment ).
                add( R.id.main_contaner, localVideoFragment ).
                add( R.id.main_contaner, pianoFragmentFragment ).commit();
        BottomNavigationView bottomnavigation = findViewById( R.id.navigation );
        bottomnavigation.setItemBackgroundResource( R.color.dodgerblue );
        bottomnavigation.setOnNavigationItemSelectedListener( mOnNavigationItemSelectedListener );
    }


    public void setNavigationView() {
        NavigationView mNavigationView = findViewById( R.id.navigation_view );
        imageView = mNavigationView.inflateHeaderView( R.layout.header ).findViewById( R.id.head_imageView );
        mNavigationView.setNavigationItemSelectedListener( this );
        mDrawerLayout = findViewById( R.id.drawer_layout );
    }

    //显示Fragment
    public void myShow(Fragment fragment) {
        fragmentManager.beginTransaction().hide( homeFragment ).commit();
        fragmentManager.beginTransaction().hide( scrollingFragment ).commit();
        fragmentManager.beginTransaction().hide( localVideoFragment ).commit();
        fragmentManager.beginTransaction().show( fragment ).commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri( MainActivity.this, uri )) {
            String docId = DocumentsContract.getDocumentId( uri );
            assert uri != null;
            if ("com.android.providers.media.documents".equals( uri.getAuthority() )) {
                String Id = docId.split( ":" )[1];
                String selection = MediaStore.Images.Media._ID + "=" + Id;
                imagePath = getImagePath( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection );
            } else if ("com.android.providers.downloads.documents".equals( uri.getAuthority() )) {
                Uri contentUri = ContentUris.withAppendedId( Uri.parse( "content://downloads/public_downloads" ), Long.valueOf( docId ) );
                imagePath = getImagePath( contentUri, null );
            }
        } else {
            assert uri != null;
            if ("content".equalsIgnoreCase( uri.getScheme() )) {
                imagePath = getImagePath( uri, null );
            } else if ("file".equalsIgnoreCase( uri.getScheme() )) {
                imagePath = uri.getPath();
            }
        }
        displayImage( imagePath );
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath( uri, null );
        displayImage( imagePath );
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile( imagePath );
            imageView.setImageBitmap( bitmap );
        } else {
            Toast.makeText( MainActivity.this, "failed to get image", Toast.LENGTH_SHORT ).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            assert bundle != null;
            String result = bundle.getString( "result" );
            Intent intent = new Intent( MainActivity.this, VebView_Fragment.class );
            intent.putExtra( "kk", result );
            startActivity( intent );
        } else if (resultCode == RESULT_OK) {
            if (requestCode == RES_1) {
                Bundle bundle = data.getExtras();
                assert bundle != null;
                Bitmap bitmap = (Bitmap) bundle.get( "data" );
                imageView.setImageBitmap( bitmap );
            } else if (requestCode == RES_2) {
                if (Build.VERSION.SDK_INT >= 19) {
                    handleImageOnKitKat( data );
                } else {
                    handleImageBeforeKitKat( data );
                }
            }
        }
    }

    /**
     * openAlbum Event
     */
    public void openAlbum() {
        Intent intent = new Intent( "android.intent.action.GET_CONTENT" );
        intent.setType( "image/*" );
        startActivityForResult( intent, RES_2 );
    }

    /**
     * onRequestPermissionsResult
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult( requestCode, permissions, grantResults );
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText( MainActivity.this, "You denid the permission", Toast.LENGTH_SHORT ).show();
                }
                break;
        }
    }

    public String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query( uri, null, selection, null, null );
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString( cursor.getColumnIndex( MediaStore.Images.Media.DATA ) );
            }
            cursor.close();
        }
        return path;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.toolbar, menu );
        return true;
    }

    /**
     * 右侧导航 扫一扫， 登录， 搜索 监听
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup:

                break;
            case R.id.menu_scan:
                startActivityForResult( new Intent( MainActivity.this, CaptureActivity.class ), 0 );
                break;
            case R.id.menu_share:
                Toast.makeText( MainActivity.this, "点击了登录", Toast.LENGTH_LONG ).show();
                break;
            case R.id.menu_search:
                Toast.makeText( MainActivity.this, "点击了收索", Toast.LENGTH_LONG ).show();
                break;
            case R.id.menu_notifation:
                Toast.makeText( MainActivity.this, "点击了通知", Toast.LENGTH_LONG ).show();
                break;
            case R.id.menu_register:
                Toast.makeText( MainActivity.this, "点击了注册", Toast.LENGTH_LONG ).show();
                break;
            case R.id.menu_logout:
                Toast.makeText( MainActivity.this, "点击了注销", Toast.LENGTH_LONG ).show();
                break;
            case R.id.menu_help:
                Toast.makeText( MainActivity.this, "点击了帮助反馈", Toast.LENGTH_LONG ).show();
                break;
            default:
        }
        return true;
    }


//

    //抽屉布局单项监听
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_camera:
                Intent intent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
                startActivityForResult( intent, RES_1 );
                break;
            case R.id.nav_gallery:
                if (ContextCompat.checkSelfPermission( MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions( MainActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1 );
                } else {
                    openAlbum();
                }
                break;
            case R.id.nav_slideshow:
                break;
            case R.id.nav_manage:
                break;
            default:
                Log.w( "Main", "Unknown drawer item selected" );
                break;
        }
        item.setChecked( true );
        mDrawerLayout.closeDrawer( GravityCompat.END);
        return false;
    }
}
