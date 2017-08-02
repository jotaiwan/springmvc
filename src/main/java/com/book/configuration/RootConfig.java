package com.book.configuration;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by jotaiwan on 29/07/2017.
 */
@Configuration
@ComponentScan(basePackages = {"com.book"},
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class),
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class),
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Aspect.class)
    })
@Import(value = {PersistenceConfig.class})
public class RootConfig {
}
