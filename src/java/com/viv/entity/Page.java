package com.viv.entity;

/**
 * Created by viv on 16-4-29.
 */
public class Page {
    /*当前页索引*/
    private int pageIndex;
    /*当前页业务记录数*/
    private int pageSize;
    /*从第几条业务开始*/
    private int pageStart;
    /*排序字段*/
    private String orderFieldStr;
    /*排序方向*/
    private String orderDirectionStr;

    public Page(){

    }

    public Page(String orderDirectionStr, String orderFieldStr, int pageIndex, int pageSize) {
        this.orderDirectionStr = orderDirectionStr;
        this.orderFieldStr = orderFieldStr;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.pageStart = pageIndex * pageSize;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getOrderFieldStr() {
        return orderFieldStr;
    }

    public void setOrderFieldStr(String orderFieldStr) {
        this.orderFieldStr = orderFieldStr;
    }

    public String getOrderDirectionStr() {
        return orderDirectionStr;
    }

    public void setOrderDirectionStr(String orderDirectionStr) {
        this.orderDirectionStr = orderDirectionStr;
    }
}
