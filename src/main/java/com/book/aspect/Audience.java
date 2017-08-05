package com.book.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jotaiwan on 2/08/2017.
 */
@Aspect
public class Audience {
    private static final Logger logger = LoggerFactory.getLogger(Audience.class);

    @Pointcut("execution(** com. com.book.concert.Performance.perform(..))")
    public void performance() {}

    @Before("performance()")
    public void silenceCellPhones() {
        logger.info("Silencing cell phones");
    }

    @Before("performance()")
    public void takeSeats() {
        logger.info("Taking seats");
    }

    @AfterReturning("performance()")
    public void applause() {
        logger.info("CALP! CLAP! CLAP!");
    }

    @AfterReturning("performance()")
    public void demandRefund() {
        logger.info("Demanding a refund");
    }
}
