package com.lzw.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author lzw
 * @Description
 * @creat 2020-11-30-21:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsCarItem {
    private Integer goodsid;
    private String username;
    private String goodsname;
    private Integer count;
    private Double price;
    private Double totalPrice;
}
