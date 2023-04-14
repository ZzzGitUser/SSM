package com.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 1、@RequestMapping注解的位置
 * 1.1、@RequestMapping标识一个类：设置映射请求的请求路径的基础信息
 * 1.2、@RequestMapping标识一个方法：设置映射请求的请求路径的具体信息
 * <p>
 * 2、@RequestMapping注解的value属性
 * 2.1、value属性通过请求的请求地址匹配请求映射
 * 2.2、value属性是一个字符串类型的数组，表示该请求映射能够匹配多个请求地址所对应的请求
 * 2.3、value属性必须设置，至少通过请求地址匹配请求映射
 * <p>
 * 3、@RequestMapping注解的method属性
 * 3.1、method属性通过请求的请求方式（get或post）匹配请求映射
 * 3.2、method属性是一个RequestMethod类型的数组，表示该请求映射能够匹配多种请求方式的请求
 * 3.3、若当前请求的请求地址满足请求映射的value属性，但是请求方式不满足method属性，
 * *则浏览器报错405：Request method 'POST' not supported
 * <p>
 * 4、@RequestMapping注解的params属性
 * 4.1、params属性通过请求的请求参数匹配请求映射
 * 4.2、params属性是一个字符串类型的数组，可以通过四种表达式设置请求参数和请求映射的匹配关系
 * （1）"param"：要求请求映射所匹配的请求必须携带param请求参数
 * （2）"!param"：要求请求映射所匹配的请求必须不能携带param请求参数
 * （3）"param=value"：要求请求映射所匹配的请求必须携带param请求参数且param=value
 * （4）"param!=value"：要求请求映射所匹配的请求必须携带param请求参数但是param!=value
 * 4.3、若当前请求满足@RequestMapping注解的value和method属性，但是不满足params属性，
 * *此时页面回报错400：Parameter conditions "username" not met for actual request parameters
 * <p>
 * 5、@RequestMapping注解的headers属性
 * 5.1、headers属性通过请求的请求头信息匹配请求映射
 * 5.2、headers属性是一个字符串类型的数组，可以通过四种表达式设置请求头信息和请求映射的匹配关系
 * （1）"header"：要求请求映射所匹配的请求必须携带header请求头信息
 * （2）"!header"：要求请求映射所匹配的请求必须不能携带header请求头信息
 * （3）"header=value"：要求请求映射所匹配的请求必须携带header请求头信息且header=value
 * （4）"header!=value"：要求请求映射所匹配的请求必须携带header请求头信息且header!=value
 * 5.3、若当前请求满足@RequestMapping注解的value和method属性，但是不满足headers属性，此时页面显示404错误，即资源未找到
 * <p>
 * 6、SpringMVC支持ant风格的路径
 * （1）？：表示任意的单个字符
 * （2）*：表示任意的0个或多个字符
 * （3）**：表示任意层数的任意目录，注意：在使用**时，**写在双斜线中，前后不能有任何的其他字符
 * <p>
 * 7、SpringMVC支持路径中的占位符
 * 原始方式：/deleteUser?id=1
 * rest方式：/user/delete/1
 * SpringMVC路径中的占位符常用于RESTful风格中，当请求路径中将某些数据通过路径的方式传输到服
 * 务器中，就可以在相应的@RequestMapping注解的value属性中通过占位符{xxx}表示传输的数据，在
 * 通过@PathVariable注解，将占位符所表示的数据赋值给控制器方法的形参
 */

@Controller
//@RequestMapping("/test")
public class TestRequestMappingController {

    //此时控制器方法所匹配的请求路径为：/test/hello
    @RequestMapping(
            value = {"/hello", "/abc"},
            method = {RequestMethod.POST, RequestMethod.GET},
            //params = {"username"},
            headers = {"referer"}
    )
    public String hello() {
        return "success";
    }

    @RequestMapping("/**/a*")
    public String testAnt() {
        return "success";
    }

    @RequestMapping("/test/rest/{username}/{id}")
    public String testRest(@PathVariable("id") Integer id, @PathVariable("username") String username) {
        System.out.println("");
        System.out.println("id:" + id + ",username:" + username);
        System.out.println();
        return "success";
    }
}
