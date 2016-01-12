package com.labassistant.beans;

/**
 * Created by zhangjun on 16/1/8.
 */
public class ToolsVersionEntity extends ToStringBase {
    private String version;
    private String url;
    private String toolsname;

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

    public String getToolsname() {
        return toolsname;
    }

    public void setToolsname(String toolsname) {
        this.toolsname = toolsname;
    }
}
