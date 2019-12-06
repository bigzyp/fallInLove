package com.love.fallinlove.service;

import com.love.fallinlove.domain.Wishes;
import com.love.fallinlove.dto.WishesDTO;

import java.util.List;

public interface WishesService {

    /**
     * 根据条件查询所有纪念日
     * @param wishesDTO
     * @return
     */
    List<Wishes> listWishes(WishesDTO wishesDTO);


    /**
     * 根据id查询纪念日
     * @param wishesDTO
     * @return
     */
    Wishes getWishesById(WishesDTO wishesDTO);

    /**
     * 新增纪念日
     * @param wishesDTO
     * @return
     */
    void saveWishes(WishesDTO wishesDTO);

    /**
     * 编辑纪念日
     * @param wishesDTO
     */
    void updateWishes(WishesDTO wishesDTO);
}
