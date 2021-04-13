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
 *
 *
 * 如果项目名称为test,你在浏览器中输入请求路径：http://localhost:8080/test/pc/list.jsp
 *
 * 执行下面向行代码后打印出如下结果：
 *
 * 1、 System.out.println(request.getContextPath());
 *
 * 打印结果：/test
 *    2、System.out.println(request.getServletPath());
 *
 * 打印结果：/pc/list.jsp
 * 3、 System.out.println(request.getRequestURI());
 *
 * 打印结果：/test/pc/list.jsp
 * 4、 System.out.println(request.getRealPath("/"));
 *
 * 打印结果：F:\Tomcat 6.0\webapps\test\test
 * @author lzw
 * @Description
 * @creat 2020-11-26-13:33
 */
public class Myinterceptor implements HandlerInterceptor {
    //类似与Filtert中的doFiltert     拦截器的作用域更加广泛，既可以用于WEB，也可以用于Application、Swing中；过滤器值只能用于WEB中
    //这是请求前的操作
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(request.getRequestURI().contains("login")) return true;//要去登陆页面放行

        if(request.getRequestURI().contains("regist")) return true;//要去注册页面放行

        //if(request.getRequestURI().contains("/goods/list"))  return true;//进入首页
        if(request.getRequestURI().contains("/goods/list"))  return true;//跳转到商品显示界面
        if(request.getRequestURI().contains("/goods/listByPrice"))  return true;

        if(request.getRequestURI().contains("static")) return true;  //不过滤静态资源

        String username = (String) request.getSession().getAttribute("username");

        //System.out.println(username!=null);

        if(username!=null) return true;//放行


        //不是登录和注册页面，请求       都进行跳转到登陆页面
        response.sendRedirect(request.getContextPath()+"    /user/tologin");;
        return false;
    }
    //这是请求后的操作，比如日志等
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
