package com.shuzhi.entity;

/**
 * 功能描述 消息回执二层数据
 *
 * @author YHF
 * @date 2019/6/6
 * @params
 * @return
 */
public class MessageRevertData {
    //时间戳
    private String timestamp;
    //命令码
    private int code;
    //数据内容
    private ResultDataEntity data;

    public MessageRevertData() {
    }

    public MessageRevertData(String timestamp, int code, ResultDataEntity data) {
        this.timestamp = timestamp;
        this.code = code;
        this.data = data;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ResultDataEntity getData() {
        return data;
    }

    public void setData(ResultDataEntity data) {
        this.data = data;
    }

//    @Override
//    public String toString() {
//        final StringBuilder sb = new StringBuilder("{");
//        sb.append("\"timestamp\":\"")
//                .append(timestamp).append('\"');
//        sb.append(",\"code\":")
//                .append(code);
//        // @TODO 是否需要创建实体类
//        sb.append(",\"data\":{\"result\":")
//                .append(data);
//        sb.append("}}");
//        return sb.toString();
//    }
}
