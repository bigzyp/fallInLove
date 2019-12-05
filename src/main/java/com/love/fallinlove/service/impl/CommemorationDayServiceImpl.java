package com.love.fallinlove.service.impl;

import com.love.enums.CodeMessageEnum;
import com.love.enums.LoveRuntimeException;
import com.love.fallinlove.dao.CommemorationDayDao;
import com.love.fallinlove.dao.UserPairDao;
import com.love.fallinlove.domain.CommemorationDay;
import com.love.fallinlove.dto.CommemorationDayDTO;
import com.love.fallinlove.service.CommemorationDayService;
import com.love.utils.EntityUtil;
import com.love.utils.LoginUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommemorationDayServiceImpl implements CommemorationDayService {

    @Resource
    private CommemorationDayDao commemorationDayDao;

    @Resource
    private UserPairDao userPairDao;

    @Resource
    private LoginUser loginUser;


    /**
     * 根据条件查询所有纪念日
     * @param commemorationDayDTO
     * @return
     */
    @Override
    public List<CommemorationDay> listCommemorationDay(CommemorationDayDTO commemorationDayDTO) {
        return commemorationDayDao.selectCommemorationDayBySearch(commemorationDayDTO);
    }

    /**
     * 根据id查询纪念日
     * @param commemorationDayDTO
     * @return
     */
    @Override
    public CommemorationDay getCommemorationDayById(CommemorationDayDTO commemorationDayDTO) {
        return commemorationDayDao.selectByPrimaryKey(commemorationDayDTO.getCommemorationDayId());
    }

    /**
       * @Description: 新增纪念日
       * @params:  [commemorationDayDTO]
       * @Return:  void
       * @Author:  lixin
       * @Date:  2019/11/21 16:11
       * @Modified:
       */
    @Override
    public void saveCommemorationDay(CommemorationDayDTO commemorationDayDTO) {
        Long userJoinId = userPairDao.selectJoinIdByUserId(commemorationDayDTO.getUserId());
        if (userJoinId == null) {
            throw new LoveRuntimeException(CodeMessageEnum.ACCESS_BARRED);
        }
        commemorationDayDTO.setUserJoinId(userJoinId);
        try {
            EntityUtil.entityInit(commemorationDayDTO);
        } catch (Exception e) {
            throw new LoveRuntimeException(CodeMessageEnum.SERVICE_ERROR);
        }
        commemorationDayDao.insert(commemorationDayDTO);
    }

    /**
       * @Description: 编辑纪念日
       * @params:  [commemorationDayDTO]
       * @Return:  void
       * @Author:  lixin
       * @Date:  2019/11/21 16:52
       * @Modified:
       */
    @Override
    public void updateCommemorationDay(CommemorationDayDTO commemorationDayDTO) {
        if (commemorationDayDTO.getCommemorationDayId() == null) {
            throw new LoveRuntimeException(CodeMessageEnum.VALIDATION_ERROR);
        }
        commemorationDayDao.updateByPrimaryKey(commemorationDayDTO);
    }
}
