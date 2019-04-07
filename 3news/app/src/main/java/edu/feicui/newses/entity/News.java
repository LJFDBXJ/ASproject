package edu.feicui.newses.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/12.
 */

public class News implements Serializable {
    private int type;
    private int nid;
    private String stamp;
    private String icon;
    private String title;
    private String summary;
    private String link;
    public News(int type, int nid, String stamp, String icon, String title, String summary, String link){
        this.type=type;
        this.nid=nid;
        this.stamp=stamp;
        this.icon=icon;
        this.title=title;
        this.summary=summary;
        this.link=link;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return type+ nid+stamp+icon+title+summary+link;
    }
}
