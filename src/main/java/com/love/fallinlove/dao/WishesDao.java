package com.love.fallinlove.dao;

import com.love.fallinlove.domain.Wishes;
import com.love.fallinlove.dto.WishesDTO;

import java.util.List;

public interface WishesDao {
    int deleteByPrimaryKey(Long wishId);

    int insert(WishesDTO record);

    Wishes selectByPrimaryKey(Long wishId);

    int updateByPrimaryKey(WishesDTO record);

    /**
       * @Description: 根据条件查询心愿
       * @params:  [WishesDTO]
       * @Return:  java.util.List<com.love.fallinlove.domain.Wishes>
       * @Author:  lixin
       * @Date:  2019/11/21 14:34
       * @Modified:
       */
    List<Wishes> selectWishesBySearch(WishesDTO WishesDTO);
}