package com.example.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect // AOP 어노테이션
@Component // Spring에서 관리하기 위한 어노테이션
public class ParameterAop {

    @Pointcut("execution(* com.example.aop.controller..*.*(..))") // controller 패키지 하위 클래스에 모두 적용하겠다.
    private void cut(){}

    //@Before("cut()")  // cut 메소드 실행 전 시행
    public void before(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println(method.getName());

        Object[] args = joinPoint.getArgs(); // arguments

        for(Object obj : args){
            System.out.println("type : " + obj.getClass().getSimpleName());
            System.out.println("value : " + obj);
        }
    }

    //@AfterReturning(value = "cut()", returning = "returnObj") // cut 메소드 실행 후 시행
    public void afterReturn(JoinPoint joinPoint, Object returnObj){
        System.out.println("return obj");
        System.out.println(returnObj);
    }
}
