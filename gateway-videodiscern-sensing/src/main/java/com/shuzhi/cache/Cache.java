package com.shuzhi.cache;

import com.shuzhi.entity.CommandInfo;
import com.shuzhi.entity.TGatewayConfigEntity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: 缓存对象
 * @Author:     lirb
 * @CreateDate:   2019/7/23 13:33
 * @Version:   1.0
 **/
public class Cache {

    //设备命令缓存 Key:cmdid
    public static Map<String, CommandInfo> commandMap = new ConcurrentHashMap<>();
    //网关链路信息缓存
    public static TGatewayConfigEntity gatewayConfigEntity = new TGatewayConfigEntity();

}
