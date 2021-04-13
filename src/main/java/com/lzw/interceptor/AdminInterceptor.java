
package com.lzw.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 过滤器
 *
 * servlet规范中的一部分，任何java web工程都可以使用
 *
 * 在url-pattern中配置了/*之后，可以对所有要访问的资源进行拦截
 *
 * 拦截器
 *
 * 拦截器是SpringMVC框架自己的，只有使用了SpringMVC框架的工程才能使用
 *
 * 拦截器只会拦截访问的控制器方法， 如果访问的是jsp/html/css/image/js是不会进行拦截的
 * @author lzw
 * @Description
 * @creat 2020-11-26-13:33
 */
public class AdminInterceptor implements HandlerInterceptor {
    //类似与Filtert中的doFiltert     拦截器的作用域更加广泛，既可以用于WEB，也可以用于Application、Swing中；过滤器值只能用于WEB中
    //这是请求前的操作
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String username = (String) request.getSession().getAttribute("username");

        if(request.getRequestURI().contains("/goods/listByPrice")||request.getRequestURI().contains("/goods/list"))
            return true;//商品显示界面不用登录，更不用admin

        if(
                request.getRequestURI().contains("/WEB-INF/jsp/pages/manager/")
                ||request.getRequestURI().contains("/WEB-INF/jsp/pages/common/manger_menu")
                ||request.getRequestURI().contains("/goods/")
                ||request.getRequestURI().contains("/order/allList")
                ||request.getRequestURI().contains("/order/updateStatus")
        ){
            //单独监听jsp页面不起作用，会通过controller中转过去，所以也需要将controller拦截

//            System.out.println(username=="admin");   //username=="admin"只判断地址

            if("admin".equals(username)) return true;
            else {
                //不是登录和注册页面，请求       都进行跳转到登陆页面
                response.sendRedirect("/ssm/user/tologin");
                return false;
            }
        }
        else {//其他普通页面可以通过
            return true;
        }


    }
    //这是请求后的操作，比如日志等
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
