package com.lzw.Controller;

import com.lzw.Pojo.Goods;
import com.lzw.Pojo.Page;
import com.lzw.Service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lzw
 * @Description
 * @creat 2020-11-30-10:41
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/list")
    public String allLsit(HttpServletRequest request, Integer pageid, Integer pagesize, Model model){

        if(pageid==null)   pageid=1;
        if(pagesize==null)   pagesize=4; //第一次默认
        Page<Goods> page = goodsService.getPage(pageid, pagesize);
        String path = request.getContextPath();  //返回项目名
       // path=path.substring(0,path.length()-1);
       // String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        page.setPath(path);

        System.out.println(path);
        page.setUrl(path+"/goods/list?");
        model.addAttribute("page",page);//作用就类似于hashmap。
        return "client/index";
    }
    @RequestMapping("/listByPrice")
    public String listByPrice(HttpServletRequest request,Integer pageid, Integer pagesize, Integer min,Integer max,Model model){

        if(pageid==null)   pageid=1;
        if(pagesize==null)   pagesize=4; //第一次默认
        Map<String, Integer> map = new HashMap<String, Integer>();
        int start=(pageid-1)*pagesize;
        map.put("start",start);
        map.put("pageid",pageid);
        map.put("pagesize",pagesize);
        map.put("min",min);
        map.put("max",max);
        Page<Goods> page = goodsService.getPageByPrice(map);
        String path = request.getContextPath();
        page.setUrl(path+"/goods/listByPrice?min="+min+"&max="+max+"&");
        model.addAttribute("page",page);
        return "client/index";
    }

    @RequestMapping("/tomanager")
    public String tomanager(){
        return "manager/manager";
    }
    @RequestMapping("/togoodsManager")
    public String togoodsManager(HttpServletRequest request,Integer pageid, Integer pagesize, Model model){
        if(pageid==null)   pageid=1;
        if(pagesize==null)   pagesize=4; //第一次默认
        Page<Goods> page = goodsService.getPage(pageid, pagesize);
        String path = request.getContextPath();
        page.setUrl(path+"/goods/togoodsManager?");
        model.addAttribute("page",page);
        return "manager/goods_manager";
    }

    @RequestMapping("/toadd")
    public String toadd(HttpServletRequest request,Model model,Integer pagesize,Integer pageid,Integer jilutotal){
        model.addAttribute("pageid",pageid);
        model.addAttribute("pagesize",pagesize);
        model.addAttribute("jilutotal",jilutotal);
        return "manager/goods_edit";
    }

    @RequestMapping("/addGoods")
    public String addGoods(HttpServletRequest request,Integer pageid, Integer pagesize,Integer jilutotal,Goods goods,Model model){

        int insert = goodsService.insert(goods);

        if(jilutotal%pagesize==0) pageid++;
        model.addAttribute("pageid",pageid);
        return "redirect:/goods/togoodsManager";   //此处的重定向/包含项目名,并且包含了model中的数据
    }

    @RequestMapping("/deleteGoods")
    public String deleteGoods(HttpServletRequest request,Integer id,Integer pagesize,Integer pageid,Integer jilutotal,Model model){
        goodsService.deleteById(id);

       if(jilutotal%pagesize==1) pageid--;
        model.addAttribute("pageid",pageid);
        return "redirect:/goods/togoodsManager";   //此处的重定向/包含项目名
    }

    @RequestMapping("/toupdate")
    public String toupdate(HttpServletRequest request,Model model,Integer pageid,Integer id){
        Goods goods = goodsService.selectById(id);
        System.out.println(goods);
        System.out.println(pageid);
        model.addAttribute("goods",goods);
        model.addAttribute("pageid",pageid);
        return "manager/goods_edit";
    }
    @RequestMapping("/updateGoods")
    public String updateGoods(HttpServletRequest request,Goods goods,Integer pageid,Model model){
        goodsService.update(goods);
        model.addAttribute("pageid",pageid);
        return "redirect:/goods/togoodsManager";   //此处的重定向/包含项目名且包含model中的返回数据
    }

}
