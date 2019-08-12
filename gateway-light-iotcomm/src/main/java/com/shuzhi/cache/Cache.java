package com.shuzhi.cache;

import com.shuzhi.entity.CommandInfo;
import com.shuzhi.entity.DeviceInfo;
import com.shuzhi.entity.TDeviceFactoryCronEntity;
import com.shuzhi.entity.TGatewayConfigEntity;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: 缓存对象
 * @Author:     lirb
 * @CreateDate:   2019/8/9 11:36
 * @Version:   1.0
 **/
public class Cache {

    //设备信息缓存 Key:cuuid value：deviceId
    public static Map<Integer, DeviceInfo> deviceInfoMap = new ConcurrentHashMap<>();

    //设备命令缓存 Key:cmdid
    public static Map<String, CommandInfo> commandMap = new ConcurrentHashMap<>();

    //网关链路信息缓存
    public static TGatewayConfigEntity gatewayConfigEntity = new TGatewayConfigEntity();

    public static String cookie = "";

    // 定时任务缓存
    public static TDeviceFactoryCronEntity cronEntity ;

}