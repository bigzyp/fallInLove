package com.love.fallinlove.domain;

import java.util.Date;

public class CommemorationDay {
    private Long commemorationDayId;

    private Long userId;

    private Integer dayType;

    private Date commemorationTime;

    private String title;

    private String detail;

    private Integer sort;

    private Date gmtCreate;

    private Integer state;

    private Integer repeatTime;

    private Integer homeDisplay;

    public Long getCommemorationDayId() {
        return commemorationDayId;
    }

    public void setCommemorationDayId(Long commemorationDayId) {
        this.commemorationDayId = commemorationDayId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCommemorationTime() {
        return commemorationTime;
    }

    public void setCommemorationTime(Date commemorationTime) {
        this.commemorationTime = commemorationTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Integer getDayType() {
        return dayType;
    }

    public void setDayType(Integer dayType) {
        this.dayType = dayType;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getRepeatTime() {
        return repeatTime;
    }

    public void setRepeatTime(Integer repeatTime) {
        this.repeatTime = repeatTime;
    }

    public Integer getHomeDisplay() {
        return homeDisplay;
    }

    public void setHomeDisplay(Integer homeDisplay) {
        this.homeDisplay = homeDisplay;
    }
}