package edu.feicui.newses.entity;

/**
 * Created by Administrator on 2016/12/17.
 */

public class BaseEntity <T>{
    /**返回文字内容*/
    private String message;
    /**状态*/
    private String status;
    /**数据（可能是集合也可能是对象）*/
    private T data;
    public BaseEntity(){

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
