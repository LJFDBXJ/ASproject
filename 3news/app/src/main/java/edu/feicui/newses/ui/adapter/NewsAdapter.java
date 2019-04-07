package edu.feicui.newses.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.feicui.newses.R;
import edu.feicui.newses.common.LoadImage;
import edu.feicui.newses.entity.News;
import edu.feicui.newses.ui.base.MyBaseAdapter;

/**
 * Created by Administrator on 2016/12/13.
 */

public class NewsAdapter extends MyBaseAdapter<News> {
    private boolean isclick=false;
    private String icol;
    private ArrayList<News> arrayList=new ArrayList<>();
    private LoadImage image;
    private LayoutInflater layoutInflater;
    public NewsAdapter(Context context) {
        super(context);
      layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        image=new LoadImage(context, new LoadImage.ImageLoadListener() {
            @Override
            public void imageLoadOk(Bitmap bitmap, String url) {
                image.saveCachFile(url,bitmap);
                image.saveSoftReferences(url,bitmap);
                NewsAdapter.this.update();
            }
        });
    }
    //清除数据
    public void clean(){

        arrayList.clear();
    }
    //加载数据
    public void add(ArrayList<News> list){

        if(list!=null) {
            arrayList.addAll(list);
        }

    }


    public boolean isclick() {
        return isclick;
    }

    public void setIsclick(boolean isclick) {
        this.isclick = isclick;
    }

    @Override
    public View getMyView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder =null;
        if (convertView==null){
            holder=new ViewHolder();
            convertView=layoutInflater.inflate(R.layout.new_itekm,null);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
            holder.img_news=(ImageView)convertView.findViewById(R.id.iv_news);
            holder.tv_news1 =(TextView)convertView.findViewById(R.id.tv_news1);
            holder.tv_news2 =(TextView)convertView.findViewById(R.id.tv_news2);
        icol=(((News) (getItem(position))).getSummary());
        if (isclick==true) {
            holder.img_news.setImageResource(R.drawable.ic_launcher);
            holder.tv_news1.setText((getItem(position)).getTitle());
            holder.tv_news2.setText((getItem(position)).getSummary());
        } else if (isclick == false) {
            holder.tv_news1.setText((getItem(position)).getTitle());
            holder.tv_news2.setText( (getItem(position)).getSummary());
            holder.img_news.setImageBitmap(image.getBitmap(((getItem(position)).getIcon())));

        }
        return convertView;
    }
    private final class ViewHolder {
        ImageView img_news;
        TextView tv_news1;
        TextView tv_news2;

    }
}
