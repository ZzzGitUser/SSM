package com.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 向域对象共享数据：
 * 1、通过ModelAndView向请求域共享数据
 * 使用ModelAndView时，可以使用Model功能向请求域共享数据，使用View功能设置逻辑视图
 * 但控制器方法一定要将ModelAndView作为方法的返回值
 * <p>
 * 2、使用Model向请求域共享数据
 * <p>
 * 3、使用ModelMap向请求域共享数据
 * <p>
 * 4、使用Map向请求域共享数据
 * <p>
 * 5、Model、ModelMap、Map的关系
 * Model、ModelMap、Map类型的参数其实本质上都是 BindingAwareModelMap 类型的
 * public interface Model{}
 * public class ModelMap extends LinkedHashMap<String, Object> {}
 * public class ExtendedModelMap extends ModelMap implements Model {}
 * public class BindingAwareModelMap extends ExtendedModelMap {}
 */
@Controller
public class TestScopeController {

    @RequestMapping("/test/mav")
    public ModelAndView testMAV() {
        /**
         * ModelAndView包含Model和View的功能
         * Model：向请求域共享数据
         * View：设置逻辑视图，实现页面跳转
         */
        ModelAndView modelAndView = new ModelAndView();
        //向请求域中共享数据
        modelAndView.addObject("testRequestScope", "hello,ModelAndView");
        //设置逻辑视图
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @RequestMapping("/test/model")
    public String testModel(Model model) {
        System.out.println(model.getClass().getName());
        //org.springframework.validation.support.BindingAwareModelMap
        model.addAttribute("testRequestScope", "hello,Model");
        return "success";
    }

    @RequestMapping("/test/modelMap")
    public String testModelMap(ModelMap modelMap) {
        System.out.println(modelMap.getClass().getName());
        //org.springframework.validation.support.BindingAwareModelMap
        modelMap.addAttribute("testRequestScope", "hello,ModelMap");
        return "success";
    }

    @RequestMapping("/test/map")
    public String testMap(Map<String, Object> map) {
        System.out.println(map.getClass().getName());
        //org.springframework.validation.support.BindingAwareModelMap
        map.put("testRequestScope", "hello,map");
        return "success";
    }

    /**
     * 通过ServletAPI向Session域共享数据
     *
     * @param session
     * @return
     */
    @RequestMapping("/test/session")
    public String testSession(HttpSession session) {
        session.setAttribute("testSessionScope", "hello,session");
        return "success";
    }

    @RequestMapping("/test/application")
    public String testApplication(HttpSession session) {
        ServletContext servletContext = session.getServletContext();
        servletContext.setAttribute("testApplicationScope", "hello,application");
        return "success";
    }
}
