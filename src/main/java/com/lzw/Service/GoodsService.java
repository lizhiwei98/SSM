package com.lzw.Service;

import com.lzw.Pojo.Goods;
import com.lzw.Pojo.Page;

import java.util.List;
import java.util.Map;

/**
 * @author lzw
 * @Description
 * @creat 2020-11-30-10:25
 */
public interface GoodsService {
    public int insert(Goods goods);
    public int deleteById(Integer id);
    public int update(Goods goods);
    public Goods  selectById(Integer id);

    public int getCount();
    public Page<Goods> getPage(int pageid, int pagesize);
    public Page<Goods> getPageByPrice(Map<String,Integer> map);
}
