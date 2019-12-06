package com.love.fallinlove.dto;

import com.love.utils.PageQuery;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class WishesDTO extends PageQuery {
    private Long wishId;

    private Long userId;

    private Long userJoinId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date wishTime;

    private String title;

    private String detail;

    private Date gmtCreate;

    private Integer state;

    private Integer homeDisplay;

    private String wishAddress;

    private String wishPic;

    private Integer wishSuccess;

    public Long getWishesId() {
        return wishId;
    }

    public void setWishesId(Long wishId) {
        this.wishId = wishId;
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

    public Date getWishTime() {
        return wishTime;
    }

    public void setWishTime(Date wishTime) {
        this.wishTime = wishTime;
    }

    public String getWishAddress() {
        return wishAddress;
    }

    public void setWishAddress(String wishAddress) {
        this.wishAddress = wishAddress;
    }

    public String getWishPic() {
        return wishPic;
    }

    public void setWishPic(String wishPic) {
        this.wishPic = wishPic;
    }

    public Integer getWishSuccess() {
        return wishSuccess;
    }

    public void setWishSuccess(Integer wishSuccess) {
        this.wishSuccess = wishSuccess;
    }
}