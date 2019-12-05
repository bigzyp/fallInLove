package com.love.fallinlove.controller;

import com.love.enums.CodeMessageEnum;
import com.love.enums.LoveRuntimeException;
import com.love.fallinlove.dto.WishBoxDTO;
import com.love.fallinlove.service.WishBoxService;
import com.love.fallinlove.vo.WishBoxVO;
import com.love.utils.JsonUtils;
import com.love.utils.LogUtil;
import com.love.utils.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Author: lixin
 * @Description:
 * @Date: 2019/11/23 14:57
 */
@RestController
@RequestMapping(value = "/wishBox")
public class WishBoxController {

    @Resource
    private WishBoxService wishBoxService;

    /**
     * @Description:  新增心愿
     * @params:  [commonCommentWithPageDTO]
     * @Return:  java.lang.String
     * @Author:  lixin
     * @Date:  2018/8/8 13:57
     * @Modified:
     */
    @RequestMapping(value = "/saveWishBox", method = RequestMethod.POST)
    public String saveWishBox(@RequestBody WishBoxDTO wishBoxDTO) {
        Result result;
        try {
            wishBoxService.saveWishBox(wishBoxDTO);
        } catch (LoveRuntimeException e) {
            result = new Result(false, e.getErrorCode(), null);
            return JsonUtils.beanToJson(result);
        } catch (Exception e) {
            LogUtil.error(e);
            result = new Result(false, CodeMessageEnum.SERVICE_ERROR,null);
            return JsonUtils.beanToJson(result);
        }
        // 返回成功信息
        result = new Result(true, CodeMessageEnum.REQUEST_SUCCESS, null);
        return JsonUtils.beanToJson(result);
    }

    /**
     * @Description:  修改心愿
     * @params:  [commonCommentWithPageDTO]
     * @Return:  java.lang.String
     * @Author:  lixin
     * @Date:  2018/8/8 13:57
     * @Modified:
     */
    @RequestMapping(value = "/updateWishBox", method = RequestMethod.PUT)
    public String updateWishBox(@RequestBody WishBoxDTO wishBoxDTO) {
        Result result;
        try {
            wishBoxService.updateWishBox(wishBoxDTO);
        } catch (LoveRuntimeException e) {
            result = new Result(false, e.getErrorCode(), null);
            return JsonUtils.beanToJson(result);
        } catch (Exception e) {
            LogUtil.error(e);
            result = new Result(false, CodeMessageEnum.SERVICE_ERROR,null);
            return JsonUtils.beanToJson(result);
        }
        // 返回成功信息
        result = new Result(true, CodeMessageEnum.REQUEST_SUCCESS, null);
        return JsonUtils.beanToJson(result);
    }

    /**
     * @Description:  心愿查询
     * @params:  [commonCommentWithPageDTO]
     * @Return:  java.lang.String
     * @Author:  lixin
     * @Date:  2018/8/8 13:57
     * @Modified:
     */
    @RequestMapping(value = "/listWishBox", method = RequestMethod.GET)
    public String listWishBox(@Valid WishBoxDTO wishBoxDTO) {
        Result result;
        WishBoxVO wishBoxVO;
        try {
            wishBoxVO = wishBoxService.listWishBox(wishBoxDTO);
        } catch (LoveRuntimeException e) {
            result = new Result(false, e.getErrorCode(), null);
            return JsonUtils.beanToJson(result);
        } catch (Exception e) {
            LogUtil.error(e);
            result = new Result(false, CodeMessageEnum.SERVICE_ERROR,null);
            return JsonUtils.beanToJson(result);
        }
        // 返回成功信息
        result = new Result(true, CodeMessageEnum.REQUEST_SUCCESS, wishBoxVO);
        return JsonUtils.beanToJson(result);
    }
}
