package com.lzw.Pojo;

/**
 * @author lzw
 * @Description
 * @creat 2020-11-03-18:46
 */
public class Goods {
    private Integer id;
    private String name;
    private String intro;
    private Double price;
    private  int sales;
    private  int stock;
    private String imgPath = "static/img/default.jpg";

    public Goods() {
    }

    public Goods(Integer id, String name, String intro, Double price, int sales, int stock, String img) {
        this.id = id;
        this.name = name;
        this.intro = intro;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
        if(img!=null&& !"".equals(imgPath))
            this.imgPath=img;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        // 要求给定的图书封面图书路径不能为空
        if (imgPath != null && !"".equals(imgPath)) {
            this.imgPath = imgPath;
        }
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", intro='" + intro + '\'' +
                ", price=" + price +
                ", sales=" + sales +
                ", stock=" + stock +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
