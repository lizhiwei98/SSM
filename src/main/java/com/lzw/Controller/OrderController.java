package com.lzw.Controller;

import com.lzw.Pojo.GoodsCarItem;
import com.lzw.Pojo.Order;
import com.lzw.Pojo.OrderItem;
import com.lzw.Service.GoodsCarItemService;
import com.lzw.Service.OrderItemSerivce;
import com.lzw.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author lzw
 * @Description
 * @creat 2020-12-01-20:27
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private GoodsCarItemService goodsCarItemService;
    @Autowired
    private OrderItemSerivce orderItemSerivce;
    @RequestMapping("/addOrder")
    public String addOrder(HttpSession httpSession,Model model){
        String username = (String) httpSession.getAttribute("username");
        List<GoodsCarItem> car = goodsCarItemService.getByname(username);
        System.out.println("carsize:"+car.size());
        if (car.size()!=0){ //car是list类型，空的时候不为null，应该用长度判断
        Date date = new Date();
        String orderId=date.getTime()+username;
        System.out.println(orderId);
        double totalPrice=0;
        List<OrderItem> list=new ArrayList<OrderItem>();
        for (GoodsCarItem goodsCarItem : car) {
             totalPrice+=goodsCarItem.getTotalPrice();
            OrderItem orderItem = new OrderItem(goodsCarItem.getGoodsid(), orderId, goodsCarItem.getGoodsname(), goodsCarItem.getCount(), goodsCarItem.getPrice(), goodsCarItem.getTotalPrice());
            list.add(orderItem);
        }
        orderService.insert(new Order(null,orderId,username,new java.sql.Date(date.getTime()),totalPrice,0));
       //需要先创建订单，因为有外键依赖
        orderItemSerivce.insertAll(list);

        //创建完订单后，请购物车清空
        goodsCarItemService.deleteAll(username);
        model.addAttribute("orderId",orderId);
            return "cart/checkout";
        }
        else { //购物车为空
            return "cart/cart";
        }

    }
    @RequestMapping("/deleteOrder")
    public String deleteOrder(String orderid){
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("orderid",orderid);
        orderService.deleteById(orderid);
        return "redirect:/order/orderList";
    }

    @RequestMapping("/orderList")
    public String orderList(HttpSession httpSession,Model model){
        String username = (String) httpSession.getAttribute("username");
        //Map<String,Object> map=new HashMap<String,Object>();
        List<Order> orders = orderService.getByname(username);
        model.addAttribute("orders",orders);
        return "order/order";
    }
    @RequestMapping("/allList")
    public String allList(HttpSession httpSession,Model model){
        List<Order> orders = orderService.getAll();
        model.addAttribute("AdminOrders",orders);
        return "manager/order_manager";
    }
    @RequestMapping("/orderDetail")
    public String orderDetail(HttpSession httpSession,Model model,String orderid,double totalPrice){
        String username = (String) httpSession.getAttribute("username");
        //Map<String,Object> map=new HashMap<String,Object>();
        List<OrderItem> details = orderItemSerivce.getByOrderId(orderid);
        Integer totalCount=0;
        for (OrderItem detail : details) {
            totalCount+=detail.getCount();
        }
        model.addAttribute("details",details);
        model.addAttribute("totalCount",totalCount);
        model.addAttribute("totalPrice",totalPrice);
        return "order/order_detail";
    }

    @RequestMapping("/updateStatus")
    public String updateStatus(Integer yes,String orderid){
        Map<String, Object> map = new HashMap<String, Object>();
      //  System.out.println("haskzfhksf"+yes);
        if(yes==1) yes=0;
        else yes=1;
        map.put("yes",yes);
        map.put("orderid",orderid);
        orderService.updateStatus(map);
        return "redirect:/order/allList";
    }

}
