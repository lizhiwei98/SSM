package com.lzw.Mapper;

import com.lzw.Pojo.GoodsCarItem;


import java.util.List;
import java.util.Map;

/**
 * @author lzw
 * @Description
 * @creat 2020-11-30-21:50
 */
public interface GoodsCarItemMapper {
    public int insert(GoodsCarItem goodsCarItem);
    public int deleteByid(Map<String,Object> map);//删除单个商品,用户名和商品id
    public int updateCountBygid(Map<String,Object> map);//根据商品id修改对应的数量
    public GoodsCarItem  selectById(Map<String,Object> map);//判断一个用户的购物车中是否拥有该商品，有责不插入，而是修改
    public List<GoodsCarItem> getPage(Map<String,Object> map);
    public List<GoodsCarItem> getPageByPrice(Map<String,Object> map);
    public List<GoodsCarItem> getByname(String usernmae);//获取该用户的购物车里面所有的数据
    public  int deleteAll(String username);//将用户的购物车清空
}
