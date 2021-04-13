package com.lzw.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lzw
 * @Description
 * @creat 2020-12-01-19:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private Integer goodsid;
    private String orderid;
    private String goodsname;
    private Integer count;
    private Double price;
    private Double totalPrice;
}
