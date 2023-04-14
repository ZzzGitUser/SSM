package com.learn.controller;

import com.learn.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 1、@RequestBody：将请求体中的内容，和控制器方法的形参，进行绑定
 * 2、使用@RequestBody注解，将json格式的请求参数，转换为java对象
 * 2.1》导入jackson的依赖
 * 2.2》在SpringMVC的配置文件中设置<mvc:annotation-driven/>
 * 2.3》在处理请求的控制器方法的形参位置，直接设置json格式的请求参数要转换的java类型的形参，
 * 使用@RequestBody注解标识即可
 * <p>
 * 3、@ResponseBody：将所标识的控制器方法的返回值，作为响应报文的响应体，响应到浏览器
 * 4、使用@ResponseBody注解，响应浏览器json格式的数据
 * 4.1》导入jackson的依赖
 * 4.2》在SpringMVC的配置文件中设置<mvc:annotation-driven/>
 * 4.3》将需要转换为json字符串的java对象，直接作为控制器方法的返回值，
 * 使用@ResponseBody注解标识控制器方法，就可以将java对象直接转换为json字符串，并响应到浏览器
 * <p>
 * 常用的java对象转换为json的结果
 * 实体类---》json对象
 * map---》json对象
 * list---》json数组
 */
@Controller
public class TestAjaxController {

    @RequestMapping("/test/ajax")
    public void testAjax(Integer id, @RequestBody String requestBody, HttpServletResponse response) throws IOException {
        System.out.println("requestBody:" + requestBody);
        System.out.println("id:" + id);
        response.getWriter().write("hello,axios");
    }

    //@RequestMapping("/test/requestBody/json")
    public void testRequestBody(@RequestBody Map<String, Object> map, HttpServletResponse response) throws IOException {
        System.out.println(map);
        response.getWriter().write("Hello,responseBody");
    }

    @RequestMapping("/test/requestBody/json")
    public void testRequestBody(@RequestBody User user, HttpServletResponse response) throws IOException {
        System.out.println(user);
        response.getWriter().write("Hello,RequestBody");
    }

    @RequestMapping("/test/responseBody")
    @ResponseBody
    public String testResponseBody() {
        return "success";
    }

    @RequestMapping("/test/responseBody/json")
    @ResponseBody
    public List<User> testResponseBodyJson() {
        User user1 = new User(1001, "admin1", "123456", 20, "男");
        User user2 = new User(1002, "admin2", "123456", 20, "男");
        User user3 = new User(1003, "admin3", "123456", 20, "男");
        List<User> list = Arrays.asList(user1, user2, user3);
        return list;
    }
}
