package com.love.fallinlove.vo;

import com.love.fallinlove.domain.WishBox;

import java.util.List;

/**
 * @Author: lixin
 * @Description:
 * @Date: 2019/11/27 14:42
 */
public class WishBoxVO {

    private List<WishBox> wishBoxList;

    private Long wishSuccessNum;

    private Long wishTotalNum;

    public List<WishBox> getWishBoxList() {
        return wishBoxList;
    }

    public void setWishBoxList(List<WishBox> wishBoxList) {
        this.wishBoxList = wishBoxList;
    }

    public Long getWishSuccessNum() {
        return wishSuccessNum;
    }

    public void setWishSuccessNum(Long wishSuccessNum) {
        this.wishSuccessNum = wishSuccessNum;
    }

    public Long getWishTotalNum() {
        return wishTotalNum;
    }

    public void setWishTotalNum(Long wishTotalNum) {
        this.wishTotalNum = wishTotalNum;
    }
}
