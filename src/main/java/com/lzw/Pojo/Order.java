package com.lzw.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * @author lzw
 * @Description
 * @creat 2020-11-09-9:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    Integer id;
    String orderid;
    String username;
    Date date;
    Double price;
    int yes;
}
