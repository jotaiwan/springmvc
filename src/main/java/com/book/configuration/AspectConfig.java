package com.book.configuration;

import com.book.aspect.Audience;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by jotaiwan on 2/08/2017.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.book.aspect")
public class AspectConfig {

//    @Bean
//    public Audience audience() {
//        return new Audience();
//    }
}
