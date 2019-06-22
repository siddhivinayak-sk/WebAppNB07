/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.appconfig;

import com.app.aop.BasicAspect;
import com.app.controllers.validators.StudentValidator;
import com.app.interceptor.CustomHandlerInterceptorAdapter;
//import com.app.resolvers.CustomBeanPostProcessor;
import com.app.resolvers.CustomViewResolver;
import com.app.resolvers.DbPropertySourcesPlaceholderConfigurer;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.servlet.ServletContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles2.TilesConfigurer;

/**
 *
 * @author sandeep.kumar
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.app.controllers")	
//@PropertySource("classpath*:spring/*.properties")
public class WebConfig 
        //extends WebMvcConfigurationSupport
        extends WebMvcConfigurerAdapter 
        {
    
    
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/scripts/").addResourceLocations("/scripts/**");
    }    
    

    /*
    @Bean
    public CustomBeanPostProcessor _customBeanPostProcessor() {
        CustomBeanPostProcessor temp = new CustomBeanPostProcessor();
        return temp;
    }
    
    @Bean
    public Properties dbProperties() {
        Properties temp = DbPropertySourcesPlaceholderConfigurer.getProperties();
        return temp;
    }
    */
    
    
    @Bean
    public BasicAspect myAspect3() {
        BasicAspect ba = new BasicAspect();
        return ba;
    }
    
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource tmp = new ReloadableResourceBundleMessageSource();
        tmp.setBasenames("WEB-INF/i18n/messages", "WEB-INF/i18n/application");
        tmp.setFallbackToSystemLocale(false);
        tmp.setDefaultEncoding("UTF-8");
        return tmp;
    }
    
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver irvr = new InternalResourceViewResolver();
        irvr.setPrefix("/WEB-INF/jsps/");
        irvr.setSuffix(".jsp");
        irvr.setOrder(1);
        return irvr;
    }
    
    @Bean
    public CustomViewResolver tilesViewResolver() {
        List<String> ignoreList = new ArrayList<String>();
        ignoreList.add("uncaughtexception");
        ignoreList.add("dataaccessfailure");
        ignoreList.add("resourcenotfound");
        ignoreList.add("timemismatch");
        ignoreList.add("parametermissingfound");
        ignoreList.add("nohandler");
        ignoreList.add("defaulterrorview");
        ignoreList.add("redirect:.*");
        CustomViewResolver cvr = new CustomViewResolver();
        cvr.setViewClass(org.springframework.web.servlet.view.tiles2.TilesView.class);
        cvr.setViewsToIgnore(ignoreList);
        cvr.setOrder(0);
        return cvr;
    }
    
    @Bean
    public TilesConfigurer tilesConfigurer(ServletContext servletContext) {
        TilesConfigurer tc = new TilesConfigurer();
        tc.setDefinitions("/WEB-INF/layouts/layouts.xml", "/WEB-INF/views/**/views.xml");
        tc.setPreparerFactoryClass(org.springframework.web.servlet.view.tiles2.SpringBeanPreparerFactory.class);
        tc.setServletContext(servletContext);
        tc.afterPropertiesSet();
        return tc;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver cmr = new CommonsMultipartResolver();
        cmr.setMaxUploadSize(52428800);
        return cmr;
    }
    
    @Bean(name ="studentValidator")
    public StudentValidator studentValidator() {
    	return new StudentValidator();
    }
    
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CustomHandlerInterceptorAdapter());
	}
}
