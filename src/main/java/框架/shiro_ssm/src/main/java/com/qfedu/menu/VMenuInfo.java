package com.qfedu.menu;

import java.util.List;

/**
 * @Author: 轩轩
 * @Date: 2020/4/5 22:44
 * @description: vo类别
 */
public class VMenuInfo {
    /**
     * 权限id（父id）数据库主键
     */
    private Integer pid;
    /**
     * 父菜单名称
     */
    private String pname;
    private String url;
    /**
     * 子菜单
     */
    private List<VMenuInfo> cmenuList;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<VMenuInfo> getCmenuList() {
        return cmenuList;
    }

    public void setCmenuList(List<VMenuInfo> cmenuList) {
        this.cmenuList = cmenuList;
    }

    @Override
    public String toString() {
        return "VMenuInfo{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", url='" + url + '\'' +
                ", cmenuList=" + cmenuList +
                '}';
    }
}
