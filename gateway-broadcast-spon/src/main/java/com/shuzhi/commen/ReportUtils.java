package com.shuzhi.commen;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuzhi.cache.Cache;
import com.shuzhi.entity.CommandInfo;
import com.shuzhi.entity.SystemInfoData;
import com.shuzhi.entity.command.GetZoneTerminalData;
import com.shuzhi.service.CommandService;
import com.shuzhi.service.ReportService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

/**
 * @Description: 上报相关业务工具类
 * @Author: lirb
 * @CreateDate: 2019/7/15 13:35
 * @Version: 1.0
 **/
@Component
public class ReportUtils {

    private final static Logger logger = LoggerFactory.getLogger(ReportUtils.class);

    @Autowired
    private ReportService reportService;
    @Autowired
    private ConfigData configData;

    private final String url = "/php/getzoneterminaldata.php";

    /**
     * 上报
     */
    public String getReportUrl() {

        CommandInfo commandInfo =null;
        Map<String, CommandInfo> commandMap = Cache.commandMap;
        for(CommandInfo command:commandMap.values()){
            commandInfo=command;
            if(commandInfo!=null){
                break;
            }
        }
        //获取url
        StringBuilder sb = new StringBuilder();
        sb.append("http://");
        sb.append(commandInfo.getTdeviceFactoryEntity().getServerIp());
        sb.append(":");
        sb.append(commandInfo.getTdeviceFactoryEntity().getServerPort());
        sb.append(url);
        return sb.toString();
    }

    /**
     * 上报请求实体
     *
     * @return
     */
    public SystemInfoData getRequestBody() {
        //暂时写死，后期从数据库取
        SystemInfoData infoData = new SystemInfoData();
        infoData.setMsgid(UUID.randomUUID().toString());
        infoData.setMsgtype(4);
        infoData.setSystype(configData.getSysType());
        infoData.setSysid(Integer.parseInt(Cache.gatewayConfigEntity.getSysId()));
        infoData.setConnectid(Integer.parseInt(Cache.gatewayConfigEntity.getConnectId()));
        return infoData;
    }

    /**
     * 请求参数
     *
     * @return
     */
    public GetZoneTerminalData getRequestParams() {
        GetZoneTerminalData params = new GetZoneTerminalData();
        params.setPageIndex(0);
        params.setPageCount(Cache.deviceInfoMap.size()+10);
        params.setGroupName("*");
        params.setShowType("1");
        params.setSimple(1);
        return params;
    }

    /**
     * 发送上报请求
     */
    public void reportRequest() {
        String url = getReportUrl();
        GetZoneTerminalData gztd = getRequestParams();
        SystemInfoData systemInfoData = getRequestBody();
        reportService.reportService(url, gztd.toString(), systemInfoData);
    }
}
