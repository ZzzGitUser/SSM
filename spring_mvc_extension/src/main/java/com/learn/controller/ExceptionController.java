package com.learn.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//注解@ControllerAdvice：将当前类标识为处理异常的组件
@ControllerAdvice
public class ExceptionController {

    //注解@ExceptionHandler：设置所标识的方法处理的异常
    @ExceptionHandler(ArithmeticException.class)
    //形参ex：表示当前请求处理中出现的异常对象
    public String handleException(Throwable ex, Model model) {
        model.addAttribute("ex", ex);
        return "error";
    }
}
