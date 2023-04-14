package com.learn.controller;

import com.learn.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class TestParamController {

    /**
     * 获取请求参数的方法：
     * 1、通过ServletAPI获取：
     * 在控制器方法的形参位置设置HttpServletRequest类型的形参，通过HttpServletRequest对象获取请求参数
     */
    @RequestMapping("/param/servletAPI")
    public String getParamByServletAPI(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username:" + username + ",password:" + password);
        return "success";
    }

    /**
     * 2、通过控制器方法的形参获取
     * 定义控制器方法的形参，与请求参数的name属性值相同即可
     * <p>
     * 3、@RequestParam注解：将控制器方法的形参和请求参数进行映射
     * 注解@RequestParam的三个属性：
     * （1）value：设置请求参数名，为形参赋值
     * （2）required：设置请求参数是否必须，默认为true。当required=true时，浏览器的请求中未包含该请求参数时，
     * 报错400：Required String parameter 'xxx' is not present
     * （3）defaultValue：设置请求参数的默认值。
     * <p>
     * 4、@RequestHeader：将控制器方法的形参和请求头信息进行映射
     * 注解@RequestHeader一共有三个属性：value、required、defaultValue，用法同@RequestParam
     * <p>
     * 5、@CookieValue：将控制器方法的形参和cookie数据进行映射
     * 注解@CookieValue注解一共有三个属性：value、required、defaultValue，用法同@RequestParam
     */
    @RequestMapping("/param")
    public String getParam(
            @RequestParam(
                    value = "userName",
                    required = true,
                    defaultValue = "hello"
            ) String username,
            String password,
            @RequestHeader("referer") String referer,
            @CookieValue("JSESSIONID") String jsessionId
    ) {
        System.out.println("username:" + username + ",password:" + password);
        System.out.println("referer:" + referer);
        System.out.println("jsessionId:" + jsessionId);
        return "success";
    }

    /**
     * 6、可以在控制器方法的形参位置设置一个实体类类型的形参，
     * 若浏览器传输的请求的请求参数和实体类的属性一致，
     * 那么请求参数就会为属性赋值
     *
     * @param user
     * @return
     */
    @RequestMapping("/param/pojo")
    public String getParamByPojo(User user) {

        System.out.println(user);
        return "success";
    }
}
