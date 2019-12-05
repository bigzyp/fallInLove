package com.love.fallinlove.dao;

import com.love.fallinlove.domain.CommemorationDay;
import com.love.fallinlove.dto.CommemorationDayDTO;

import java.util.List;

public interface CommemorationDayDao {
    int deleteByPrimaryKey(Long commemorationDayId);

    int insert(CommemorationDayDTO record);

    CommemorationDay selectByPrimaryKey(Long commemorationDayId);

    int updateByPrimaryKey(CommemorationDayDTO record);

    /**
       * @Description: 根据条件查询纪念日
       * @params:  [commemorationDayDTO]
       * @Return:  java.util.List<com.love.fallinlove.domain.CommemorationDay>
       * @Author:  lixin
       * @Date:  2019/11/21 14:34
       * @Modified:
       */
    List<CommemorationDay> selectCommemorationDayBySearch(CommemorationDayDTO commemorationDayDTO);
}