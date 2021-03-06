package com.shuzhi.cache;

import com.shuzhi.entity.CommandInfo;
import com.shuzhi.entity.DeviceInfo;
import com.shuzhi.entity.DeviceState;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:缓存对象
 * @Author: ztt
 * @date 17:01
 */
public class Cache {

    //设备信息缓存 Key:did
    public static Map<String, DeviceInfo> deviceInfoMap = new ConcurrentHashMap<>();
    //设备命令缓存 Key:cmdid
    public static Map<String, CommandInfo> commandMap = new ConcurrentHashMap<>();
    //设备上报结果缓存
    public static List<DeviceState> reportResultList = new ArrayList<>();
}
