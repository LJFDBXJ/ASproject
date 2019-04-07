package com.example.administrator.anew.Utils_Tool;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2017/2/7.
 */

public class LodeImage_Url {
    private String myurl;
    private ImageView myImageView;
    private LruCache<String, Bitmap> mCaChe;

    public LodeImage_Url() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 4;
        mCaChe = new LruCache<String, Bitmap>(cacheSize) {
            //在每次存入缓存的时候调用
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    //增加到缓存
    public void addbitmapToCache(String url, Bitmap bitmap) {
        if (getBitmapFromURL(url) == null) {
            mCaChe.put(url, bitmap);
        }
    }

    //从缓冲中获取数据。
    public Bitmap getBitmapFromCache(String url) {
        return mCaChe.get(url);
    }

    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (myImageView.getTag().equals(myurl)) {
                myImageView.setImageBitmap((Bitmap) msg.obj);

            }
        }
    };

    public void showImageByThred(ImageView imageView, final String url) {
        myImageView = imageView;
        myurl = url;
        new Thread() {
            @Override
            public void run() {
                super.run();
                Bitmap bitmap = getBitmapFromURL(myurl);
                Message message = Message.obtain();
                message.obj = bitmap;
                myHandler.sendMessage(message);
            }
        }.start();
    }

    public Bitmap getBitmapFromURL(String urlString) {
        Bitmap bitmap;
        InputStream is = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            is = new BufferedInputStream(connection.getInputStream());
            bitmap = BitmapFactory.decodeStream(is);
            connection.disconnect();
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void getShowImageByAsyncTask(ImageView imageView, String url) {
        //从缓存中取出图片
        Bitmap bitmap = getBitmapFromCache(url);
        if (bitmap == null) {
            new NewAsyncTask(imageView, url).execute(url);
        } else {
            imageView.setImageBitmap(bitmap);
        }

    }

    private class NewAsyncTask extends AsyncTask<String, Void, Bitmap> {
        private ImageView mImageView;
        String myurl;

        public NewAsyncTask(ImageView imageView, String url) {
            mImageView = imageView;
            myurl = url;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String url = params[0];
            //从网络获取图片
            Bitmap bitmap = getBitmapFromURL(url);
            if (bitmap != null) {
                //将不再缓冲对的图片加入缓冲
                addbitmapToCache(url, bitmap);
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (mImageView.getTag().equals(myurl)) {
                mImageView.setImageBitmap(bitmap);

            }
        }
    }
}
