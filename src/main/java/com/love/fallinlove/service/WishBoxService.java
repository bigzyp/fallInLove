package com.love.fallinlove.service;

import com.love.fallinlove.dto.WishBoxDTO;
import com.love.fallinlove.vo.WishBoxVO;

/**
 * @Author: lixin
 * @Description:
 * @Date: 2019/11/23 14:06
 */
public interface WishBoxService {

    /**
       * @Description: 新增心愿
       * @params:  [wishBoxDTO]
       * @Return:  void
       * @Author:  lixin
       * @Date:  2019/11/27 14:28
       * @Modified:
       */
    void saveWishBox(WishBoxDTO wishBoxDTO);

    /**
       * @Description: 修改心愿
       * @params:  [wishBoxDTO]
       * @Return:  void
       * @Author:  lixin
       * @Date:  2019/11/27 14:32 
       * @Modified:
       */
    void updateWishBox(WishBoxDTO wishBoxDTO);

    /**
       * @Description: 心愿查询
       * @params:  [wishBoxDTO]
       * @Return:  void
       * @Author:  lixin
       * @Date:  2019/11/27 14:41
       * @Modified:
       */
    WishBoxVO listWishBox(WishBoxDTO wishBoxDTO);
}
