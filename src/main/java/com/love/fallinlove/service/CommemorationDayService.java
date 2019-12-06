package com.love.fallinlove.service;

import com.love.fallinlove.domain.CommemorationDay;
import com.love.fallinlove.dto.CommemorationDayDTO;

import java.util.List;

public interface CommemorationDayService {

    /**
     * 根据条件查询所有纪念日
     * @param commemorationDayDTO
     * @return
     */
    List<CommemorationDay> listCommemorationDay(CommemorationDayDTO commemorationDayDTO);


    /**
     * 根据id查询纪念日
     * @param commemorationDayDTO
     * @return
     */
    CommemorationDay getCommemorationDayById(CommemorationDayDTO commemorationDayDTO);

    /**
     * 新增纪念日
     * @param commemorationDayDTO
     * @return
     */
    void saveCommemorationDay(CommemorationDayDTO commemorationDayDTO);

    /**
     * 编辑纪念日
     * @param commemorationDayDTO
     */
    void updateCommemorationDay(CommemorationDayDTO commemorationDayDTO);
}
