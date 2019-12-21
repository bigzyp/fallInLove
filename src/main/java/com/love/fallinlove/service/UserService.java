package com.love.fallinlove.service;

import com.love.fallinlove.domain.User;
import com.love.fallinlove.dto.UserDTO;
import com.love.fallinlove.dto.WxUserInfo;
import com.love.fallinlove.vo.LoversVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: lixin
 * @Description:
 * @Date: 2019/11/9 9:52
 */
public interface UserService {

    /**
       * @Description: 微信小程序登录
       * @params:  [info, request, response]
       * @Return:  com.love.fallinlove.domain.User
       * @Author:  lixin
       * @Date:  2019/11/9 10:38
       * @Modified:
       */
    User loginAppWechat(WxUserInfo info, HttpServletRequest request, HttpServletResponse response);

    /**
       * @Description: 微信小程序推荐
       * @params:  [info, request, response]
       * @Return:  com.love.fallinlove.domain.User
       * @Author:  lixin
       * @Date:  2019/11/22 14:45
       * @Modified:
       */
    LoversVO wxAppReferee(WxUserInfo info, HttpServletRequest request, HttpServletResponse response);

    /**
       * @Description: 微信小程序登录
       * @params:  [info, request, response]
       * @Return:  com.love.fallinlove.domain.User
       * @Author:  lixin
       * @Date:  2019/11/22 16:33
       * @Modified:
       */
    LoversVO wxAppLogin(WxUserInfo info, HttpServletRequest request, HttpServletResponse response);

    /**
       * @Description: 编辑用户信息
       * @params:  [userDTO]
       * @Return:  void
       * @Author:  lixin
       * @Date:  2019/11/23 14:12
       * @Modified:
       */
    void updateUserInfo(UserDTO userDTO);

    /**
       * @Description: 账号登录
       * @params:  [user]
       * @Return:  com.love.fallinlove.vo.LoversVO
       * @Author:  lixin
       * @Date:  2019/12/21 10:19
       * @Modified:
       */
    LoversVO loginIn(User user);
}
