package com.love.utils;

import com.love.fallinlove.dao.UserDao;
import com.love.fallinlove.domain.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: lixin
 * @Description:
 * @Date: 2019/11/21 14:24
 */
@Component
public class LoginUser {

    @Resource
    private UserDao userDao;

    /**
       * @Description: 根据id查询用户信息
       * @params:  [userId]
       * @Return:  com.love.fallinlove.domain.User
       * @Author:  lixin
       * @Date:  2019/11/21 14:26
       * @Modified:
       */
    public User getLoginUser (Long userId) {
        User user = userDao.selectByPrimaryKey(userId);
        return user;
    }
}
