package com.book.configuration;

import com.book.aspect.Audience;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by jotaiwan on 29/07/2017.
 */
@Configuration
@ComponentScan(basePackages = {"com.book"},
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class),
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
    })
@Import(value = {PersistenceConfig.class, AspectConfig.class})
public class RootConfig {
}
