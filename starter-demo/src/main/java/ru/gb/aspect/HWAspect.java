package ru.gb.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;


@Slf4j
@Aspect
public class HWAspect  {

    @Pointcut("within(@ru.gb.aspect.Timer *)")
    public void beansAnnotatedWith() {

    }

    @Pointcut("@annotation(ru.gb.aspect.Timer)")
    public void methodsAnnotatedWith() {

    }



    @Around("beansAnnotatedWith() || methodsAnnotatedWith()")
    public Object TimerAspect(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("method = {}", joinPoint.getSignature());
        try {
            long start = System.currentTimeMillis();
            Object returnValue = joinPoint.proceed();
            long duration = System.currentTimeMillis() - start;
            log.info("количество миллисекунд выполнения: {}",duration);
            return returnValue;

        } catch (Throwable e) {
           log.info("exception = [{}, {}]", e.getClass(), e.getMessage());
             throw e;
        }
    }

}