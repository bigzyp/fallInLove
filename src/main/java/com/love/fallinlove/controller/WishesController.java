package com.love.fallinlove.controller;


import com.love.enums.CodeMessageEnum;
import com.love.enums.LoveRuntimeException;
import com.love.fallinlove.domain.Wishes;
import com.love.fallinlove.dto.WishesDTO;
import com.love.fallinlove.service.WishesService;
import com.love.utils.JsonUtils;
import com.love.utils.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/wishes")
public class WishesController {

    @Resource
    private WishesService WishesService;

    /**
     * @Description:  根据条件查询所有心愿
     * @params:  [commonCommentWithPageDTO]
     * @Return:  java.lang.String
     * @Author:  lixin
     * @Date:  2018/8/8 13:57
     * @Modified:
     */
    @RequestMapping(value = "/listWishes", method = RequestMethod.GET)
    public String listWishesAll(WishesDTO WishesDTO) {
        List<Wishes> list;
        Result result;
        try {
            list = WishesService.listWishes(WishesDTO);
        } catch (LoveRuntimeException e) {
            result = new Result(false, e.getErrorCode(), null);
            return JsonUtils.beanToJson(result);
        } catch (Exception e) {
            result = new Result(false, CodeMessageEnum.SERVICE_ERROR,null);
            return JsonUtils.beanToJson(result);
        }
        // 返回成功信息
        result = new Result(true, CodeMessageEnum.REQUEST_SUCCESS, list);
        return JsonUtils.beanToJson(result);
    }

    /**
     * @Description:  根据id查询心愿
     * @params:  [commonCommentWithPageDTO]
     * @Return:  java.lang.String
     * @Author:  lixin
     * @Date:  2018/8/8 13:57
     * @Modified:
     */
    @RequestMapping(value = "/getWishesById", method = RequestMethod.GET)
    public String getWishesByIdgetWishesById(WishesDTO WishesDTO) {
        Wishes Wishes;
        Result result;
        try {
            Wishes = WishesService.getWishesById(WishesDTO);
        } catch (LoveRuntimeException e) {
            result = new Result(false, e.getErrorCode(), null);
            return JsonUtils.beanToJson(result);
        } catch (Exception e) {
            result = new Result(false, CodeMessageEnum.SERVICE_ERROR,null);
            return JsonUtils.beanToJson(result);
        }
        // 返回成功信息
        result = new Result(true, CodeMessageEnum.REQUEST_SUCCESS, Wishes);
        return JsonUtils.beanToJson(result);
    }

    /**
     * @Description:  新增心愿
     * @params:  [commonCommentWithPageDTO]
     * @Return:  java.lang.String
     * @Author:  lixin
     * @Date:  2018/8/8 13:57
     * @Modified:
     */
    @RequestMapping(value = "/saveWishes", method = RequestMethod.POST)
    public String saveWishes(@RequestBody WishesDTO WishesDTO) {
        Result result;
        try {
            WishesService.saveWishes(WishesDTO);
        } catch (LoveRuntimeException e) {
            result = new Result(false, e.getErrorCode(), null);
            return JsonUtils.beanToJson(result);
        } catch (Exception e) {
            result = new Result(false, CodeMessageEnum.SERVICE_ERROR,null);
            return JsonUtils.beanToJson(result);
        }
        // 返回成功信息
        result = new Result(true, CodeMessageEnum.REQUEST_SUCCESS, null);
        return JsonUtils.beanToJson(result);
    }

    /**
     * @Description:  编辑心愿
     * @params:  [commonCommentWithPageDTO]
     * @Return:  java.lang.String
     * @Author:  lixin
     * @Date:  2018/8/8 13:57
     * @Modified:
     */
    @RequestMapping(value = "/updateWishes", method = RequestMethod.PUT)
    public String updateWishes(@RequestBody WishesDTO WishesDTO) {
        Result result;
        try {
            WishesService.updateWishes(WishesDTO);
        } catch (LoveRuntimeException e) {
            result = new Result(false, e.getErrorCode(), null);
            return JsonUtils.beanToJson(result);
        } catch (Exception e) {
            result = new Result(false, CodeMessageEnum.SERVICE_ERROR,null);
            return JsonUtils.beanToJson(result);
        }
        // 返回成功信息
        result = new Result(true, CodeMessageEnum.REQUEST_SUCCESS, null);
        return JsonUtils.beanToJson(result);
    }
}
