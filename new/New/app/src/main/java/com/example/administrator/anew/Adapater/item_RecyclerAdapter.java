package com.example.administrator.anew.Adapater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.anew.R;
import com.example.administrator.anew.Utils_Tool.LoadImage;
import com.example.administrator.anew.Utils_Tool.LodeImage_Url;
import com.example.administrator.anew.Utils_Tool.NewsBase;

import java.util.List;

public class item_RecyclerAdapter extends RecyclerView.Adapter<item_RecyclerAdapter.MyViewHolder> {
    private LoadImage loadImage;
    private List<NewsBase> myNews;
    private OnItemClickListener onItemClickListener;

    public item_RecyclerAdapter(List<NewsBase> news, Context myContext) {
        this.myNews = news;
        loadImage = new LoadImage(myContext, null);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener monItemClickListener) {
        this.onItemClickListener = monItemClickListener;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titlehead, titlebody, mytime;
        ImageView image;

        private MyViewHolder(View itemView) {
            super(itemView);
            titlehead = (TextView) itemView.findViewById(R.id.title_head_url);
            titlebody = (TextView) itemView.findViewById(R.id.title_body_url);
            mytime = (TextView) itemView.findViewById(R.id.time_url);
            image = (ImageView) itemView.findViewById(R.id.url_item_image);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_item_url, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        NewsBase news = myNews.get(position);
        holder.titlehead.setText(news.getTitlehead());
        holder.titlebody.setText(news.getTitlebody());
        holder.mytime.setText(news.getMytime());
        String url = myNews.get(position).getImage();

        LodeImage_Url myImageLode = new LodeImage_Url();

//        boolean fling = true;
//        if (fling) {//外部图片加载
//            holder.image.setImageBitmap(loadImage.getBitmap(myNews.get(position).getImage()));
//        } else {
//            holder.image.setImageResource(R.drawable.animator);
//        }
//        Glide.with(myContext).load(url).into(holder.image);//Glide图片加载
        holder.image.setTag(url);
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int pos = holder.getLayoutPosition();
                            onItemClickListener.onItemClick(holder.itemView, pos);
                        }
                    }
            );
        }

        myImageLode.getShowImageByAsyncTask(holder.image, url);
    }

    @Override
    public int getItemCount() {
        return myNews.size();
    }
//    private LodeImage_Url myImageLode;
//    private List<NewsBase> mNewsList;
//    LayoutInflater myinflate;
//
//    public item_RecyclerAdapter(Context context, List<NewsBase> mNewsList) {
//        myImageLode = new LodeImage_Url();
//        this.mNewsList = mNewsList;
//        this.myinflate =LayoutInflater.from(context);
//    }
//    @Override
//    public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//    }
//    @Override
//    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//
//    }
//    @Override
//    public int getCount() {
//        return mNewsList.size();
//    }
//    @Override
//    public Object getItem(int position)
//    {
//        return mNewsList.get(position);
//    }
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewhlder;
//        if(convertView==null){
//            viewhlder=new ViewHolder();
//            convertView=myinflate.inflate(R.layout.inflate_item_url,null);
//            viewhlder.titlehead= (TextView) convertView.findViewById(R.id.title_head_url);
//            viewhlder.titlebody= (TextView) convertView.findViewById(R.id.title_body_url);
//            viewhlder.mytime= (TextView) convertView.findViewById(R.id.time_url);
//            viewhlder.icIcon= (ImageView) convertView.findViewById(R.id.url_item_image);
//            convertView.setTag(viewhlder);
//        }
//        else {
//            viewhlder= (ViewHolder) convertView.getTag();
//        }
//        viewhlder.titlehead.setText(mNewsList.get(position).titlehead);
//        viewhlder.titlebody.setText(mNewsList.get(position).titlebody);
//        viewhlder.mytime.setText(mNewsList.get(position).mytime);
////        viewhlder.icIcon.setImageResource(R.mipmap.ic_cc);
//        String url=mNewsList.get(position).image;
//        viewhlder.icIcon.setTag(url);
//        myImageLode.getShowImageByAsyncTask(viewhlder.icIcon,url);
//        return convertView;
//    }
// class ViewHolder{
//    public TextView titlehead, titlebody,mytime;
//     public ImageView icIcon;
//}
}