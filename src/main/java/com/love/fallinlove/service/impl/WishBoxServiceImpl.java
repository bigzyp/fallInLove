package com.love.fallinlove.service.impl;

import com.love.enums.CodeMessageEnum;
import com.love.enums.LoveRuntimeException;
import com.love.fallinlove.dao.UserPairDao;
import com.love.fallinlove.dao.WishBoxDao;
import com.love.fallinlove.domain.WishBox;
import com.love.fallinlove.dto.WishBoxDTO;
import com.love.fallinlove.service.WishBoxService;
import com.love.fallinlove.vo.WishBoxVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: lixin
 * @Description:
 * @Date: 2019/11/23 14:06
 */
@Service
public class WishBoxServiceImpl implements WishBoxService {

    @Resource
    private WishBoxDao wishBoxDao;

    @Resource
    private UserPairDao userPairDao;


    /**
     * @param wishBoxDTO
     * @Description: 新增心愿
     * @params: [wishBoxDTO]
     * @Return: void
     * @Author: lixin
     * @Date: 2019/11/27 14:28
     * @Modified:
     */
    @Override
    public void saveWishBox(WishBoxDTO wishBoxDTO) {
        wishBoxDTO.setGmtCrate(new Date());
        wishBoxDTO.setState(1);
        wishBoxDTO.setWishSuccess(0);
        wishBoxDao.insert(wishBoxDTO);
    }

    /**
     * @param wishBoxDTO
     * @Description:
     * @params: [wishBoxDTO]
     * @Return: void
     * @Author: lixin
     * @Date: 2019/11/27 14:32
     * @Modified:
     */
    @Override
    public void updateWishBox(WishBoxDTO wishBoxDTO) {
        WishBox wishBox = new WishBox();
        wishBox.setDetail(wishBoxDTO.getDetail());
        wishBox.setState(wishBoxDTO.getState());
        wishBox.setWishAddress(wishBoxDTO.getWishAddress());
        wishBox.setWishBoxId(wishBoxDTO.getWishBoxId());
        wishBox.setWishName(wishBoxDTO.getWishName());
        wishBox.setWishPic(wishBoxDTO.getWishPic());
        wishBox.setWishSuccess(wishBoxDTO.getWishSuccess());
        wishBox.setWishTime(wishBoxDTO.getWishTime());
        wishBoxDao.updateByPrimaryKey(wishBox);
    }

    /**
     * @param wishBoxDTO
     * @Description: 心愿查询
     * @params: [wishBoxDTO]
     * @Return: void
     * @Author: lixin
     * @Date: 2019/11/27 14:41
     * @Modified:
     */
    @Override
    public WishBoxVO listWishBox(WishBoxDTO wishBoxDTO) {
        Long userJoinId = userPairDao.selectJoinIdByUserId(wishBoxDTO.getUserId());
        if (userJoinId == null) {
            throw new LoveRuntimeException(CodeMessageEnum.ACCESS_BARRED);
        }
        wishBoxDTO.setUserJoinId(userJoinId);
        WishBoxVO wishBoxVO = new WishBoxVO();
        //查询总心愿数
        long total = wishBoxDao.selectCountWishBox(wishBoxDTO);
        wishBoxVO.setWishTotalNum(total);
        if (total == 0L) {
            wishBoxVO.setWishSuccessNum(0L);
            return wishBoxVO;
        }
        //心愿列表
        List<WishBox> wishBoxList = wishBoxDao.selectWishBox(wishBoxDTO);
        wishBoxVO.setWishBoxList(wishBoxList);

        //已完成心愿
        long successNum = wishBoxDao.selectCountWishBoxBySuccess(wishBoxDTO);
        wishBoxVO.setWishSuccessNum(successNum);
        return wishBoxVO;

    }
}
