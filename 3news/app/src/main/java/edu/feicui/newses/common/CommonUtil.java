package edu.feicui.newses.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/12/8.
 */

public class CommonUtil {
    /**联网的路径*/
    public static final String APPURL="http://118.244.212.82:9092/newsClient";
    /**当前版本号*/
    public static final int VERSION_CODE=1;
    /**联网的ip*/
    public static String NETIP="118.244.212.82";
    /** 联网的路径 */
    public static String NETPATH="http://"+NETIP+":9092/newsClient";
    /** SharedPreferences 保存用户名键 */
    public static final String SHARE_USER_NAME = "userName";
    /** SharedPreferences 保存用户名密码 */
    public static final String SHARE_USER_PWD = "userPwd";
    /** SharedPreferences 保存是否第一次登陆 */
    public static final String SHARE_IS_FIRST_RUN = "isFirstRun";
    /** SharedPreferences 存储路径 */
    public static final String SHAREPATH = "news_share";
    public static String getSystime() {
        String systime;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEEE");
        /**获取当前时间并刷新格式*/
        systime=dateFormat.format(new Date(System.currentTimeMillis()));
        return systime;
    }
    /**获取当前日期*/
    public static String getDate(){
        Date date=new Date(System.currentTimeMillis());
        String strs="";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyMMdd");
        strs=sdf.format(date);
        return strs;
    }
    public static String getSystime1(){
        String systime;
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddhhmmss");
        systime=dateFormat.format(new Date(System.currentTimeMillis()));
        return systime;
    }
    /**验证邮箱格式*/
    public static boolean verifyEmail(String email){
        Pattern pattern= Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)"+"|(([a-zA-Z0-9\\-]+\\.)+))"+"([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher matcher=pattern.matcher(email);
        return matcher.matches();
    }
    /**验证密码格式*/
    public static boolean verifyPassword(String password){
        Pattern pattern= Pattern.compile("^[a-zA-Z0-9]{6,16}$");
        Matcher matcher=pattern.matcher(password);
        return matcher.matches();
    }

}
