package com.shuzhi.utils;

import com.shuzhi.vo.R;

/**
 * 对象模型工具类
 * author: Yujq
 * date: 2018/4/5
 */
public class RUtil {

    public static R success(Object object){
        R r = new R();
        r.setCode(0);
        r.setMsg("ok");
        r.setData(object);
        return r;
    }

    public static R success(Object object,String sessionId){
        R r = new R();
        r.setCode(0);
        r.setMsg("ok");
        r.setSeesionId(sessionId);
        r.setData(object);
        return r;
    }

    public static R success(){
        return success(null);
    }

    public static R error(Integer code, String msg){
        R r = new R();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }
}