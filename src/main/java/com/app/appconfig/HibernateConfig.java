/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.appconfig;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import com.app.models.*;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
/**
 *
 * @author sandeep.kumar
 */
@Configuration
public class HibernateConfig {
    
    @Autowired
    @Qualifier("dataSource")
    DataSource dataSource;
    
    @Bean
    public SessionFactory hibernateSessionFactory() throws Exception {
        Properties hp = new Properties();
        //hp.put("hibernate.dialect", "org.hibernate.dialect.DerbyDialect");
        //hp.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        hp.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hp.put("hibernate.hbm2ddl.auto", "update");
        hp.put("hibernate.show_sql", "true");
        LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
        lsfb.setDataSource(dataSource);
        lsfb.setHibernateProperties(hp);
        lsfb.setAnnotatedClasses(new Class[]{Customer.class,DiscountCode.class,Manufacturer.class,MicroMarket.class,MyappUser.class,MyappPermissions.class,MyappRoles.class,Product.class,ProductCode.class,PurchaseOrder.class,Property.class});
        lsfb.afterPropertiesSet();
        return lsfb.getObject();
    }
    
    @Bean
    public AbstractPlatformTransactionManager transactionManager1() throws Exception {
        HibernateTransactionManager htm = new HibernateTransactionManager();
        htm.setSessionFactory(hibernateSessionFactory());
        htm.afterPropertiesSet();
        return htm;
    }
}
