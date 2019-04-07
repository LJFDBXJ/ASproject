package android.weather.lifdbxj.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/2/15.
 */

public class Basic {
    @SerializedName("city")
    public String cityName;
    @SerializedName("id")
    public String weatherId;

    public Update update;
    public class Update{
        @SerializedName("loc")
        public String updateTime;
    }
    /**
     * 由于JSON中的一些字段可能不太适合直接作为JAVA字段来命名，因此这里使用了@Ser...注解的方式来
     * 让JSON字段和JAVA字段之间建立映射关系
     */
}
