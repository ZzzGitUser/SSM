package com.learn.spring.aop.annotation;

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

/**
 * 1、在切面中，需要通过指定的注解将方法标识为通知方法
 * *@Before：前置通知，在目标对象方法执行之前执行
 * *@After：后置通知，在目标对象方法的finally语句中执行
 * *@AfterReturning：返回通知，在目标对象方法返回值之后执行
 * *@AfterThrowing：异常通知，在目标对象方法的catch语句中执行
 * <p>
 * <p>
 * 2、切入点表达式：设置在标识通知的注解中，通过注解的value属性设置
 * execution(public int com.learn.spring.aop.annotation.CalculatorImpl.add(int,int))
 * execution(* com.learn.spring.aop.annotation.CalculatorImpl.*(..))
 * 第一个*表示任意的访问修饰符和返回值类型
 * 第二个*表示类中的任意方法
 * ..表示任意的参数列表
 * 类的地方也可以用*，表示包下所有的类
 * <p>
 * <p>
 * 3、重用切入点表达式
 * //Pointcut注解声明一个公共的切入点表达式
 * *@Pointcut("execution(* com.learn.spring.aop.annotation.CalculatorImpl.*(..))")
 * *public void pointCut(){}
 * *使用方式：@Before("pointCut()")
 * <p>
 * <p>
 * 4、获取连接点的信息
 * 在通知方法的参数位置，设置JoinPoint类型的参数，就可以获取连接点所对应的方法的信息
 * //获取连接点所对应的方法的签名信息
 * Signature signature = joinPoint.getSignature();
 * //获取连接点所对应的方法的参数
 * Object[] args = joinPoint.getArgs();
 */

@Component
@Aspect //将当前组件标识为切面
public class LoggerAspect {

    @Pointcut("execution(* com.learn.spring.aop.annotation.CalculatorImpl.*(..))")
    public void pointCut() {
    }

    //@Before("execution(public int com.learn.spring.aop.annotation.CalculatorImpl.add(int,int))")
    @Before("pointCut()")
    public void beforeAdviceMethod(JoinPoint joinPoint) {
        //获取连接点所对应的方法的签名信息
        Signature signature = joinPoint.getSignature();
        //获取连接点所对应的方法的参数
        Object[] args = joinPoint.getArgs();
        System.out.println("LoggerAspect前置通知，方法：" + signature.getName() + "，参数：" + Arrays.toString(args));
    }

    @After("pointCut()")
    public void afterAdviceMethod(JoinPoint joinPoint) {
        //获取连接点所对应的方法的签名信息
        Signature signature = joinPoint.getSignature();
        System.out.println("LoggerAspect后置通知，方法：" + signature.getName() + "，执行完毕");
    }

    /**
     * 在返回通知中，若要获取目标对象方法的返回值
     * 只需要通过AfterReturning注解的returning属性
     * 就可以将通知方法的某个形参，指定为接受目标对象方法的返回值的参数
     *
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value = "pointCut()", returning = "result")
    public void afterReturningAdviceMethod(JoinPoint joinPoint, Object result) {
        //获取连接点所对应的方法的签名信息
        Signature signature = joinPoint.getSignature();
        System.out.println("LoggerAspect返回通知，方法：" + signature.getName() + "，结果：" + result);
    }

    /**
     * *@AfterThrowing注解中的属性throwing，用来将通知方法的某个形参，接收目标方法的异常
     *
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value = "pointCut()", throwing = "ex")
    public void afterThrowingAdviceMethod(JoinPoint joinPoint, Throwable ex) {
        System.out.println("LoggerAspect异常通知，方法：" + joinPoint.getSignature().getName() + "，异常：" + ex);
    }

    //环绕通知的方法的返回值，一定要和目标对象方法的返回值一致
    @Around("pointCut()")
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
