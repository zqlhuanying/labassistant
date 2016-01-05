package com.labassistant.beans;

/**
 * Created by zql on 2016/1/5.
 */
public class VersionEntity extends ToStringBase{

    private static final long serialVersionUID = -6772219823046242947L;
    private String version;
    private String url;
    private String apk;
    private String desc;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApk() {
        return apk;
    }

    public void setApk(String apk) {
        this.apk = apk;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
