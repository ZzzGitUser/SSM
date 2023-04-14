package com.learn.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器的作用：
 * 用于拦截控制器方法的执行
 * <p>
 * 拦截器的三个方法：
 * preHandle()：在控制器方法执行前执行，其返回值表示对控制器方法的拦截（false）或放行（true）
 * postHandle()：在控制器方法执行后执行
 * afterCompletion()：在控制器方法执行后，且视图渲染完毕后执行
 * <p>
 * 多个拦截器的执行顺序，和在SpringMVC的配置文件中配置的顺序有关
 * preHandle()按配置的顺序执行，而postHandle()和afterCompletion()按照配置的反序执行
 * <p>
 * 若多个拦截器中，有某个拦截器的preHandle()返回false，则：
 * 该拦截器，和它之前的拦截器，preHandle()都会被执行，
 * 所有拦截器，postHandle()都不被执行，
 * 该拦截器之前的拦截器，afterCompletion()会被执行
 */
@Component
public class FirstInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("");
        System.out.println("FirstInterceptor------preHandle");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("FirstInterceptor------postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("FirstInterceptor------afterCompletion");
        System.out.println("");
    }
}
