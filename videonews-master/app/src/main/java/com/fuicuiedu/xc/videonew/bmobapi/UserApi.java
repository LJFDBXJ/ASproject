package com.fuicuiedu.xc.videonew.bmobapi;

import com.fuicuiedu.xc.videonew.bmobapi.entity.UserEntity;
import com.fuicuiedu.xc.videonew.bmobapi.result.UserResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 *   \ 用户登录和注册的相关网络接口
 */

public interface UserApi {

    //登录
    @GET("1/login")
    Call<UserResult> login(
            @Query("username") String username,
            @Query("password") String password
    );

    //注册
    @POST("1/users")
    Call<UserResult> register(@Body UserEntity userEntity);
}
