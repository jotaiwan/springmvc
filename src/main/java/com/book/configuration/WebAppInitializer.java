package com.book.configuration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;

import com.opensymphony.sitemesh.webapp.SiteMeshFilter;

import java.util.EnumSet;

/**
 * Created by jotaiwan on 20/08/2017.
 */
public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) {
        createAppRootContext(container);

        AnnotationConfigWebApplicationContext dispatcherContext = createDispatcherServlet();

        addSitemeshFilterToServletContext(container);

        // Register and map the dispatcher servlet
        ServletRegistration.Dynamic dispatcher =
                container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.setMultipartConfig(getMultipartConfigElement());
        dispatcher.addMapping("/");

    }

    private void createAppRootContext(ServletContext container) {
        // Create the 'root' Spring application context
        AnnotationConfigWebApplicationContext rootContext =
                new AnnotationConfigWebApplicationContext();
        rootContext.register(RootConfig.class);

        // Manage the lifecycle of the root application context
        container.addListener(new ContextLoaderListener(rootContext));
    }

    private AnnotationConfigWebApplicationContext createDispatcherServlet() {
        // Create the dispatcher servlet's Spring application context
        AnnotationConfigWebApplicationContext dispatcherContext =
                new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(WebConfig.class);
        return dispatcherContext;
    }

    private void addSitemeshFilterToServletContext(ServletContext servletContext) {
        FilterRegistration.Dynamic sitemesh =
                servletContext.addFilter("sitemesh", new SiteMeshFilter());
        EnumSet<DispatcherType> sitemeshDispatcherTypes =
                EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);
        sitemesh.addMappingForUrlPatterns(sitemeshDispatcherTypes, true, "/*");
    }

    private MultipartConfigElement getMultipartConfigElement(){
        MultipartConfigElement multipartConfigElement =
                new MultipartConfigElement("C:/tmp", 1024*1024*5, 1024*1024*5*5, 1024*1024);
        return multipartConfigElement;
    }

    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        return new Filter[]{ characterEncodingFilter, new SiteMeshFilter() };
    }
}
