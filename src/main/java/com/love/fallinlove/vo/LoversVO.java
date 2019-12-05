package com.love.fallinlove.vo;

import com.love.fallinlove.domain.User;

/**
 * @Author: lixin
 * @Description:
 * @Date: 2019/11/22 16:32
 */
public class LoversVO {

    private User user;

    private User joinUser;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getJoinUser() {
        return joinUser;
    }

    public void setJoinUser(User joinUser) {
        this.joinUser = joinUser;
    }
}
