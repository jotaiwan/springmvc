package com.book.configuration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * Created by jotaiwan on 20/08/2017.
 */
public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) {
        // Create the 'root' Spring application context
        AnnotationConfigWebApplicationContext rootContext =
                new AnnotationConfigWebApplicationContext();
        rootContext.register(RootConfig.class);

        // Manage the lifecycle of the root application context
        container.addListener(new ContextLoaderListener(rootContext));

        // Create the dispatcher servlet's Spring application context
        AnnotationConfigWebApplicationContext dispatcherContext =
                new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(WebConfig.class);

        // Register and map the dispatcher servlet
        ServletRegistration.Dynamic dispatcher =
                container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.setMultipartConfig(getMultipartConfigElement());
        dispatcher.addMapping("/");

        /*
        * Servlet Filter that allows one to specify a character encoding for requests. This is
        * useful because current browsers typically do not set a character encoding even if
        * specified in the HTML page or form
        *
        **/
//        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//        characterEncodingFilter.setEncoding("UTF-8");
//        characterEncodingFilter.setForceEncoding(true);
//
//        EnumSet<DispatcherType> dispatcherTypes =
//                EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);
//        FilterRegistration.Dynamic characterEncoding =
//                container.addFilter("CharacterEncodingFilter", characterEncodingFilter);
//        characterEncoding.addMappingForUrlPatterns(dispatcherTypes, true, "/*");

    }

    private MultipartConfigElement getMultipartConfigElement(){
        MultipartConfigElement multipartConfigElement =
                new MultipartConfigElement("C:/tmp", 1024*1024*5, 1024*1024*5*5, 1024*1024);
        return multipartConfigElement;
    }
}
