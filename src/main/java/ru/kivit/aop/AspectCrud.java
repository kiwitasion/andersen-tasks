package ru.kivit.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectCrud {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //PointCuts
    @Pointcut("execution(* ru.kivit.services.*.find*(..))")
    public void callFindMethod(){}

    @Pointcut("execution(* ru.kivit.services.*.save*(..))")
    public void callSaveMethod(){}

    @Pointcut("execution(* ru.kivit.services.*.update*(..))")
    public void callUpdateMethod(){}

    @Pointcut("execution(* ru.kivit.services.*.delete*(..))")
    public void callDeleteMethod(){}

    //Find methods
    @Before("callFindMethod()")
    public void beforeFindMethod(JoinPoint joinPoint){
        logger.info("Start selection by {}", joinPoint);
    }

    @AfterReturning("callFindMethod()")
    public void afterReturningFindMethod(JoinPoint joinPoint){
        logger.info("Finish selection by {}", joinPoint);
    }


    //Save methods
    @Before("callSaveMethod()")
    public void beforeSaveMethod(JoinPoint joinPoint){
        logger.info("Start add to database by {}", joinPoint);
    }

    @After("callSaveMethod()")
    public void afterSaveMethod(JoinPoint joinPoint){
        logger.info("Finish add to database by {}", joinPoint);
    }


    //Update methods
    @Before("callUpdateMethod()")
    public void beforeUpdateMethod(JoinPoint joinPoint){
        logger.info("Start update by {}", joinPoint);

    }

    @After("callUpdateMethod()")
    public void afterUpdateMethod(JoinPoint joinPoint){
        logger.info("Finish update by {}", joinPoint);
    }

    //Delete methods
    @Before("callDeleteMethod()")
    public void beforeDeleteMethod(JoinPoint joinPoint){
        logger.info("Start deleting by {}", joinPoint);

    }

    @After("callDeleteMethod()")
    public void afterDeleteMethod(JoinPoint joinPoint){
        logger.info("Finish deleting by {}", joinPoint);
    }

}
