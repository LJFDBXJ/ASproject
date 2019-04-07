package edu.feicui.newses.model.biz.parser;

import android.app.DownloadManager;
import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.net.sip.SipAudioCall;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Date;

import javax.xml.transform.ErrorListener;

import edu.feicui.newses.common.CommonUtil;

/**
 * Created by Administrator on 2016/12/21.
 */

public class UpdateManager {
    /**下载版本、下载地址*/
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void downLoad(Context context, String url){
        /**初始化下载管理器*/
        DownloadManager manager=(DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
        /**创建请求*/
        DownloadManager.Request request=new DownloadManager.Request(Uri.parse(url));
        /**设置允许使用的网络类型，wifi*/
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        /**在通知栏显示下载详情在API 11中被setNotificationVisibility()取代*/
        request.setShowRunningNotification(true);
        /**显示下载界面*/
        request.setVisibleInDownloadsUi(true);
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-ddhh-mm-ss");
        String date=dateFormat.format(new Date());
        /*设置下载后文件存放的位置--如果目标位置已经存在这个文件名，则不执行下载，所以用data类型随机取名*/
        request.setDestinationInExternalFilesDir(context,null,date+".apk");
        /*将下载请求放入队列*/
        manager.enqueue(request);
    }
    /**判断是否更新、请求路径地址、回调接口、请求参数，顺序如下：arg[0]:IMEI,arg[1]:pkg,arg[2]:ver*/
//    public static void judgeUpdate(ResponseHandlerInterface responseHandler,String...args){
//        String url= CommonUtil.APPURL+"/update?imei="+args[0]+"&pkg="+args[1]+"&ver="+args[2];
//        new AsyncHttpClient().get(url,responseHandler);
//    }
//    public static void judgeUpdate(Context context, Listener<String> listener, ErrorListener errorListener,String...args){
//        String url= CommonUtil.APPURL+"/update?imei="+args[0]+"&pkg="+args[1]+"&ver="+args[2];
////        new AsyncHttpClient().get(url,responseHandler);
//        new VolleyHttp(context).getJSONObject(url,listener,errorListener);
//    }

}
