package com.love.fallinlove.domain;

import java.util.Date;

public class WishBox {
    private Long wishBoxId;

    private Long userId;

    private String wishName;

    private String detail;

    private Date wishTime;

    private String wishAddress;

    private String wishPic;

    private Integer wishSuccess;

    private Date gmtCrate;

    private Integer state;

    private Integer homeDisplay;

    public Long getWishBoxId() {
        return wishBoxId;
    }

    public void setWishBoxId(Long wishBoxId) {
        this.wishBoxId = wishBoxId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getWishName() {
        return wishName;
    }

    public void setWishName(String wishName) {
        this.wishName = wishName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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

    public Date getGmtCrate() {
        return gmtCrate;
    }

    public void setGmtCrate(Date gmtCrate) {
        this.gmtCrate = gmtCrate;
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
}