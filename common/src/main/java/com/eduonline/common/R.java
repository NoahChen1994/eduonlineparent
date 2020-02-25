package com.eduonline.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义具体的数据格式
 */
@Data
public class R implements ResultCode{

    private Boolean success; //操作是否成功

    private Integer code;   //响应状态码

    private String message; //响应消息

    private Map<String,Object> data = new HashMap<>(); //响应数据

    private R(){}

    //操作成功 调用该方法
    public static R ok(){
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("操作成功");
        return r;
    }

    //操作失败
    public static R error(){
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("操作失败");
        return r;
    }

    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

}
