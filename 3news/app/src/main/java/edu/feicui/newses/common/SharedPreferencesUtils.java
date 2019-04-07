package edu.feicui.newses.common;

import android.content.Context;
import android.content.SharedPreferences;

import edu.feicui.newses.entity.BaseEntity;
import edu.feicui.newses.entity.Register;

/**
 * Created by Administrator on 2016/12/17.
 */

public class SharedPreferencesUtils {
    /**此方法用于注册或者是登录后，保存解析得到的内容*/
    public static void saveRegister(Context context, BaseEntity<Register> register){
        SharedPreferences sp=context.getSharedPreferences("register", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("message",register.getMessage());
        editor.putInt("status", Integer.parseInt(register.getStatus()));
        Register data=register.getData();
        editor.putString("result",data.getResult());
        editor.putString("token",data.getToken());
        editor.putString("explain",data.getExplain());
        editor.commit();
    }
    /**获取用户令牌*/
    public static String getToken(Context context){
        SharedPreferences sp=context.getSharedPreferences("register", Context.MODE_PRIVATE);
        return sp.getString("token","");
    }
    /**获取用户名和用户头像地址*/
    public static String[] getUserNameAndPhoto(Context context){
        SharedPreferences sp=context.getSharedPreferences("user", Context.MODE_PRIVATE);
        return new String[]{sp.getString("userName",""),sp.getString("headImage","")};
    }
    /**保存用户头像本地路径*/
    public static void saveUserLocalIcon(Context context, String path){
        SharedPreferences sp=context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("imagePath",path);
        editor.commit();
    }
    /**获取保存的本地头像路径*/
    public static String getUserLocalIcon(Context context){
        SharedPreferences sp=context.getSharedPreferences("user", Context.MODE_PRIVATE);
        return sp.getString("imagePath",null);
    }
}
