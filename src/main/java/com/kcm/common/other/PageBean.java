package com.kcm.common.other;

/**
 * @author: lucky
 * @date: 2020/8/5
 * @description: 分页工具类
 **/
public class PageBean {
    /**
     * 当前页面
     */
    private Integer currentPage;
    /**
     * 开始页面
     */
    private Integer startIndex;
    /**
     * 当前页数据最大条数
     */
    private Integer pageSize=5;
    /**
     * 数据条数
     */
    private Integer totalCount;
    /**
     * 总页数
     */
    private Integer totalPage;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
        //计算总页数
        this.totalPage=(int)Math.ceil((this.totalCount*1.0/this.pageSize));
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
