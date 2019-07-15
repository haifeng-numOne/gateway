package com.shuzhi.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuzhi.cache.Cache;
import com.shuzhi.entity.CommandInfo;
import com.shuzhi.entity.SystemInfoData;
import com.shuzhi.service.ReportService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    private Utils utils;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private ConfigData configData;


    /**
     * 上报
     */
    public String getReportUrl() {

        CommandInfo commandInfo = Cache.commandMap.get("10001");
        if (commandInfo == null) {
            logger.error("未查询到lcd设备cmdid为:" + "1001" + "的命令,放弃请求");
            return null;
        }

        //获取url
        String url = "http://" + commandInfo.getTdeviceFactoryEntity().getServerIp() + ":" + commandInfo.getTdeviceFactoryEntity().getServerPort() + commandInfo.getTmsgInfoEntity().getInterfaceId();
        StringBuilder sb = new StringBuilder();
        sb.append(url);
        sb.append("?account=");
        sb.append(configData.getAccount());
        sb.append("&password=");
        sb.append(configData.getPassword());

        return sb.toString();
    }

    /**
     * 上报请求实体
     *
     * @return
     */
    public SystemInfoData getRequestBody() {
        SystemInfoData infoData = new SystemInfoData();
        infoData.setMsgid("550e8400-e29b-41d4-a716-446655440000");
        infoData.setMsgtype(4);
        infoData.setSystype(1001);
        infoData.setSysid(1);
        infoData.setConnectid(1);
        infoData.setSign("4634e0d2f0b2b423936eb7651eacc54b98cb248f");
        return infoData;
    }

    /**
     * 发送上报请求
     */
    public void reportRequest() {
        String reportUrl = getReportUrl();
        if (StringUtils.isNotEmpty(reportUrl)) {
            reportService.reportService(reportUrl, getRequestBody());
        }
    }
}
