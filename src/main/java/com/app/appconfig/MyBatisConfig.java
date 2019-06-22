/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.appconfig;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author sandeep.kumar
 */
@Configuration
public class MyBatisConfig {
    
    @Autowired
    @Qualifier("dataSource")
    DataSource dataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
        ssfb.setDataSource(dataSource);
        ssfb.afterPropertiesSet();
        return ssfb.getObject();
    }
    
    @Bean
    public MapperScannerConfigurer myBatisBuildConfigurer() throws Exception {
        MapperScannerConfigurer conf = new MapperScannerConfigurer();
        conf.setBasePackage("com.app.mappers");
        conf.afterPropertiesSet();
        return conf;
    }
    
}
