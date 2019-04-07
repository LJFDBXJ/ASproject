package edu.feicui.newses.ui.base;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import edu.feicui.newses.R;
import edu.feicui.newses.entity.News;
import edu.feicui.newses.model.biz.parser.NewsDBManager;
import edu.feicui.newses.receiver.XListView;
import edu.feicui.newses.ui.adapter.NewsAdapter;
import edu.feicui.newses.view.Activity_show;
import edu.feicui.newses.view.MainActivity;

/**
 * Created by Administrator on 2016/12/28.
 */

public class Activity_fragment extends Fragment implements XListView.IXListViewListener{
    private NewsAdapter adapter;
    private static final String TAG = Activity_fragment.class.getSimpleName();
    private int pager = 1;
    private int limit = 10;
    private int offset = 0;
    private int start=0;
    private ArrayList<News> datas;
    private NewsDBManager manager;
    private ArrayList<String> items = new ArrayList<String>();
    private Handler handler;
    private Handler mHandler;
    private XListView mListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.action_fragment,container,false);
        mHandler = new Handler();
        init(view);
        initData();
        geneItems();
       return view;
    }


    private void init(View view) {
        mListView=(XListView)view.findViewById(R.id.lv_action_fragment);
        mListView.setPullLoadEnable(true);
        final String path = "http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";
        manager = NewsDBManager.getDbManager(view.getContext());
        adapter = new NewsAdapter(view.getContext());
        mListView.setAdapter(adapter);
        mListView.setXListViewListener(this);
        handler=new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0x123) {
                    adapter.clean();
                    readDBManager(limit, offset);
                    adapter.add(datas);
                    mListView.setAdapter(adapter);
                    adapter.update();
                }
            }
        };


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String str = getDataHttp(path);
                    ArrayList<News> data = jsonData(str);
                    for (int i = 0; i < data.size(); i++) {
                        manager.insertNews(data.get(i));
                    }
                    handler.sendEmptyMessage(0x123);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    private void initData() {

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putString("link", adapter.getItem(i).getLink());
                Intent intent=new Intent(view.getContext(),Activity_show.class);
                startActivity(intent,bundle);
            }


        });
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                    adapter.setIsclick(true);
                    adapter.notifyDataSetChanged();
                } else if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    adapter.setIsclick(false);
                    adapter.notifyDataSetChanged();
                } else if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    adapter.setIsclick(false);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (totalItemCount > 9 && mListView.getLastVisiblePosition() + 1 == totalItemCount) {
                    readDBManager(limit, offset);
                }
            }
        });
    }




    public String getDataHttp(String path) throws IOException {
        String all = "";
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();

        if (connection.getResponseCode() == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                all += line;
            }
            Log.e("news", all);
            reader.close();
            connection.disconnect();
            ;
        }

        return all;
    }


    public ArrayList<News> jsonData(String str) {
        ArrayList<News> list = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(str);
            Log.e("news", str);
            JSONArray array = object.getJSONArray("data");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                int type = jsonObject.getInt("type");
                int nid = jsonObject.getInt("nid");
                String stamp = jsonObject.getString("stamp");
                String icon = jsonObject.getString("icon");
                String title = jsonObject.getString("title");
                String summary = jsonObject.getString("summary");
                String link = jsonObject.getString("link");
                News news = new News(type, nid, stamp, icon, title, summary, link);
                list.add(news);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void readDBManager(int limit2,int offset2){
        ArrayList<News> array=manager.queryNews(limit2,offset2);
        adapter.addendData(array,false);
        this.offset=offset+array.size();
        adapter.update();
    }

    @Override
    public void onRefresh() {

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                geneItems();
                adapter.notifyDataSetInvalidated();

                onLoad();
            }
        }, 2000);

    }

    @Override
    public void onLoadMore() {

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                geneItems();

                adapter.notifyDataSetChanged();
                onLoad();
            }
        }, 2000);

    }
    private void geneItems() {
        for (int i = 0; i < 20; ++i) {
            items.add("refresh cnt " + (++start));
        }

        Log.i(TAG, "-------------->start = " + start);

    }

    private void onLoad() {
        mListView.stopRefresh();
        mListView.stopLoadMore();
        mListView.setRefreshTime("刚刚");
    }
}
