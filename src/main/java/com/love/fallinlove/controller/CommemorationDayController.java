package com.love.fallinlove.controller;


import com.love.enums.CodeMessageEnum;
import com.love.enums.LoveRuntimeException;
import com.love.fallinlove.domain.CommemorationDay;
import com.love.fallinlove.dto.CommemorationDayDTO;
import com.love.fallinlove.service.CommemorationDayService;
import com.love.utils.JsonUtils;
import com.love.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/commemorationDay")
public class CommemorationDayController {

    @Resource
    private CommemorationDayService commemorationDayService;

    /**
     * @Description:  根据条件查询所有纪念日
     * @params:  [commonCommentWithPageDTO]
     * @Return:  java.lang.String
     * @Author:  lixin
     * @Date:  2018/8/8 13:57
     * @Modified:
     */
    @RequestMapping(value = "/listCommemorationDay", method = RequestMethod.GET)
    public String listCommemorationDayAll(CommemorationDayDTO commemorationDayDTO) {
        List<CommemorationDay> list;
        Result result;
        try {
            list = commemorationDayService.listCommemorationDay(commemorationDayDTO);
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
     * @Description:  根据id查询纪念日
     * @params:  [commonCommentWithPageDTO]
     * @Return:  java.lang.String
     * @Author:  lixin
     * @Date:  2018/8/8 13:57
     * @Modified:
     */
    @RequestMapping(value = "/getCommemorationDayById", method = RequestMethod.GET)
    public String getCommemorationDayByIdgetCommemorationDayById(CommemorationDayDTO commemorationDayDTO) {
        CommemorationDay commemorationDay;
        Result result;
        try {
            commemorationDay = commemorationDayService.getCommemorationDayById(commemorationDayDTO);
        } catch (LoveRuntimeException e) {
            result = new Result(false, e.getErrorCode(), null);
            return JsonUtils.beanToJson(result);
        } catch (Exception e) {
            result = new Result(false, CodeMessageEnum.SERVICE_ERROR,null);
            return JsonUtils.beanToJson(result);
        }
        // 返回成功信息
        result = new Result(true, CodeMessageEnum.REQUEST_SUCCESS, commemorationDay);
        return JsonUtils.beanToJson(result);
    }

    /**
     * @Description:  新增纪念日
     * @params:  [commonCommentWithPageDTO]
     * @Return:  java.lang.String
     * @Author:  lixin
     * @Date:  2018/8/8 13:57
     * @Modified:
     */
    @RequestMapping(value = "/saveCommemorationDay", method = RequestMethod.POST)
    public String saveCommemorationDay(@RequestBody CommemorationDayDTO commemorationDayDTO) {
        Result result;
        try {
            commemorationDayService.saveCommemorationDay(commemorationDayDTO);
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
     * @Description:  编辑纪念日
     * @params:  [commonCommentWithPageDTO]
     * @Return:  java.lang.String
     * @Author:  lixin
     * @Date:  2018/8/8 13:57
     * @Modified:
     */
    @RequestMapping(value = "/updateCommemorationDay", method = RequestMethod.PUT)
    public String updateCommemorationDay(@RequestBody CommemorationDayDTO commemorationDayDTO) {
        Result result;
        try {
            commemorationDayService.updateCommemorationDay(commemorationDayDTO);
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
