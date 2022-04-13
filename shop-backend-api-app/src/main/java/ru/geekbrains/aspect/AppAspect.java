package ru.geekbrains.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AppAspect {

    private static final Logger logger = LoggerFactory.getLogger(AppAspect.class);

    @Pointcut("execution(*  ru.geekbrains.persist..*.*(..))")
    private void findMethods() {

    }

    @Before("findMethods()")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Call of {}", joinPoint);
    }

    @Around("@annotation(ru.geekbrains.aspect.TrackTime)")
    public Object trackTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        logger.info("Time taken by {} is {} ms", joinPoint, System.currentTimeMillis() - start);

        return result;
    }
}
