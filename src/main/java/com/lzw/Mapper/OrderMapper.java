package com.lzw.Mapper;

import com.lzw.Pojo.GoodsCarItem;
import com.lzw.Pojo.Order;

import java.util.List;
import java.util.Map;

/**
 * @author lzw
 * @Description
 * @creat 2020-12-01-18:58
 */
public interface OrderMapper {
    public int insert(Order order);
    public int deleteById(String orderid);//删除单个订单
    public int update(Order order);//修改订单
    public Order  selectById(Integer id);//
    public List<Order> getPage(Map<String,Object> map);
    public List<Order> getByname(String usernmae);//获取该用户的所有订单
    public List<Order> getAll();
    public int updateStatus(Map<String,Object> map);//根据订单号改变订单状态
    //public  int deleteAll(String username);//将用户的购物车清空
}
