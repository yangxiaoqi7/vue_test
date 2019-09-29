package com.yangfan;

import lombok.Data;

import java.util.List;

public class PageBean {
    private Integer nowPage;   //当前页数
    private Integer pageSize;  //每页行数
    private Integer pageNum;   //总页数
    private Integer rowsNum;   //总行数

    private List list;

    private Integer start;
    private Integer end;

    public Integer getNowPage() {
        return nowPage;
    }

    public void setNowPage(Integer nowPage) {
        this.nowPage = nowPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        if(rowsNum%pageSize==0){
            pageNum=rowsNum/pageSize;}
        else{
            pageNum=rowsNum/pageSize+1;}
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getRowsNum() {
        return rowsNum;
    }

    public void setRowsNum(Integer rowsNum) {
        this.rowsNum = rowsNum;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    /*
    计算 end  start
     */
    public void cal(){
        this.start=nowPage-2;
        this.end=nowPage+2;
        this.pageNum=getPageNum();
        if(pageNum>5){
            if(nowPage<=2){
                this.start=1;
                this.end=5;
            }else if(nowPage>=pageNum-2){
                this.start=pageNum-4;
                this.end=pageNum;
            }
        }else{
            this.start=1;
            this.end=pageNum;
        }
    }
}
