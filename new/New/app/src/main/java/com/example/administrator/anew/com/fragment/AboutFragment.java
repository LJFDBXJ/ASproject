package com.example.administrator.anew.com.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.anew.Adapater.item_RecyclerAdapter;
import com.example.administrator.anew.R;
import com.example.administrator.anew.Utils_Tool.AdDomain;
import com.example.administrator.anew.Utils_Tool.NewsBase;
import com.jeremyfeinstein.slidingmenu.lib.CustomViewAbove;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/1/13.
 */
public class AboutFragment extends Fragment {
    private RecyclerView myRecyclerView;
    public static final String ARG_TOOL_TYPE = "toolType";
    NewsBase news_cls;
    item_RecyclerAdapter adapter;
    static String param;
    private View rootView;
    int values;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            param = getArguments().getString(ARG_TOOL_TYPE);
        }
    }

    public static AboutFragment newInstance(String param1) {
        AboutFragment fragment = new AboutFragment();
        param = param1;
//        Bundle args = new Bundle();
//        args.putString(ARG_TOOL_TYPE, param1);
//        fragment.setArguments(args);
        return fragment;
    }

    //    www.imooc.com/api/teacher?type=48&num=30
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_tool_about, container, false);
        myRecyclerView =rootView.findViewById(R.id.recyclerView);
        // 获取图片加载实例
        mImageLoader = ImageLoader.getInstance();
        initImageLoader();
        initAdData();
        startAd();
        async(param);

        return rootView;
    }

    public void async(String url) {
        NewsAsyncTask asyncTask = new NewsAsyncTask();
        asyncTask.execute(url);
    }

    /**
     * 将URL对应的JSON所对应格式数据，转换为我们字符串数据填入类中
     *
     * @param url
     * @return
     */

    private List<NewsBase> getJsonData(String url) {
        List<NewsBase> newBeanList = new ArrayList<>();
        try {
            String jsonString = readStream(new URL(url).openStream());
            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    news_cls = new NewsBase();
                    news_cls.setTitlehead(jsonObject.getString("title"));
                    news_cls.setTitlebody(jsonObject.getString("summary"));
                    news_cls.setImage(jsonObject.getString("icon"));
                    news_cls.setMytime(jsonObject.getString("stamp"));
                    news_cls.setMylink(jsonObject.getString("link"));
                    newBeanList.add(news_cls);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newBeanList;
    }

    /**
     * 通过InputStream解析网页返回的数据
     *
     * @param is
     * @return
     */
    private String readStream(InputStream is) {
        InputStreamReader isr;
        String result = "";
        try {
            String line;
            isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            try {
                while ((line = br.readLine()) != null) {
                    result += line;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 实现了网络的异步访问
     */
    class NewsAsyncTask extends AsyncTask<String, Integer, List<NewsBase>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<NewsBase> doInBackground(String... params) {
            publishProgress(values);
            return getJsonData(params[0]);
        }

        @Override
        protected void onPostExecute(final List<NewsBase> news_clses) {
            super.onPostExecute(news_clses);
            adapter = new item_RecyclerAdapter(news_clses, getActivity());
//            ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
//                @Override
//                public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//
//                    int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
//                    int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
//                    return makeMovementFlags(dragFlags, swipeFlags);
//                }
//
//                @Override
//                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                    Collections.swap(news_clses, viewHolder.getAdapterPosition(), target.getAdapterPosition());
//                    adapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
//                    return false;
//                }
//
//                @Override
//                public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//                    news_clses.remove(viewHolder.getAdapterPosition());
//                    adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
//                }
//
//                @Override
//                public boolean isLongPressDragEnabled() {
//                    return true;
//                }
//            });
//            helper.attachToRecyclerView(myRecyclerView);
            LinearLayoutManager layoutManger = new LinearLayoutManager(getActivity());
            layoutManger.setOrientation(LinearLayoutManager.VERTICAL);
            myRecyclerView.setLayoutManager(layoutManger);
            adapter.setOnItemClickListener(new item_RecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    String url = news_clses.get(position).getMylink();
                    Intent intent = new Intent(getActivity(), VebViewFragment.class);
                    intent.putExtra("kk", url);
                    startActivity(intent);
                }
            });
            adapter.notifyDataSetChanged();
            myRecyclerView.setAdapter(adapter);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int vlaue = values[0];
        }
    }

    private TextView tv_date;
    private TextView tv_title;
    private TextView tv_topic_from;
    private TextView tv_topic;
    public static String IMAGE_CACHE_PATH = "imageloader/Cache"; // 图片缓存路径
    private ViewPager adViewPager;
    private List<ImageView> imageViews;// 滑动的图片集合
    private List<View> dots; // 图片标题正文的那些点
    private List<View> dotList;
    private int currentItem = 0; // 当前图片的索引号
    private ScheduledExecutorService scheduledExecutorService;// 异步加载图片
    private ImageLoader mImageLoader;
    private DisplayImageOptions options;// 轮播banner的数据
    private List<AdDomain> adList;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            adViewPager.setCurrentItem(currentItem);
        }
    };

    private void initImageLoader() {
        File cacheDir = com.nostra13.universalimageloader.utils.StorageUtils.getOwnCacheDirectory(getContext(), IMAGE_CACHE_PATH);
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.top_banner_android)
                .showImageForEmptyUri(R.drawable.top_banner_android)
                .showImageOnFail(R.drawable.top_banner_android)
                .cacheInMemory(true).cacheOnDisc(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY).build();

