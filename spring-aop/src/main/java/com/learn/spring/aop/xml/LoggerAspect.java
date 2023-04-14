package com.learn.spring.aop.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class LoggerAspect {

    public void pointCut() {
    }

    public void beforeAdviceMethod(JoinPoint joinPoint) {
        //获取连接点所对应的方法的签名信息
        Signature signature = joinPoint.getSignature();
        //获取连接点所对应的方法的参数
        Object[] args = joinPoint.getArgs();
        System.out.println("LoggerAspect前置通知，方法：" + signature.getName() + "，参数：" + Arrays.toString(args));
    }

    public void afterAdviceMethod(JoinPoint joinPoint) {
        //获取连接点所对应的方法的签名信息
        Signature signature = joinPoint.getSignature();
        System.out.println("LoggerAspect后置通知，方法：" + signature.getName() + "，执行完毕");
    }

    public void afterReturningAdviceMethod(JoinPoint joinPoint, Object result) {
        //获取连接点所对应的方法的签名信息
        Signature signature = joinPoint.getSignature();
        System.out.println("LoggerAspect返回通知，方法：" + signature.getName() + "，结果：" + result);
    }

    public void afterThrowingAdviceMethod(JoinPoint joinPoint, Throwable ex) {
        System.out.println("LoggerAspect异常通知，方法：" + joinPoint.getSignature().getName() + "，异常：" + ex);
    }

    public Object aroundAdviceMethod(ProceedingJoinPoint proceedingJoinPoint) {
        Object result = null;
        try {
            System.out.println("环绕通知--->前置通知");
            result = proceedingJoinPoint.proceed();
            System.out.println("环绕通知--->返回通知");
        } catch (Throwable e) {
            System.out.println("环绕通知--->异常通知");
            e.printStackTrace();
        } finally {
            System.out.println("环绕通知--->后置通知");
        }
        return result;
    }
}
