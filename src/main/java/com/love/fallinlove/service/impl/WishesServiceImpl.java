package com.love.fallinlove.service.impl;

import com.love.enums.CodeMessageEnum;
import com.love.enums.LoveRuntimeException;
import com.love.fallinlove.dao.WishesDao;
import com.love.fallinlove.dao.UserPairDao;
import com.love.fallinlove.domain.Wishes;
import com.love.fallinlove.dto.WishesDTO;
import com.love.fallinlove.service.WishesService;
import com.love.utils.EntityUtil;
import com.love.utils.LoginUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WishesServiceImpl implements WishesService {

    @Resource
    private WishesDao wishesDao;

    @Resource
    private UserPairDao userPairDao;

    @Resource
    private LoginUser loginUser;


    /**
     * 根据条件查询所有纪念日
     * @param wishesDTO
     * @return
     */
    @Override
    public List<Wishes> listWishes(WishesDTO wishesDTO) {
        return wishesDao.selectWishesBySearch(wishesDTO);
    }

    /**
     * 根据id查询纪念日
     * @param wishesDTO
     * @return
     */
    @Override
    public Wishes getWishesById(WishesDTO wishesDTO) {
        return wishesDao.selectByPrimaryKey(wishesDTO.getWishesId());
    }

    /**
       * @Description: 新增纪念日
       * @params:  [wishesDTO]
       * @Return:  void
       * @Author:  lixin
       * @Date:  2019/11/21 16:11
       * @Modified:
       */
    @Override
    public void saveWishes(WishesDTO wishesDTO) {
        Long userJoinId = userPairDao.selectJoinIdByUserId(wishesDTO.getUserId());
        if (userJoinId == null) {
            throw new LoveRuntimeException(CodeMessageEnum.ACCESS_BARRED);
        }
        wishesDTO.setUserJoinId(userJoinId);
        try {
            EntityUtil.entityInit(wishesDTO);
        } catch (Exception e) {
            throw new LoveRuntimeException(CodeMessageEnum.SERVICE_ERROR);
        }
        wishesDao.insert(wishesDTO);
    }

    /**
       * @Description: 编辑纪念日
       * @params:  [wishesDTO]
       * @Return:  void
       * @Author:  lixin
       * @Date:  2019/11/21 16:52
       * @Modified:
       */
    @Override
    public void updateWishes(WishesDTO wishesDTO) {
        if (wishesDTO.getWishesId() == null) {
            throw new LoveRuntimeException(CodeMessageEnum.VALIDATION_ERROR);
        }
        wishesDao.updateByPrimaryKey(wishesDTO);
    }
}
