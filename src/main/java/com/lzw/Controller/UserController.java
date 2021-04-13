package com.lzw.Controller;

import com.google.gson.Gson;
import com.lzw.Pojo.user;
import com.lzw.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lzw
 * @Description
 * @creat 2020-11-29-15:04
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/tologin")
    public String toLogin(){
     //   System.out.println("test");
         return "user/login" ;
    }
    @RequestMapping("/login")
    public String login(user u, HttpSession httpSession){
        user login = userService.login(u);
        if(login!=null){
            httpSession.setAttribute("username",u.getUsername());
            httpSession.setAttribute("password",u.getPassword());
            return "user_success/login_success";
        }
        //重定向 , 不需要视图解析器 , 本质就是重新请求一个新地方嘛 , 所以注意路径问题.

        else  return "redirect:/user/tologin";  //  redirect:/ssm/user/tologin   报错很奇怪。重定向的/包含了项目名
                                                    // 应该是在服务器端，就把/包含形项目名了
      }

    @RequestMapping("/toregist")
    public String toregist(){
        return "user/regist" ;
    }

    @RequestMapping("/regist")
    public String regist(user u){
        Integer regist = userService.regist(u);
        if(regist!=null){
            return "redirect:/user/tologin";
        }
        else return "redirect:/user/toregist";
    }
    @ResponseBody
    @RequestMapping("/existuser")
    public String existuser(String username){
        user existuser = userService.existuser(username);
        Map<String, Object> map = new HashMap<String, Object>();
        Gson gson = new Gson();
        if(existuser!=null){
            map.put("username",true);
            String s = gson.toJson(map);
            return s;
        }
        else {
            map.put("username",false);
            String s = gson.toJson(map);
            return s;
        }
    }
    @RequestMapping("/logout")
    public String logout(HttpSession httpSession){
     httpSession.removeAttribute("username");
     httpSession.removeAttribute("password");
        return "user/login" ;
    }
}
