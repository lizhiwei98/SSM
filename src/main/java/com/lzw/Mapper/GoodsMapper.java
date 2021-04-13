package com.lzw.Mapper;

import com.lzw.Pojo.Goods;
import com.lzw.Pojo.user;

import java.util.List;
import java.util.Map;

/**
 * @author lzw
 * @Description
 * @creat 2020-11-30-10:24
 */
public interface GoodsMapper {
    public int insert(Goods goods);
    public int deleteById(Integer id);
    public int update(Goods goods);
    public Goods  selectById(Integer id);
    public List<Goods> getPage(Map<String,Integer> map);
    public List<Goods> getPageByPrice(Map<String,Integer> map);
    public int getCount();
}
