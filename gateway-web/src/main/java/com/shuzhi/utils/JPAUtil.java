package com.shuzhi.utils;

/**
 * author: Yujq
 * date: 2018/4/7
 */
public class JPAUtil {

    public static String like(String column){
        StringBuilder sb = new StringBuilder("%"+column+"%");
        return sb.toString();
    }
}
