package com.lzw.Pojo;

import java.util.List;

/**
 * @author lzw
 * @Description
 * @creat 2020-11-05-16:43
 */
public class Page<T> {
    private  static final Integer PAGEID=1;
    private  static final Integer PAGESIZE=4;
    private Integer pageid=PAGEID;//当前页码
    private Integer pagesize=PAGESIZE; //页面大小
    private Integer pagetotal; //总页码
    private Integer jilutotal; //总记录数
    private List<T> goods;      //页面数据
    private String url;//servlet地址
    private String path; //记录当前项目地址，不包括后面的 controller

    public Page() {
    }

    public Page(Integer pageid, Integer pagesize, Integer pagetotal, Integer jilutotal, List<T> goods, String url, String path) {
        this.pageid = pageid;
        this.pagesize = pagesize;
        this.pagetotal = pagetotal;
        this.jilutotal = jilutotal;
        this.goods = goods;
        this.url = url;
        this.path = path;
    }

    public Integer getPageid() {
        return pageid;
    }

    public void setPageid(Integer pageid) {
        if(pageid<1) pageid=1;
        if(pageid>pagetotal) pageid=pagetotal;   //将setPagetotal函数的调用放在前面
        this.pageid = pageid;
    }

    public static Integer getPAGEID() {
        return PAGEID;
    }

    public static Integer getPAGESIZE() {
        return PAGESIZE;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pasesize) {
        this.pagesize = pasesize;
    }

    public Integer getPagetotal() {
        return pagetotal;
    }

    public void setPagetotal(Integer pagetotal) {
        this.pagetotal = pagetotal;
    }

    public Integer getJilutotal() {
        return jilutotal;
    }

    public void setJilutotal(Integer jilutotal) {
        this.jilutotal = jilutotal;
    }

    public List<T> getGoods() {
        return goods;
    }

    public void setGoods(List<T> goods) {
        this.goods = goods;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageid=" + pageid +
                ", pagesize=" + pagesize +
                ", pagetotal=" + pagetotal +
                ", jilutotal=" + jilutotal +
                ", goods=" + goods +
                ", url='" + url + '\'' +
                '}';
    }
}
