package cn.iald.psb.common;

import java.io.Serializable;

public class ConfigEntity implements Serializable {
    private String hid;//酒店hid
    private String url;//请求路径
    private String config;//参数 json格式  用于配置公安上传接口所需的公共参数
    private String createTime;//创建时间
    private String createId;//创建人ID
    private String lastUpdateTime;//最后一次修改时间
    private String lastUpdateId;//最后一次修改人id
    private String hotelCode;//酒店码
    private String hotelCodeEndTime;//酒店码过期时间
    private String policeTypeDic;//酒店psb类型关联dic表
    private String machineId;//自助机机器码

    public String getHid() {
        return hid;
    }

    public ConfigEntity setHid(String hid) {
        this.hid = hid;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ConfigEntity setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getConfig() {
        return config;
    }

    public ConfigEntity setConfig(String config) {
        this.config = config;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public ConfigEntity setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getCreateId() {
        return createId;
    }

    public ConfigEntity setCreateId(String createId) {
        this.createId = createId;
        return this;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public ConfigEntity setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
        return this;
    }

    public String getLastUpdateId() {
        return lastUpdateId;
    }

    public ConfigEntity setLastUpdateId(String lastUpdateId) {
        this.lastUpdateId = lastUpdateId;
        return this;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public ConfigEntity setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
        return this;
    }

    public String getHotelCodeEndTime() {
        return hotelCodeEndTime;
    }

    public ConfigEntity setHotelCodeEndTime(String hotelCodeEndTime) {
        this.hotelCodeEndTime = hotelCodeEndTime;
        return this;
    }

    public String getPoliceTypeDic() {
        return policeTypeDic;
    }

    public ConfigEntity setPoliceTypeDic(String policeTypeDic) {
        this.policeTypeDic = policeTypeDic;
        return this;
    }

    public String getMachineId() {
        return machineId;
    }

    public ConfigEntity setMachineId(String machineId) {
        this.machineId = machineId;
        return this;
    }
}
