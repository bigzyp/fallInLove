package com.love.fallinlove.dao;

import com.love.fallinlove.domain.User;
import java.util.List;

public interface UserDao {
    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    User selectByPrimaryKey(Long userId);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    /**
       * @Description: 根据wechat的unionId
       * @params:  [wechat]
       * @Return:  com.love.fallinlove.domain.User
       * @Author:  lixin
       * @Date:  2019/11/9 11:06
       * @Modified:
       */
    User findUserByWechat(String wechat);

    /**
       * @Description: 根据userId查询对象信息
       * @params:  [userId]
       * @Return:  com.love.fallinlove.domain.User
       * @Author:  lixin
       * @Date:  2019/11/22 16:35
       * @Modified:
       */
    User selectJoinUserByUserId(Long userId);

    /**
       * @Description: 账号密码
       * @params:  [user]
       * @Return:  com.love.fallinlove.domain.User
       * @Author:  lixin
       * @Date:  2019/12/21 10:22
       * @Modified:
       */
    User selectUserByLogin(User user);
}