package com.example.administrator.anew.com.activity;

import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.administrator.anew.R;
import com.example.administrator.anew.com.fragment.HomeFragment;
import com.example.administrator.anew.com.fragment.ScrollingFragment;
import com.example.administrator.anew.com.fragment.AboutFragment;
import com.example.administrator.anew.com.fragment.VebViewFragment;
import com.example.administrator.anew.com.fragment.local.LocalVideoFragment;
import com.xys.libzxing.zxing.activity.CaptureActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private static int RES_1 = 1;
    private static int RES_2 = 2;
    ImageView imageView;
    FragmentManager fragmentManager=getSupportFragmentManager();

    private HomeFragment homeFragment = new HomeFragment();
    private ScrollingFragment scrollingFragment = new ScrollingFragment();
    private LocalVideoFragment localVideoFragment=new LocalVideoFragment();

    TextView[] NavigationBottom = new TextView[5];
    String[] NavigationText = new String[]{"首页", "发现", "视频", "设置", "我的"};
    int[] NavigationTextId = {R.id.ljf1, R.id.ljf2, R.id.ljf3, R.id.ljf4, R.id.ljf5};

    private DrawerLayout mDrawerLayout;

    public MainActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        setNavigationView();

        imageView .setImageResource(R.drawable.example_appwidget_preview);
//        String filePath = Environment.getExternalStorageDirectory().getPath() + "/" + "temp.png";
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        for (int i = 0; i < 5; i++) {
            NavigationBottom[i] = (TextView) findViewById(NavigationTextId[i]);
            NavigationBottom[i].setText("");
            NavigationBottom[i].setHint(NavigationText[i]);
            NavigationBottom[i].setTextSize(15);
            NavigationBottom[i].setTypeface(Typeface.DEFAULT_BOLD);
            NavigationBottom[i].setOnClickListener(this);
        }
        NavigationBottom[1].setText("发现");
        NavigationBottom[1].setTextSize(17);
        NavigationBottom[1].setTextColor(getResources().getColor(R.color.dodgerblue));

        fragmentManager.beginTransaction().add(R.id.main_contaner, homeFragment).commit();
        fragmentManager.beginTransaction().add(R.id.main_contaner, scrollingFragment).commit();
        fragmentManager.beginTransaction().add(R.id.main_contaner,localVideoFragment).commit();
        myShow(homeFragment);
    }

    //显示Fragment
    public void myShow(Fragment fragment) {
        fragmentManager.beginTransaction().hide(homeFragment).commit();
        fragmentManager.beginTransaction().hide(scrollingFragment).commit();
        fragmentManager.beginTransaction().hide(localVideoFragment).commit();
        fragmentManager.beginTransaction().show(fragment).commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(MainActivity.this, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            assert uri != null;
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String Id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + Id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else {
            assert uri != null;
            if ("content".equalsIgnoreCase(uri.getScheme())) {
                imagePath = getImagePath(uri, null);
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                imagePath = uri.getPath();
            }
        }
        displayImage(imagePath);
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            imageView.setImageBitmap(bitmap);
        } else {
            Toast.makeText(MainActivity.this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            assert bundle != null;
            String result = bundle.getString("result");
            Intent intent = new Intent(MainActivity.this, VebViewFragment.class);
            intent.putExtra("kk", result);
            startActivity(intent);
        } else if (resultCode == RESULT_OK) {
            if (requestCode == RES_1) {
                Bundle bundle = data.getExtras();
                assert bundle != null;
                Bitmap bitmap = (Bitmap) bundle.get("data");
                imageView.setImageBitmap(bitmap);
            } else if (requestCode == RES_2) {
                if (Build.VERSION.SDK_INT >= 19) {
                    handleImageOnKitKat(data);
                } else {
                    handleImageBeforeKitKat(data);
                }
            }
        }
    }

    /**
     * openAlbum Event
     */
    public void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, RES_2);
    }

    /**
     * onRequestPermissionsResult
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(MainActivity.this, "You denid the permission", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    public void setNavigationView() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.about_toolbar);
        setSupportActionBar(mToolbar);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.drawer);
        mToolbar.setTitleMarginEnd(50);
        //设置导航图片的监听，打开抽屉导航
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
        NavigationView mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        imageView= (ImageView) mNavigationView.inflateHeaderView(R.layout.header).findViewById(R.id.head_imageView);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    /**
     * 右侧导航 扫一扫， 登录， 搜索 监听
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup:
                Toast.makeText(MainActivity.this, "点击了扫一扫", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_scan:
                startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class), 0);
                break;
            case R.id.menu_share:
                Toast.makeText(MainActivity.this, "点击了登录", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_search:
                Toast.makeText(MainActivity.this, "点击了收索", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_notifation:
                Toast.makeText(MainActivity.this, "点击了通知", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_register:
                Toast.makeText(MainActivity.this, "点击了注册", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_logout:
                Toast.makeText(MainActivity.this, "点击了注销", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_help:
                Toast.makeText(MainActivity.this, "点击了帮助反馈", Toast.LENGTH_LONG).show();
                break;
            default:
        }
        return true;
    }

    /**
     * 底部按钮切换 首页 ， 发现。视频
     */
    @Override
    public void onClick(View v) {
        for (int i = 0; i < 5; i++) {
            NavigationBottom[i].setText("");
            NavigationBottom[i].setHint(NavigationText[i]);
            NavigationBottom[i].setTextSize(15);

        }
        switch (v.getId()) {
            case R.id.ljf1:
                NavigationBottom[0].setText("首页");
                NavigationBottom[0].setTextSize(17);
                NavigationBottom[0].setTextColor(MainActivity.this.getResources().getColor(R.color.dodgerblue));
                break;
            case R.id.ljf2:
                NavigationBottom[1].setText("发现");
                NavigationBottom[1].setTextSize(17);
                NavigationBottom[1].setTextColor(MainActivity.this.getResources().getColor(R.color.dodgerblue));
                myShow(homeFragment);
                break;
            case R.id.ljf3:
                NavigationBottom[2].setText("视频");
                NavigationBottom[2].setTextSize(17);
                NavigationBottom[2].setTextColor(MainActivity.this.getResources().getColor(R.color.dodgerblue));
                myShow(localVideoFragment);
                break;
            case R.id.ljf4:
                NavigationBottom[3].setText("设置");
                NavigationBottom[3].setTextSize(17);
                NavigationBottom[3].setTextColor(MainActivity.this.getResources().getColor(R.color.dodgerblue));
                break;
            case R.id.ljf5:
                NavigationBottom[4].setText("我的");
                NavigationBottom[4].setTextSize(17);
                NavigationBottom[4].setTextColor(MainActivity.this.getResources().getColor(R.color.dodgerblue));
                myShow(scrollingFragment);
                break;
        }
    }

    //抽屉布局单项监听
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_camera:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, RES_1);
                break;
            case R.id.nav_gallery:
                if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    openAlbum();
                }
                break;
            case R.id.nav_slideshow:
                break;
            case R.id.nav_manage:
                break;
            default:
                Log.w("Main", "Unknown drawer item selected");
                break;
        }
        item.setChecked(true);
//        mDrawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
}





