
package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Platform implements Serializable {

    @JsonProperty("app_id")
    public Integer appId;

    @JsonProperty("app_source")
    public String appSource;

    @JsonProperty("app_version")
    public String appVersion;

    @JsonProperty("device_id")
    public String deviceId;

    @JsonProperty("hard_disk_id")
    public String hardDiskId;

    @JsonProperty("mac_address")
    public String macAddress;

    public String os;

    @JsonProperty("os_version")
    public String osVersion;

    public Integer getAppId() {
        return appId;
    }

    public Platform setAppId(Integer appId) {
        this.appId = appId;
        return this;
    }

    public String getAppSource() {
        return appSource;
    }

    public Platform setAppSource(String appSource) {
        this.appSource = appSource;
        return this;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public Platform setAppVersion(String appVersion) {
        this.appVersion = appVersion;
        return this;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public Platform setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public String getHardDiskId() {
        return hardDiskId;
    }

    public Platform setHardDiskId(String hardDiskId) {
        this.hardDiskId = hardDiskId;
        return this;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public Platform setMacAddress(String macAddress) {
        this.macAddress = macAddress;
        return this;
    }

    public String getOs() {
        return os;
    }

    public Platform setOs(String os) {
        this.os = os;
        return this;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public Platform setOsVersion(String osVersion) {
        this.osVersion = osVersion;
        return this;
    }
}
