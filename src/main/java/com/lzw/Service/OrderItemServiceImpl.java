package com.lzw.Service;

import com.lzw.Mapper.OrderItemMapper;
import com.lzw.Mapper.OrderMapper;
import com.lzw.Pojo.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzw
 * @Description
 * @creat 2020-12-01-20:51
 */
@Service
public class OrderItemServiceImpl implements OrderItemSerivce {
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Override
    public int insertAll(List<OrderItem> orderItemList) {
        return orderItemMapper.insertAll(orderItemList);
    }

    @Override
    public int deleteByid(Integer goodsId) {
        return orderItemMapper.deleteByid(goodsId);
    }

    @Override
    public int updateCountBygid(OrderItem orderItem) {
        return orderItemMapper.updateCountBygid(orderItem);
    }

    @Override
    public List<OrderItem> getByOrderId(String orderId) {
        return orderItemMapper.getByOrderId(orderId);
    }
}
