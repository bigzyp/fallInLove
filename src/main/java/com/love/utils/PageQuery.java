package com.love.utils;

import java.io.Serializable;

/**
 * @Author: lixin
 * @Description:
 * @Date: 2018/7/18 13:52
 */
public class PageQuery implements Serializable {

    private static final long serialVersionUID = -6109041301910469195L;

    /** 每页记录数 */
    private int pageSize = 20;

    /** 当前页 */
    private int currentPage = 1;

    public int getSize() {
        return this.getPageSize();
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
