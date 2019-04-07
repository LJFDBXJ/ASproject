package com.fuicuiedu.xc.videonew.ui.login_register;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.fuicuiedu.xc.videonew.R;
import com.fuicuiedu.xc.videonew.bmobapi.BmobClient;
import com.fuicuiedu.xc.videonew.bmobapi.UserApi;
import com.fuicuiedu.xc.videonew.bmobapi.result.ErrorResult;
import com.fuicuiedu.xc.videonew.bmobapi.result.UserResult;
import com.fuicuiedu.xc.videonew.commons.ToastUtils;
import com.google.gson.Gson;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;


public class LoginFragment extends DialogFragment{

    private Unbinder mUnbinder;

    @BindView(R.id.etUsername)
    EditText mEtUsername;
    @BindView(R.id.etPassword)
    EditText mEtPassword;
    @BindView(R.id.btnLogin)
    Button mBtnLogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /**无标题栏*/
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        /**butterknife解绑对象*/
        View view = inflater.inflate(R.layout.dialog_login, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    /**点击登录*/
    @OnClick(R.id.btnLogin)
    public void onClick(){
        final String username = mEtUsername.getText().toString();
        String password = mEtPassword.getText().toString();
        /**用户名和密码不能为空*/
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            ToastUtils.showShort(R.string.username_or_password_can_not_be_null);
            return;
        }

        /**显示进度条*/
        mBtnLogin.setVisibility(View.GONE);

        /**登录网络请求*/
        UserApi userApi = BmobClient.getInstance().getUserApi();
        /**拿到api中的get或者post请求，返回的是带泛型的call*/
        Call<UserResult> call = userApi.login(username,password);
        /**异步执行call*/
        call.enqueue(new retrofit2.Callback<UserResult>() {
            @Override
            public void onResponse(Call<UserResult> call, retrofit2.Response<UserResult> response) {
                /**显示进度条*/
                mBtnLogin.setVisibility(View.VISIBLE);
                /**登录未成功——打印错误的代码*/
                if (!response.isSuccessful()){
                    try {
                        String error = response.errorBody().string();
                        ErrorResult errorResult = new Gson().fromJson(error,ErrorResult.class);
                        ToastUtils.showShort(errorResult.getError());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                /**登录成功——响应体返回的是userresult实体类*/
                UserResult result = response.body();
                listener.loginSuccess(username,result.getObjectId());
                ToastUtils.showShort(R.string.login_success);
            }

            @Override
            public void onFailure(Call<UserResult> call, Throwable t) {
                /**隐藏进度条*/
                mBtnLogin.setVisibility(View.VISIBLE);
                ToastUtils.showShort(t.getMessage());
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    /**当登录成功之后触发的方法*/
    public interface OnLoginSuccessListener {
        /**
         * 当登录成功时，将来调用
         */
        void loginSuccess(String username, String objectId);
    }

    private OnLoginSuccessListener listener;

    public void setListener(@NonNull OnLoginSuccessListener listener) {
        this.listener = listener;
    }
}
