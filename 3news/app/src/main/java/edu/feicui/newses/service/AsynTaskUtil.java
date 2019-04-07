package edu.feicui.newses.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/12/14.
 */

public class AsynTaskUtil extends AsyncTask<String,Integer,String> {
    private Toast toast;
    private Context context;
    @Override
    protected String doInBackground(String... params) {
        try {
            URL url=new URL(params[0]);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream=connection.getInputStream();
            Bitmap bitmap= BitmapFactory.decodeStream(stream);
            stream.close();
            connection.disconnect();
//            publishProgress(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "OK";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(context, "开始下载", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Toast.makeText(context, "正在下载", Toast.LENGTH_SHORT).show();
    }
}
