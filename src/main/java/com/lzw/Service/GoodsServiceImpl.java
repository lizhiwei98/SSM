package com.lzw.Service;

import com.lzw.Mapper.GoodsMapper;
import com.lzw.Pojo.Goods;
import com.lzw.Pojo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzw
 * @Description
 * @creat 2020-11-30-10:25
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    public int insert(Goods goods) {
        return goodsMapper.insert(goods);
    }

    public int deleteById(Integer id) {
        return goodsMapper.deleteById(id);
    }

    public int update(Goods goods) {
        return goodsMapper.update(goods);
    }

    public Goods selectById(Integer id) {
        return goodsMapper.selectById(id);
    }


    public int getCount() {
        return goodsMapper.getCount();
    }

    public Page<Goods> getPage(int pageid, int pagesize) {
        Map<String,Integer> map=new HashMap<String, Integer>();
        int start;
        start=(pageid-1)*pagesize;
        map.put("start",start);
        map.put("pagesize",pagesize);//limit第二个参数代表输出几个
        Integer count = Math.toIntExact(goodsMapper.getCount());
        Integer pagecount=count/pagesize;
        List<Goods> page = goodsMapper.getPage(map);

        if(count%pagesize!=0) pagecount++;
        Page<Goods> goodsPage = new Page<Goods>();
        goodsPage.setPagesize(pagesize);
        goodsPage.setJilutotal(count);
        goodsPage.setPagetotal(pagecount);
        goodsPage.setGoods(page);
        goodsPage.setPageid(pageid);
        return goodsPage;
    }

    @Override
    public Page<Goods> getPageByPrice(Map<String, Integer> map) {
        int pageid=map.get("pageid"),pagesize=map.get("pagesize");
        Integer count = Math.toIntExact(goodsMapper.getCount());
        Integer pagecount=count/pagesize;
        List<Goods> page = goodsMapper.getPageByPrice(map);

        if(count%pagesize!=0) pagecount++;
        Page<Goods> goodsPage = new Page<Goods>();
        goodsPage.setPagesize(pagesize);
        goodsPage.setJilutotal(count);
        goodsPage.setPagetotal(pagecount);
        goodsPage.setGoods(page);
        goodsPage.setPageid(pageid);
        return goodsPage;
    }
}
