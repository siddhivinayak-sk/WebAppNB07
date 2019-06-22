/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.appconfig;

import net.sf.ehcache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

/**
 *
 * @author sandeep.kumar
 */
@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheEhManager() throws Exception {
        EhCacheManagerFactoryBean ecmfb = new EhCacheManagerFactoryBean();
        //ecmfb.setConfigLocation(new FileSystemResource(CacheConfig.class.getResource("ehcache.xml").getFile()));
        ecmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
        ecmfb.afterPropertiesSet();
        return ecmfb.getObject();
    }
    
    @Bean
    public EhCacheCacheManager cacheManager() throws Exception {
        EhCacheCacheManager eccm = new EhCacheCacheManager();
        eccm.setCacheManager(cacheEhManager());
        eccm.afterPropertiesSet();
        return eccm;
    }
}
