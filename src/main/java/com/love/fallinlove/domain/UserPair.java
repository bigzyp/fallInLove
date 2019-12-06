package com.love.fallinlove.domain;

import java.util.Date;

public class UserPair {
    private Long userPairId;

    private Long userId;

    private Long userJoinId;

    private Date gmtCreate;

    private Integer state;

    public Long getUserPairId() {
        return userPairId;
    }

    public void setUserPairId(Long userPairId) {
        this.userPairId = userPairId;
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
}