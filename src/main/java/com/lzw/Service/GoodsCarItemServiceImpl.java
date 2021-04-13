package com.lzw.Service;

import com.lzw.Mapper.GoodsCarItemMapper;

import com.lzw.Pojo.Goods;
import com.lzw.Pojo.GoodsCarItem;
import com.lzw.Pojo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzw
 * @Description
 * @creat 2020-11-30-21:52
 */
@Service
public class GoodsCarItemServiceImpl implements GoodsCarItemService {
    @Autowired
    private GoodsCarItemMapper goodsCarItemMapper;
    @Override
    public int insert(GoodsCarItem goodsCarItem) {
        return goodsCarItemMapper.insert(goodsCarItem);
    }

    @Override
    public int deleteByid(Map<String,Object> map) {
        return goodsCarItemMapper.deleteByid(map);
    }


    @Override
    public int updateCountBygid(Map<String,Object> map){//根据商品id修改对应的数量
        return goodsCarItemMapper.updateCountBygid(map);
    }

    @Override
    public GoodsCarItem selectById(Map<String,Object> map) {
        return goodsCarItemMapper.selectById(map);
    }

    @Override
    public Page<GoodsCarItem> getPage(Map<String, Object> map) {
       // Map<String,Integer> map=new HashMap<String, Integer>();
        Integer pagesize= (Integer) map.get("pagesize");
        Integer pageid= (Integer) map.get("pageid");
        String username= (String) map.get("username");
        Integer start;
        start=(pageid-1)*pagesize;
        map.put("start",start);
        map.put("pagesize",pagesize);//limit第二个参数代表输出几个
        map.put("username",username);
        List<GoodsCarItem> byname = goodsCarItemMapper.getByname(username);
        Integer count=byname.size();
        System.out.println(count);
        Integer pagecount=count/pagesize;
        List<GoodsCarItem> page = goodsCarItemMapper.getPage(map);

        if(count%pagesize!=0) pagecount++;
        Page<GoodsCarItem> goodsPage = new Page<GoodsCarItem>();
        goodsPage.setPagesize(pagesize);
        goodsPage.setJilutotal(count);
        goodsPage.setPagetotal(pagecount);
        goodsPage.setGoods(page);
        goodsPage.setPageid(pageid);
        return goodsPage;


    }

    @Override
    public Page<GoodsCarItem> getPageByPrice(Map<String, Object> map) {
        return null;
    }


    @Override
    public List<GoodsCarItem> getByname(String usernmae) {
        return goodsCarItemMapper.getByname(usernmae);
    }

    @Override
    public int deleteAll(String username) {
        return goodsCarItemMapper.deleteAll(username);
    }
}
