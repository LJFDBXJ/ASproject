package com.example.administrator.anew.Utils_Tool;

/**
 * Created by Administrator on 2017/2/7.
 */

public class NewsBase {
   private String titlehead;
    private String titlebody;
    private String mytime;
    private String image;
    private String mylink;

    public NewsBase(){}

    public NewsBase(String titlehead, String titlebody, String mytime, String image, String mylink) {
        this.titlehead = titlehead;
        this.titlebody = titlebody;
        this.mytime = mytime;
        this.image = image;
        this.mylink = mylink;
    }

    public String getTitlehead() {
        return titlehead;
    }

    public void setTitlehead(String titlehead) {
        this.titlehead = titlehead;
    }

    public String getTitlebody() {
        return titlebody;
    }

    public void setTitlebody(String titlebody) {
        this.titlebody = titlebody;
    }

    public String getMytime() {
        return mytime;
    }

    public void setMytime(String mytime) {
        this.mytime = mytime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setMylink(String mylink) {
        this.mylink = mylink;
    }

    public String getMylink() {
        return mylink;
    }
}
