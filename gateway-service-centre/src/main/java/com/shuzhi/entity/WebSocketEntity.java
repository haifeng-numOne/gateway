package com.shuzhi.entity;

import com.shuzhi.pojo.MessagePojo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.websocket.Session;

/**
* @Program: WebSocketEntity
* @Description:
* @Author: YuJQ
* @Create: 2019/6/6 11:05
**/

@Data
public class WebSocketEntity {
    private Session session;
    private Boolean firstAllianceConnection =false;//建立连接是否发送首次建立按信息
    private Integer sendCount = 0;//发送次数
    private String message;//发送消息
    private Long sendTimeStamp; //发送时间戳 年月日时分秒 毫秒值
    private Long expiresTimeStamp; //过期毫秒值
    private TGatewayConfigEntity tGatewayConfigEntity;

}
