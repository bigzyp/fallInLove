package com.love.fallinlove.dao;

import com.love.fallinlove.domain.UserPair;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserPairDao {
    int deleteByPrimaryKey(Long userPairId);

    int insert(UserPair record);

    UserPair selectByPrimaryKey(Long userPairId);

    List<UserPair> selectAll();

    int updateByPrimaryKey(UserPair record);

    /**
       * @Description: 根据userId查询JoinId
       * @params:  [userId]
       * @Return:  java.lang.Long
       * @Author:  lixin
       * @Date:  2019/11/21 16:16
       * @Modified:
       */
    Long selectJoinIdByUserId(Long userId);

    /**
       * @Description: 查询对方是否已存在
       * @params:  [userId, refereeId]
       * @Return:  int
       * @Author:  lixin
       * @Date:  2019/11/22 16:09
       * @Modified:
       */
    int selectCountByUserId(@Param("userJoinId") Long userJoinId,@Param("userId") Long userId);
}