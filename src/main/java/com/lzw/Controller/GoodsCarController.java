package com.lzw.Controller;

import com.google.gson.Gson;
import com.lzw.Pojo.Goods;
import com.lzw.Pojo.GoodsCarItem;
import com.lzw.Pojo.Page;
import com.lzw.Service.GoodsCarItemService;
import com.lzw.Service.GoodsCarItemServiceImpl;
import com.lzw.Service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzw
 * @Description
 * @creat 2020-12-01-10:18
 */
@Controller
@RequestMapping("/Car")
public class GoodsCarController {
    @Autowired
    private GoodsCarItemService goodsCarItemService;
    @Autowired
    private GoodsService goodsService;
    @RequestMapping("/carList")
    public String carList(HttpServletRequest request, Integer pageid, Integer pagesize, HttpSession httpSession, Model model){
        String username = (String) httpSession.getAttribute("username");
        if(pageid==null)   pageid=1;
        if(pagesize==null)   pagesize=4; //第一次默认
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username",username);
        map.put("pageid",pageid);
        map.put("pagesize",pagesize);
        Page<GoodsCarItem> page = goodsCarItemService.getPage(map);//获取对应页码的购物车中的商品项
        //page不会为空，先是查找有没有对应页码的购物车项，没有只是也会返回page的goods列表属性为空，page本身不为空
        //例如：Page{pageid=0, pagesize=4, pagetotal=0, jilutotal=0, goods=[], url='null'}
        //总价，总数量
        List<GoodsCarItem> byname = goodsCarItemService.getByname(username);//获取对应用户的所有商品项
        double totalCount=0,totalPrice=0;
        for (GoodsCarItem carItem : byname) {
            totalCount+=carItem.getCount();//每个商品项都有几个商品
            totalPrice+=carItem.getCount()*carItem.getPrice();
        }
        String path = request.getContextPath();
        System.out.println(path);
        path=path.substring(0,path.length()-1);
        page.setPath(path);
        page.setUrl(path+"/Car/carList?");
        if(page!=null)  model.addAttribute("page",page);
        model.addAttribute("totalCount",totalCount);
        model.addAttribute("totalPrice",totalPrice);
        return "cart/cart";
    }

    @RequestMapping("/addItem")
    @ResponseBody
    public String addItem(HttpSession httpSession, Integer goodsId){
        String  username= (String) httpSession.getAttribute("username");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username",username);
        map.put("goodsId",goodsId);
        GoodsCarItem goodsCarItem = goodsCarItemService.selectById(map);//判断购物车中是否有，有责增加数量，无则要插入
        Goods goods = goodsService.selectById(goodsId);
        System.out.println(goodsCarItem);
        if (goodsCarItem!=null){
            Integer count = goodsCarItem.getCount();
            count++;
            map.put("count",count);
            map.put("totalPrice",count*goods.getPrice());
             goodsCarItemService.updateCountBygid(map);
        }
        else{
            goodsCarItemService.insert(new GoodsCarItem(goodsId,username,goods.getName(),1,goods.getPrice(),goods.getPrice()));
        }
        List<GoodsCarItem> byname = goodsCarItemService.getByname(username);//获取对应用户的所有商品项
        double totalCount=0;
        for (GoodsCarItem carItem : byname) {
            totalCount+=carItem.getCount();//每个商品项都有几个商品
        }
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("totalCount",totalCount);
        map1.put("lastName",goods.getName());
        httpSession.setAttribute("lastName",goods.getName());
        Gson gson = new Gson();
        String s = gson.toJson(map1);
        return s;
    }
    @RequestMapping("/updateCount")
    public String updateCount(Integer goodsId,Integer count,HttpSession httpSession){
        String username = (String) httpSession.getAttribute("username");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", username);
        map.put("goodsId", goodsId);
        if(count!=0) {
            map.put("count", count);
            Goods goods = goodsService.selectById(goodsId);
            map.put("totalPrice", count * goods.getPrice());
            goodsCarItemService.updateCountBygid(map);
        }
        else {
            goodsCarItemService.deleteByid(map);
        }
        return "redirect:/Car/carList";
    }
    @RequestMapping("/deleteOne")
    public String deleteOne(Integer goodsId,HttpSession httpSession){
        String  username= (String) httpSession.getAttribute("username");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username",username);
        map.put("goodsId",goodsId);
        goodsCarItemService.deleteByid(map);
        return "redirect:/Car/carList";
    }
    @RequestMapping("/deleteAll")
    public String deleteAll(HttpSession httpSession){
        String  username= (String) httpSession.getAttribute("username");
         goodsCarItemService.deleteAll(username);
        return "redirect:/Car/carList";
    }
}
