package com.lzw.Service;

import com.lzw.Pojo.OrderItem;

import java.util.List;

/**
 * @author lzw
 * @Description
 * @creat 2020-12-01-20:51
 */
public interface OrderItemSerivce {
    public int insertAll(List<OrderItem> orderItemList);//插入购物车里所有的商品
    public int deleteByid(Integer goodsId);//删除单个商品,订单号名和商品id
    public int updateCountBygid(OrderItem orderItem);//根据商品id修改对应的数量
    public List<OrderItem> getByOrderId(String orderId);//获取某个订单详情
}
