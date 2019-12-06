package com.love.fallinlove.dto;

import com.love.utils.PageQuery;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CommemorationDayDTO extends PageQuery {
    private Long commemorationDayId;

    private Long userId;

    private Long userJoinId;

    private Integer dayType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date commemorationTime;

    private String title;

    private String detail;

    private Integer sort;

    private Date gmtCreate;

    private Integer state;

    private Integer homeDisplay;

    private Integer repeatTime;

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

    public Long getUserJoinId() {
        return userJoinId;
    }

    public void setUserJoinId(Long userJoinId) {
        this.userJoinId = userJoinId;
    }

    public Integer getDayType() {
        return dayType;
    }

    public void setDayType(Integer dayType) {
        this.dayType = dayType;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getHomeDisplay() {
        return homeDisplay;
    }

    public void setHomeDisplay(Integer homeDisplay) {
        this.homeDisplay = homeDisplay;
    }

    public Integer getRepeatTime() {
        return repeatTime;
    }

    public void setRepeatTime(Integer repeatTime) {
        this.repeatTime = repeatTime;
    }
}