//        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
//                .cacheInMemory(true).cacheOnDisc(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getContext())
                .defaultDisplayImageOptions(options)
                .memoryCache(new LruMemoryCache(12 * 1024 * 1024))
                .memoryCacheSize(12 * 1024 * 1024)
                .discCacheSize(32 * 1024 * 1024).discCacheFileCount(100)
                .discCache(new UnlimitedDiscCache(cacheDir))
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();

        mImageLoader.init(config);
    }

    private void initAdData() {
        // 广告数据
        adList = getBannerAd();
        imageViews = new ArrayList<ImageView>();
        // 点
        dots = new ArrayList<>();
        dotList = new ArrayList<>();
        View dot0 = rootView.findViewById(R.id.v_dot0);
        View dot1 = rootView.findViewById(R.id.v_dot1);
        View dot2 = rootView.findViewById(R.id.v_dot2);
        View dot3 = rootView.findViewById(R.id.v_dot3);
        View dot4 = rootView.findViewById(R.id.v_dot4);
        dots.add(dot0);
        dots.add(dot1);
        dots.add(dot2);
        dots.add(dot3);
        dots.add(dot4);

        tv_date = (TextView) rootView.findViewById(R.id.tv_date);
        tv_title = (TextView) rootView.findViewById(R.id.tv_title);
        tv_topic_from = (TextView) rootView.findViewById(R.id.tv_topic_from);
        tv_topic = (TextView) rootView.findViewById(R.id.tv_topic);

        adViewPager = (ViewPager) rootView.findViewById(R.id.vp);
        adViewPager.setAdapter(new MyAdapter());// 设置填充ViewPager页面的适配器
//        设置一个监听器，当ViewPager中的页面改变时调用
        adViewPager.setOnPageChangeListener(new MyPageChangeListener());
        addDynamicView();
    }

    private void addDynamicView() {
        // 动态添加图片和下面指示的圆点
        // 初始化图片资源
        for (int i = 0; i < adList.size(); i++) {
            ImageView imageView = new ImageView(getActivity());
            // 异步加载图片
            mImageLoader.displayImage(adList.get(i).getImgUrl(), imageView, options);

            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageViews.add(imageView);
            dots.get(i).setVisibility(View.VISIBLE);
            dotList.add(dots.get(i));
        }
    }

    /**
     * 当Activity显示出来后，每两秒切换一次图片显示
     */
    private void startAd() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2,
                TimeUnit.SECONDS);
    }

    private class ScrollTask implements Runnable {
        @Override
        public void run() {
            synchronized (adViewPager) {
                currentItem = (currentItem + 1) % imageViews.size();
                handler.obtainMessage().sendToTarget();
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        // 当Activity不可见的时候停止切换
        scheduledExecutorService.shutdown();
    }

    private class MyPageChangeListener implements CustomViewAbove.OnPageChangeListener, ViewPager.OnPageChangeListener {
        private int oldPosition = 0;

        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            currentItem = position;
            AdDomain adDomain = adList.get(position);
            tv_title.setText(adDomain.getTitle()); // 设置标题
            tv_date.setText(adDomain.getDate());
            tv_topic_from.setText(adDomain.getTopicFrom());
            tv_topic.setText(adDomain.getTopic());

            dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
            dots.get(position).setBackgroundResource(R.drawable.dot_focused);
            oldPosition = position;
        }
    }

    //轮播适配
    private class MyAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return adList.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = imageViews.get(position);
            (container).addView(iv);
            final AdDomain adDomain = adList.get(position);
            // 在这个方法里面设置图片的点击事件
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 处理跳转逻辑
//                    Toast.makeText(getContext(), adDomain.getDate(), Toast.LENGTH_SHORT).show();
                }
            });
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
//            ((ViewPager) arg0).removeView((View) arg2);
            container.removeView(imageViews.get(position));

        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

    }

    /**
     * 轮播广播模拟数据
     *
     * @return
     */
    public static List<AdDomain> getBannerAd() {
        List<AdDomain> adList = new ArrayList<>();

        AdDomain adDomain = new AdDomain();
        adDomain.setId("108078");
        adDomain.setDate("3月4日");
        adDomain.setTitle("我和令计划只是同姓");
        adDomain.setTopicFrom("阿宅");
        adDomain.setTopic("我想知道令狐安和令计划有什么关系？");
        adDomain.setImgUrl("http://g.hiphotos.baidu.com/image/w%3D310/sign=bb99d6add2c8a786be2a4c0f5708c9c7/d50735fae6cd7b8900d74cd40c2442a7d9330e29.jpg");
        adDomain.setAd(false);
        adList.add(adDomain);

        AdDomain adDomain2 = new AdDomain();
        adDomain2.setId("108078");
        adDomain2.setDate("3月5日");
        adDomain2.setTitle("我和令计划只是同姓");
        adDomain2.setTopicFrom("小巫");
        adDomain2.setTopic("“我想知道令狐安和令计划有什么关系？”");
        adDomain2.setImgUrl("http://g.hiphotos.baidu.com/image/w%3D310/sign=7cbcd7da78f40ad115e4c1e2672e1151/eaf81a4c510fd9f9a1edb58b262dd42a2934a45e.jpg");
        adDomain2.setAd(false);
        adList.add(adDomain2);

        AdDomain adDomain3 = new AdDomain();
        adDomain3.setId("108078");
        adDomain3.setDate("3月6日");
        adDomain3.setTitle("我和令计划只是同姓");
        adDomain3.setTopicFrom("旭东");
        adDomain3.setTopic("“我想知道令狐安和令计划有什么关系？”");
        adDomain3.setImgUrl("http://e.hiphotos.baidu.com/image/w%3D310/sign=392ce7f779899e51788e3c1572a6d990/8718367adab44aed22a58aeeb11c8701a08bfbd4.jpg");
        adDomain3.setAd(false);
        adList.add(adDomain3);

        AdDomain adDomain4 = new AdDomain();
        adDomain4.setId("108078");
        adDomain4.setDate("3月7日");
        adDomain4.setTitle("我和令计划只是同姓");
        adDomain4.setTopicFrom("小软");
        adDomain4.setTopic("“我想知道令狐安和令计划有什么关系？”");
        adDomain4.setImgUrl("http://d.hiphotos.baidu.com/image/w%3D310/sign=54884c82b78f8c54e3d3c32e0a282dee/a686c9177f3e670932e4cf9338c79f3df9dc55f2.jpg");
        adDomain4.setAd(false);
        adList.add(adDomain4);

        AdDomain adDomain5 = new AdDomain();
        adDomain5.setId("108078");
        adDomain5.setDate("3月8日");
        adDomain5.setTitle("我和令计划只是同姓");
        adDomain5.setTopicFrom("大熊");
        adDomain5.setTopic("“我想知道令狐安和令计划有什么关系？”");
        adDomain5.setImgUrl("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3632343972,1043291895&fm=27&gp=0.jpg");
        adDomain5.setAd(true); // 代表是广告
        adList.add(adDomain5);
        return adList;
    }
}
