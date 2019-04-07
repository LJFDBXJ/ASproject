package com.example.administrator.anew.com.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/1/13.
 */
public class About_Fragment extends Fragment {
    private static final String TAG = "TAG";
    private RecyclerView myRecyclerView;
    public static final String ARG_TOOL_TYPE = "toolType";
    private NewsBase news_cls;
    private item_RecyclerAdapter adapter;
    private String param;
    private View rootView;
    private List<NewsBase> newBeanList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (getArguments() != null) {
            int  urlTag=getArguments().getInt( TAG );
            switch (urlTag){
                case 0:
                    param="http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";
                    break;
               case 1:
                    param= "http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=2&dir=1&nid=1&stamp=20140321&cnt=20";
                   break;
                case 2:
                    param="http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=3&dir=1&nid=1&stamp=20140321&cnt=20";
                    break;
                case 3:
                    param="http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=4&dir=1&nid=1&stamp=20140321&cnt=20";
                    break;
                case 4:
                    param="http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=5&dir=1&nid=1&stamp=20140321&cnt=20";
                    break;
                case 5:
                    param="http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=7&dir=1&nid=1&stamp=20140321&cnt=20";
                    break;
                case 6:
                    param="http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=8&dir=1&nid=1&stamp=20140321&cnt=20";


            }
        }
    }

    public static About_Fragment newInstance(int tag) {
        About_Fragment fragment = new About_Fragment();
        Bundle args = new Bundle();
        args.putInt( TAG, tag );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate( R.layout.fragment_tool_about, container, false );
        myRecyclerView = rootView.findViewById( R.id.recyclerView );
        // 获取图片加载实例
        mImageLoader = ImageLoader.getInstance();
        initImageLoader();//初始化图片加载器
        initAdData();//初始化广告数据
        startAd();
        async( param );
        return rootView;
    }

    public void async(String url) {
        NewsAsyncTask asyncTask = new NewsAsyncTask();
        asyncTask.execute( url );

    }

    private List<NewsBase> getJsonData(String url) {
        Log.d( "instantiateItem",url+"5464564" );

        newBeanList = new ArrayList<>();
        OkHttpClient client=new OkHttpClient();
        Request request =new Request.Builder().url(url).build();
        client.newCall(request).enqueue( new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonString = response.body().string();
                Log.d( "instantiateItem",jsonString+"5464564" );
                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject( jsonString );
                    JSONArray jsonArray = jsonObject.getJSONArray( "data" );

                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject = jsonArray.getJSONObject( i );

                        news_cls = new NewsBase();
                        news_cls.setTitlehead( jsonObject.getString( "title" ) );
                        news_cls.setTitlebody( jsonObject.getString( "summary" ) );

                        news_cls.setImage( jsonObject.getString( "icon" ) );
                        news_cls.setMytime( jsonObject.getString( "stamp" ) );
                        news_cls.setMylink( jsonObject.getString( "link" ) );
                        newBeanList.add( news_cls );
                    }
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        } );
        return newBeanList;
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

            return getJsonData( params[0] );
        }

        @Override
        protected void onPostExecute(final List<NewsBase> news_clses) {
            super.onPostExecute( news_clses );
            adapter = new item_RecyclerAdapter( news_clses, getActivity() );
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
            LinearLayoutManager layoutManger = new LinearLayoutManager( getActivity() );
            layoutManger.setOrientation( LinearLayoutManager.VERTICAL );
            myRecyclerView.setLayoutManager( layoutManger );

            adapter.setOnItemClickListener( new item_RecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    String url = news_clses.get( position ).getMylink();
                    Intent intent = new Intent( getActivity(), VebView_Fragment.class );
                    intent.putExtra( "kk", url );
                    startActivity( intent );
                }
            } );
            adapter.notifyDataSetChanged();
            myRecyclerView.setAdapter( adapter );
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate( values );
        }
    }

    private TextView tv_date;
    private TextView tv_title;
    private TextView tv_topic_from;
    private TextView tv_topic;
    public static String IMAGE_CACHE_PATH = "imageloader/Cache"; // 图片缓存路径
    private ViewPager adViewPager;

    private List<ImageView> imageList;// 滑动的图片集合
    private List<View> dotList; // 图片标题正文的那些点

    private int currentItem = 0; // 当前图片的索引号
    private ScheduledExecutorService scheduledExecutorService;// 异步加载图片
    private ImageLoader mImageLoader;
    private DisplayImageOptions options;// 轮播banner的数据
    private List<AdDomain> adList;
    private final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            adViewPager.setCurrentItem( currentItem );
        }
    };

    private void initImageLoader() {
        File cacheDir = com.nostra13.universalimageloader.utils.StorageUtils.getOwnCacheDirectory( getContext(), IMAGE_CACHE_PATH );
        options = new DisplayImageOptions.Builder()
                .showStubImage( R.drawable.top_banner_android )
                .showImageForEmptyUri( R.drawable.top_banner_android )
                .showImageOnFail( R.drawable.top_banner_android )
                .cacheInMemory( true ).cacheOnDisc( true )
                .bitmapConfig( Bitmap.Config.RGB_565 )
                .imageScaleType( ImageScaleType.EXACTLY ).build();

//        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
//                .cacheInMemory(true).cacheOnDisc(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder( getContext() )
                .defaultDisplayImageOptions( options )
                .memoryCache( new LruMemoryCache( 12 * 1024 * 1024 ) )
                .memoryCacheSize( 12 * 1024 * 1024 )
                .discCacheSize( 32 * 1024 * 1024 ).discCacheFileCount( 100 )
                .discCache( new UnlimitedDiscCache( cacheDir ) )
                .threadPriority( Thread.NORM_PRIORITY - 2 )
                .tasksProcessingOrder( QueueProcessingType.LIFO ).build();

        mImageLoader.init( config );
    }

    private void initAdData() {

        // 广告数据
        adList = getBannerAd();
        imageList = new ArrayList<>();
        // 点
        dotList = new ArrayList<>();
        View dot0 = rootView.findViewById( R.id.v_dot0 );
        View dot1 = rootView.findViewById( R.id.v_dot1 );
        View dot2 = rootView.findViewById( R.id.v_dot2 );
        View dot3 = rootView.findViewById( R.id.v_dot3 );
        View dot4 = rootView.findViewById( R.id.v_dot4 );
        dotList.add( dot0 );
        dotList.add( dot1 );
        dotList.add( dot2 );
        dotList.add( dot3 );
        dotList.add( dot4 );


        tv_date = rootView.findViewById( R.id.tv_date );
        tv_title = rootView.findViewById( R.id.tv_title );
        tv_topic_from = rootView.findViewById( R.id.tv_topic_from );
        tv_topic = rootView.findViewById( R.id.tv_topic );

        adViewPager = rootView.findViewById( R.id.vp );
        adViewPager.setAdapter( new MyAdapter() );// 设置填充ViewPager页面的适配器
//        设置一个监听器，当ViewPager中的页面改变时调用
        adViewPager.setOnPageChangeListener( new MyPageChangeListener() );
        addDynamicView();
    }

    private void addDynamicView() {
        // 动态添加图片和下面指示的圆点
        // 初始化图片资源
        for (int i = 0; i < adList.size(); i++) {
            ImageView imageView = new ImageView( getActivity() );
            // 异步加载图片
            mImageLoader.displayImage( adList.get( i ).getImgUrl(), imageView, options );
            imageView.setScaleType( ImageView.ScaleType.CENTER_CROP );
            imageList.add( imageView );

            dotList.get( i ).setVisibility( View.VISIBLE );
        }
    }

    /**
     * 当Activity显示出来后，每两秒切换一次图片显示
     */
    private void startAd() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate( new ScrollTask(), 1, 2,
                TimeUnit.SECONDS );
    }

    private class ScrollTask implements Runnable {
        @Override
        public void run() {
            synchronized (adViewPager) {
                currentItem = (currentItem + 1) % imageList.size();
                handler.obtainMessage().sendToTarget();
            }
        }
    }
    // 当Activity不可见的时候停止切换
    @Override
    public void onStop() {
        super.onStop();
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
            AdDomain adDomain = adList.get( position );
            tv_title.setText( adDomain.getTitle() ); // 设置标题
            tv_date.setText( adDomain.getDate() );
            tv_topic_from.setText( adDomain.getTopicFrom() );
            tv_topic.setText( adDomain.getTopic() );

            dotList.get( oldPosition ).setBackgroundResource( R.drawable.dot_normal );
            dotList.get( position ).setBackgroundResource( R.drawable.dot_focused );
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
            ImageView iv = imageList.get( position );
            (container).addView( iv );

            final AdDomain adDomain = adList.get( position );
            // 在这个方法里面设置图片的点击事件
            iv.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 处理跳转逻辑
                    Toast.makeText( getContext(), adDomain.getDate(), Toast.LENGTH_SHORT ).show();
                }
            } );
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem( container, position, object );
//            ((ViewPager) arg0).removeView((View) arg2);
            container.removeView( imageList.get( position ) );

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
        adDomain.setId( "108078" );
        adDomain.setDate( "3月4日" );
        adDomain.setTitle( "我和令计划只是同姓" );
        adDomain.setTopicFrom( "阿宅" );
        adDomain.setTopic( "我想知道令狐安和令计划有什么关系？" );
        adDomain.setImgUrl( "http://g.hiphotos.baidu.com/image/w%3D310/sign=bb99d6add2c8a786be2a4c0f5708c9c7/d50735fae6cd7b8900d74cd40c2442a7d9330e29.jpg" );
        adDomain.setAd( false );
        adList.add( adDomain );
        return adList;
    }
}
