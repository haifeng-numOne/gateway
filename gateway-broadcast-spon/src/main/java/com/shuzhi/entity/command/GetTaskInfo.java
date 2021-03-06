package com.shuzhi.entity.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shuzhi.commen.Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class GetTaskInfo {
    @JsonProperty("taskid")
    private String taskId;
    private String dir;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

   /* @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("jsondata%5BtaskId%5D=")
                .append(taskId);
        sb.append("&jsondata%5Bdir%5D=")
                .append(dir);
        return sb.toString();
    }*/

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("jsondata%5Bdir%5D=")
                .append(dir);
        sb.append("&jsondata%5BtaskId%5D=")
                .append(Utils.encodeUTF8(taskId));
        return sb.toString();
    }
}
