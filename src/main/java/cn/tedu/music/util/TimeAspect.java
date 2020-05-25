package cn.tedu.music.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeAspect {

    @Around("execution(* cn.tedu.music.service.impl.*.*(..))")
    public Object process(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Long start=System.currentTimeMillis();
        Object obj=proceedingJoinPoint.proceed();
        Long end=System.currentTimeMillis();
        System.err.println("耗时:"+(end-start)+"ms!");
        return obj;
    }
}
