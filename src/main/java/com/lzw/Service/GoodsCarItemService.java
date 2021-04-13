package com.lzw.Service;

import com.lzw.Pojo.Goods;

import com.lzw.Pojo.GoodsCarItem;
import com.lzw.Pojo.Page;

import java.util.List;
import java.util.Map;

/**
 * @author lzw
 * @Description
 * @creat 2020-11-30-21:52
 */
public interface GoodsCarItemService {
    public int insert(GoodsCarItem goodsCarItem);
    public int deleteByid(Map<String,Object> map);//删除单个商品
    public int updateCountBygid(Map<String,Object> map);//根据商品id修改对应的数量
    public GoodsCarItem selectById(Map<String,Object> map);
    public Page<GoodsCarItem> getPage(Map<String,Object> map);
    public Page<GoodsCarItem> getPageByPrice(Map<String,Object> map);
    public List<GoodsCarItem> getByname(String usernmae);
    public  int deleteAll(String username);
}
