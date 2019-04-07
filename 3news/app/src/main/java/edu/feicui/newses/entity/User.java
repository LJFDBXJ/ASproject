package edu.feicui.newses.entity;

/**
 * Created by Administrator on 2016/12/17.
 */

public class User {
    private String uid;
    private String email;
    private int integration;
    private int comnum;
    private String portrait;
//    private List<LoginLog> loginLog;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIntegration() {
        return integration;
    }

    public void setIntegration(int integration) {
        this.integration = integration;
    }

    public int getComnum() {
        return comnum;
    }

    public void setComnum(int comnum) {
        this.comnum = comnum;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

//    public List<LoginLog> getLoginLog() {
//        return loginLog;
//    }
//
//    public void setLoginLog(List<LoginLog> loginLog) {
//        this.loginLog = loginLog;
//    }
}
