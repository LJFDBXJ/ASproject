package com.example.administrator.anew.Adapater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.anew.R;
import com.example.administrator.anew.Utils_Tool.LoadImage;
import com.example.administrator.anew.Utils_Tool.NewsBase;

import java.util.List;

public class item_RecyclerAdapter extends RecyclerView.Adapter<item_RecyclerAdapter.MyViewHolder> {
    private LoadImage loadImage;
    private List<NewsBase> myNewsList;
    private OnItemClickListener onItemClickListener;
    private Context mContext;

    public item_RecyclerAdapter(List<NewsBase> news, Context myContext) {
        this.myNewsList = news;
        loadImage = new LoadImage( myContext, null );
        mContext = myContext;
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
            super( itemView );
            titlehead = itemView.findViewById( R.id.title_head_url );
            titlebody = itemView.findViewById( R.id.title_body_url );
            mytime = itemView.findViewById( R.id.time_url );
            image = itemView.findViewById( R.id.url_item_image );
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.inflate_item_url, parent, false );
        return new MyViewHolder( view );
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        NewsBase news = myNewsList.get( position );
        holder.titlehead.setText( news.getTitlehead() );
        holder.titlebody.setText( news.getTitlebody() );
        holder.mytime.setText( news.getMytime() );
        Log.e("tt",news.getTitlebody());
        String url = myNewsList.get( position ).getImage();
        Glide.with( mContext ).load( url ).into( holder.image );//Glide图片加载

        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int pos = holder.getLayoutPosition();
                            onItemClickListener.onItemClick( holder.itemView, pos );
                        }
                    }
            );
        }


//        holder.image.setTag(url);
//        boolean fling = true;
//        if (fling) {//外部图片加载
//            holder.image.setImageBitmap(loadImage.getBitmap(myNewsList.get(position).getImage()));
//        } else {
//            holder.image.setImageResource(R.drawable.animator);
//        }
//        LodeImage_Url myImageLode = new LodeImage_Url();
//        myImageLode.getShowImageByAsyncTask(holder.image, url);
    }
    //在指定位置插入，原位置的向后移动一格
    public boolean addItem ( int position, NewsBase msg){
        if (position < myNewsList.size() && position >= 0) {
            myNewsList.add( position, msg );
            notifyItemInserted( position );
            return true;
        }
        return false;
    } //去除指定位置的子项
    public boolean removeItem ( int position){
        if (position < myNewsList.size() && position >= 0) {
            myNewsList.remove( position );
            notifyItemRemoved( position );
            return true;
        }
        return false;
    } //清空显示数据
    public void clearAll () {
        myNewsList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return myNewsList.size();
    }
}