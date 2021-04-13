package com.lzw.Service;

import com.lzw.Mapper.OrderMapper;
import com.lzw.Pojo.GoodsCarItem;
import com.lzw.Pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author lzw
 * @Description
 * @creat 2020-12-01-19:32
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public int insert(Order order) {
        return orderMapper.insert(order);
    }

    @Override
    public int deleteById(String orderid) {
        return orderMapper.deleteById(orderid);
    }

    @Override
    public int update(Order order) {
        return orderMapper.update(order);
    }

    @Override
    public Order selectById(Integer id) {
        return orderMapper.selectById(id);
    }

    @Override
    public List<Order> getPage(Map<String, Object> map) {
        return orderMapper.getPage(map);
    }

    @Override
    public List<Order> getPageByPrice(Map<String, Object> map) {
        return null;
    }

    @Override
    public List<Order> getByname(String usernmae) {
        return orderMapper.getByname(usernmae);
    }

    @Override
    public List<Order> getAll() {
        return orderMapper.getAll();
    }

    @Override
    public int updateStatus(Map<String, Object> map) {
        return orderMapper.updateStatus(map);
    }

//    @Override
//    public int deleteAll(String username) {
//        return 0;
//    }
}
