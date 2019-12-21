package com.love.fallinlove.controller;

import com.love.enums.CodeMessageEnum;
import com.love.enums.LoveRuntimeException;
import com.love.fallinlove.domain.User;
import com.love.fallinlove.dto.UserDTO;
import com.love.fallinlove.dto.WxUserInfo;
import com.love.fallinlove.service.UserService;
import com.love.fallinlove.vo.LoversVO;
import com.love.utils.JsonUtils;
import com.love.utils.LogUtil;
import com.love.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Author: lixin
 * @Description:
 * @Date: 2019/11/9 9:53
 */
@RestController
public class UserController {

    @Resource
    private UserService userService;


    /**
     * @Description: 微信小程序登录
     * @params: [code, request, response]
     * @Return: java.lang.String
     * @Author: sulijuan
     * @Date: 2018/11/17 11:43
     * @Modified:
     */
    @RequestMapping(value = "/user/wxAppLogin", method = RequestMethod.POST)
    public String wxAppLogin(@RequestBody @Valid WxUserInfo info, HttpServletRequest request, HttpServletResponse response) {
        Result result;
        LoversVO loversVO;
        try {
            loversVO = userService.wxAppLogin(info, request, response);
        } catch (LoveRuntimeException e) {
            result = new Result(e, null);
            return JsonUtils.beanToJson(result);
        } catch (Exception e) {
            LogUtil.error(e, "wxAppLogin Exception error");
            result = new Result(false, CodeMessageEnum.SERVICE_ERROR, null);
            return JsonUtils.beanToJson(result);
        }
        result = new Result(true, CodeMessageEnum.REQUEST_SUCCESS, loversVO);
        return JsonUtils.beanToJson(result);
    }

    /**
     * @Description: 微信小程序邀请登录
     * @params: [code, request, response]
     * @Return: java.lang.String
     * @Author: sulijuan
     * @Date: 2018/11/17 11:43
     * @Modified:
     */
    @RequestMapping(value = "/user/wxAppReferee", method = RequestMethod.POST)
    public String wxAppReferee(@RequestBody WxUserInfo info, HttpServletRequest request, HttpServletResponse response) {
        Result result;
        LoversVO loversVO;
        try {
            loversVO = userService.wxAppReferee(info, request, response);
        } catch (LoveRuntimeException e) {
            result = new Result(e, null);
            return JsonUtils.beanToJson(result);
        } catch (Exception e) {
            LogUtil.error(e, "wxAppLogin Exception error");
            result = new Result(false, CodeMessageEnum.SERVICE_ERROR, null);
            return JsonUtils.beanToJson(result);
        }
        result = new Result(true, CodeMessageEnum.REQUEST_SUCCESS, loversVO);
        return JsonUtils.beanToJson(result);
    }

    /**
     * @Description: 账号登录
     * @params: [code, request, response]
     * @Return: java.lang.String
     * @Author: sulijuan
     * @Date: 2018/11/17 11:43
     * @Modified:
     */
    @RequestMapping(value = "/user/loginIn", method = RequestMethod.POST)
    public String loginIn(@RequestBody User user) {
        Result result;
        LoversVO loversVO;
        try {
            loversVO = userService.loginIn(user);
        } catch (LoveRuntimeException e) {
            result = new Result(e, null);
            return JsonUtils.beanToJson(result);
        } catch (Exception e) {
            LogUtil.error(e, "loginIn Exception error");
            result = new Result(false, CodeMessageEnum.SERVICE_ERROR, null);
            return JsonUtils.beanToJson(result);
        }
        result = new Result(true, CodeMessageEnum.REQUEST_SUCCESS, loversVO);
        return JsonUtils.beanToJson(result);
    }

    /**
     * @Description: 编辑用户信息
     * @params: [code, request, response]
     * @Return: java.lang.String
     * @Author: sulijuan
     * @Date: 2018/11/17 11:43
     * @Modified:
     */
    @RequestMapping(value = "/user/updateUserInfo", method = RequestMethod.PUT)
    public String updateUserInfo(@RequestBody @Valid UserDTO userDTO) {
        Result result;
        try {
            userService.updateUserInfo(userDTO);
        } catch (LoveRuntimeException e) {
            result = new Result(e, null);
            return JsonUtils.beanToJson(result);
        } catch (Exception e) {
            LogUtil.error(e, "wxAppLogin Exception error");
            result = new Result(false, CodeMessageEnum.SERVICE_ERROR, null);
            return JsonUtils.beanToJson(result);
        }
        result = new Result(true, CodeMessageEnum.REQUEST_SUCCESS, null);
        return JsonUtils.beanToJson(result);
    }
}
