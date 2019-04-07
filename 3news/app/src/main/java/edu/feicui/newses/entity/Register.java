package edu.feicui.newses.entity;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/12/17.
 */

public class Register {
    //服务器返回结果
    String result;
    //用户令牌，用于验证用户。每次请求都传递给服务器。具有实效期。
    String token;
    //服务器返回结果说明
    String explain;
    public static void saveUser(Context context, BaseEntity<User> user){
        //保存文件名为“user”
        //其中用户名保存的键值对为：userName，
        //头像路径为：headImage
        SharedPreferences sp=context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        User core=user.getData();
        editor.putString("userName",core.getUid());
        editor.putString("userName",core.getPortrait());
        editor.commit();
    }
    /**清除用户数据*/
    public static void clearUser(Context context){
        //将本地保存的用户数据“user”文件清除
        SharedPreferences sp=context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.clear();
        editor.commit();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
