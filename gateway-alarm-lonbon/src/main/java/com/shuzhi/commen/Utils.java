package com.shuzhi.commen;
import com.fasterxml.jackson.databind.JsonNode;
import com.shuzhi.cache.Cache;
import com.shuzhi.entity.MessageRevertData;
import com.shuzhi.entity.ResultDataEntity;
import com.shuzhi.entity.SystemInfoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class Utils {

    @Autowired
    ProtocolProperties protocolProperties;

    //十六进制下数字到字符的映射数组
    private final static String[] hexDigits = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};

    private static final String KEY_SHA = "SHA";
    @Value("${gateway.key}")
    private String key;
    /**
     * @Description:封装命令回执message层
     * @Author:
     * @date 2019/6/6
     */
    public MessageRevertData getMessageRevertData(String timestamp,int code,  ResultDataEntity data){
        MessageRevertData mrd = new MessageRevertData();
        mrd.setCode(code);
        mrd.setTimestamp(timestamp);
        mrd.setData(data);
        return mrd;
    }


    /**
     * @Description:获取时间戳
     * @Author: ztt
     * @date 2019/6/10
     */
    public static String  getTimeStamp(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
    }

    /**
     * @Description: sign校验
     * @Author: ztt
     * @date 2019/6/10
     */
    public boolean signVerify(SystemInfoData systemInfoData, JsonNode jsonParentNode){
        String md5Str = encodeByMD5(jsonParentNode.path("msg").toString());

        String shaStr = addPwd(systemInfoData.getMsgid() + key + md5Str + systemInfoData.getMsgts());
        if (systemInfoData.getSign().equals(shaStr)){
            return true;
        }
        return false;
    }
    /**
     * 功能描述 生成sign校验
     * @Author: ztt
     * @date 2019/6/10
     * @params [systemInfoData]
     * @return java.lang.String
     */
    public String getSignVerify(SystemInfoData systemInfoData){
        String md5Str = encodeByMD5((String) systemInfoData.getMsg());
        String shaStr = addPwd(systemInfoData.getMsgid() + key + md5Str + systemInfoData.getMsgts());
        return shaStr;
    }

    private static String addPwd(String inputStr) {
        BigInteger sha = null;
        System.out.println("=======加密前的数据:" + inputStr);
        byte[] inputData = inputStr.getBytes();
        try {
             MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA);
             messageDigest.update(inputData);
             sha = new BigInteger(messageDigest.digest());
            System.out.println("SHA加密后:" + sha.toString(32));
            return sha.toString(32);
         } catch (Exception e) {
             e.printStackTrace();
        }
        return null;
    }

    /**对字符串进行MD5编码*/
    private static String encodeByMD5(String originString){
        if (originString!=null) {
            try {
                //创建具有指定算法名称的信息摘要
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                //使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
                byte[] results = md5.digest(originString.getBytes());
                //将得到的字节数组变成字符串返回
                String result = byteArrayToHexString(results);
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 轮换字节数组为十六进制字符串
     * @param b 字节数组
     * @return 十六进制字符串
     */
    private static String byteArrayToHexString(byte[] b){
        StringBuffer resultSb = new StringBuffer();
        for(int i=0;i<b.length;i++){
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    //将一个字节转化成十六进制形式的字符串
    private static String byteToHexString(byte b){
        int n = b;
        if(n<0)
            n=256+n;
        int d1 = n/16;
        int d2 = n%16;
        return hexDigits[d1] + hexDigits[d2];
    }
    /**
     * 上报请求实体
     *
     * @return
     */
    public SystemInfoData getRequestBody() {
        SystemInfoData infoData = new SystemInfoData();
        infoData.setMsgid(UUID.randomUUID().toString());
        infoData.setMsgtype(4);
        infoData.setSystype(protocolProperties.getSystype());
        infoData.setSysid(protocolProperties.getSysid());
        infoData.setConnectid(protocolProperties.getConnectid());
        return infoData;
    }

}
