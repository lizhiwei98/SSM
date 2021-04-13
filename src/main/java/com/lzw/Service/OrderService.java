package com.lzw.Service;

import com.lzw.Pojo.GoodsCarItem;
import com.lzw.Pojo.Order;

import java.util.List;
import java.util.Map;

/**
 * @author lzw
 * @Description
 * @creat 2020-12-01-19:32
 */

public interface OrderService {
    public int insert(Order order);
    public int deleteById(String orderid);
    public int update(Order order);//根据商品id修改对应的数量
    public Order  selectById(Integer id);//判断一个用户的购物车中是否拥有该商品，有责不插入，而是修改
    public List<Order> getPage(Map<String,Object> map);
    public List<Order> getPageByPrice(Map<String,Object> map);
    public List<Order> getByname(String usernmae);
    public List<Order> getAll();
    public int updateStatus(Map<String,Object> map);//根据订单号改变订单状态
   // public  int deleteAll(String username);//将用户的购物车清空
}
