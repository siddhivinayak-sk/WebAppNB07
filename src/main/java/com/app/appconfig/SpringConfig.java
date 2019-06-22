/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.appconfig;

import com.app.dynamic.datasource.CustomAbstractRoutingDataSource;
import com.app.dynamic.datasource.RoutingDataSourceType;
//import com.app.resolvers.CustomBeanPostProcessor;
import com.app.resolvers.DbPropertySourcesPlaceholderConfigurer;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.EnableLoadTimeWeaving.AspectJWeaving;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.FileSystemResource;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author sandeep.kumar
 */
@Configuration
@ComponentScan("com.app.services, com.app.configured, com.app.jms.listeners, com.app.jms.senders, com.app.daos")
@EnableTransactionManagement
@EnableLoadTimeWeaving(aspectjWeaving = AspectJWeaving.AUTODETECT)
@Import({JPAConfig.class, AOPConfig.class, CacheConfig.class, HibernateConfig.class, JMSConfig.class, MyBatisConfig.class, SecurityConfig.class})
//@PropertySource("classpath*:spring/*.properties")
public class SpringConfig {

	/*
    @Bean
    public CustomBeanPostProcessor customBeanPostProcessor() {
        return new CustomBeanPostProcessor();
    }
    
    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer temp = new PropertyPlaceholderConfigurer();
        temp.setIgnoreUnresolvablePlaceholders(true);
        temp.setIgnoreResourceNotFound(true);
        temp.setOrder(1);
        temp.setLocation(new FileSystemResource("classpath:spring/application.properties"));
        return temp;
    }
    
    @Bean
    public DbPropertySourcesPlaceholderConfigurer dbPropertySourcesPlaceholderConfigurer() {
        DbPropertySourcesPlaceholderConfigurer temp = new DbPropertySourcesPlaceholderConfigurer();
        temp.setIgnoreUnresolvablePlaceholders(true);
        temp.setIgnoreResourceNotFound(true);
        temp.setOrder(2);
        temp.setPlaceholderPrefix("${");
        temp.setPlaceholderSuffix("}");
        temp.setDataSourceName("dataSource");
        temp.setCustomBeanPostProcessor(customBeanPostProcessor());
        return temp;
    }
    */
    
    @Bean
    public DriverManagerDataSource dataSource02() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        
        //ds.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
        //ds.setUrl("jdbc:derby://localhost:1527/sample1");
        //ds.setUsername("app");
        //ds.setPassword("app");

        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:mem:test1");
        
        //ds.setDriverClassName("com.mysql.jdbc.Driver");
        //ds.setUrl("jdbc:mysql://localhost:3306/sample1");
        //ds.setUsername("root");
        //ds.setPassword("root");
        return ds;
    }

    @Bean
    public DriverManagerDataSource dataSource01() {
        DriverManagerDataSource ds = new DriverManagerDataSource();

        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:mem:test2");
        
        //ds.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
        //ds.setUrl("jdbc:derby://localhost:1527/sample2");
        //ds.setUsername("app");
        //ds.setPassword("app");

        //ds.setDriverClassName("com.mysql.jdbc.Driver");
        //ds.setUrl("jdbc:mysql://localhost:3306/sample2");
        //ds.setUsername("root");
        //ds.setPassword("root");
        return ds;
    }
    
    @Bean
    @Primary
    public DataSource dataSource() {
        CustomAbstractRoutingDataSource cards = new CustomAbstractRoutingDataSource();
        Map<Object, Object> dsMap = new HashMap<>();
        dsMap.put(RoutingDataSourceType.DB01, dataSource01());
        dsMap.put(RoutingDataSourceType.DB02, dataSource02());
        cards.setTargetDataSources(dsMap);
        cards.setDefaultTargetDataSource(dataSource02());
        return cards;
    }
    
    @Bean
    public InstrumentationLoadTimeWeaver loadTimeWeaver() throws Throwable {
        InstrumentationLoadTimeWeaver loadTimeWeaver = new InstrumentationLoadTimeWeaver();
        return loadTimeWeaver;
    }    
    
}
