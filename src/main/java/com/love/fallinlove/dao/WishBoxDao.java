package com.love.fallinlove.dao;

import com.love.fallinlove.domain.WishBox;
import com.love.fallinlove.dto.WishBoxDTO;

import java.util.List;

public interface WishBoxDao {
    int deleteByPrimaryKey(Long wishBoxId);

    int insert(WishBoxDTO record);

    WishBox selectByPrimaryKey(Long wishBoxId);

    List<WishBox> selectAll();

    int updateByPrimaryKey(WishBox record);

    /**
       * @Description: 心愿查询
       * @params:  [wishBoxDTO]
       * @Return:  java.util.List<com.love.fallinlove.domain.WishBox>
       * @Author:  lixin
       * @Date:  2019/11/27 14:56
       * @Modified:
       */
    List<WishBox> selectWishBox(WishBoxDTO wishBoxDTO);

    /**
       * @Description: 查询心愿总数
       * @params:  [wishBoxDTO]
       * @Return:  int
       * @Author:  lixin
       * @Date:  2019/11/27 15:00
       * @Modified:
       */
    long selectCountWishBox(WishBoxDTO wishBoxDTO);

    /**
       * @Description: 查询已完成心愿数
       * @params:  [wishBoxDTO]
       * @Return:  int
       * @Author:  lixin
       * @Date:  2019/11/27 15:08
       * @Modified:
       */
    long selectCountWishBoxBySuccess(WishBoxDTO wishBoxDTO);
}