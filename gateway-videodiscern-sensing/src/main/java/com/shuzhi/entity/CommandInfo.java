package com.shuzhi.entity;
/**
 * @Description:命令信息
 * @Author: YHF
 * @date 10:46
 */
public class CommandInfo {
    private TDeviceFactoryEntity tdeviceFactoryEntity;
    private TMsgInfoEntity tmsgInfoEntity;

    public CommandInfo() {

    }

    public CommandInfo(TDeviceFactoryEntity tdeviceFactoryEntity) {
        this.tdeviceFactoryEntity = tdeviceFactoryEntity;
    }

    public CommandInfo(TMsgInfoEntity tmsgInfoEntity) {
        this.tmsgInfoEntity = tmsgInfoEntity;
    }

    public CommandInfo(TDeviceFactoryEntity tdeviceFactoryEntity, TMsgInfoEntity tmsgInfoEntity) {
        this.tdeviceFactoryEntity = tdeviceFactoryEntity;
        this.tmsgInfoEntity = tmsgInfoEntity;
    }

    public TDeviceFactoryEntity getTdeviceFactoryEntity() {
        return tdeviceFactoryEntity;
    }

    public TMsgInfoEntity getTmsgInfoEntity() {
        return tmsgInfoEntity;
    }
}
