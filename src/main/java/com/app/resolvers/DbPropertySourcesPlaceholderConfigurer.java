/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.resolvers;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;


public class DbPropertySourcesPlaceholderConfigurer extends PropertyPlaceholderConfigurer  { /*
   private static final String DEFAULT_DATASOURCENAME = "dataSource";
   private static final String DEFAULT_DBTABLENAME = "property";
   private static final String DEFAULT_DBKEYCOLUMNNAME = "pkey";
   private static final String DEFAULT_DBVALUECOLUMNNAME = "pvalue";
   String dataSourceName;
   String dbTableName;
   String dbKeyColumnName;
   String dbValueColumnName;
   DataSource dataSource = null;
   private static Properties dbProps = new Properties();
   private CustomBeanPostProcessor customBeanPostProcessor;
   
   @Override
   public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
      dataSource = (DataSource) beanFactory.getBean(getDataSourceName());
      loadProperties();
      setProperties(dbProps);
      super.postProcessBeanFactory(beanFactory);
   }

   public String getDataSourceName() {
      return dataSourceName==null?DEFAULT_DATASOURCENAME:dataSourceName;
   }

   public void setDataSourceName(String dataSourceName) {
      this.dataSourceName = dataSourceName;
   }

    public CustomBeanPostProcessor getCustomBeanPostProcessor() {
        return customBeanPostProcessor;
    }

    public void setCustomBeanPostProcessor(CustomBeanPostProcessor customBeanPostProcessor) {
        this.customBeanPostProcessor = customBeanPostProcessor;
    }
   

   private void loadProperties() {
       Connection con = null;
       try {
           con = dataSource.getConnection();
           ResultSet rs = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("select * from property");
           while(rs.next()) {
               String key = rs.getString(1);
               key = key.toLowerCase();
               String value = rs.getString(2);
               dbProps.put(key, value);
           }
           rs.close();
       }
       catch(Exception e) {
           e.printStackTrace();
       }
       finally {
           try {
               if(con != null) {
                   con.close();
               }
           }
           catch(Exception e1) {
               e1.printStackTrace();
           }
       }
   }
   
   private void updateObjects() {
	   System.out.println(customBeanPostProcessor.getObjectMap());
       if(null != customBeanPostProcessor || null != customBeanPostProcessor.getObjectMap() || customBeanPostProcessor.getObjectMap().size() < 0) {
           for(String key:customBeanPostProcessor.getObjectMap().keySet()) {
               if(null != dbProps.get(key)) {
                   List<Object> objectList = customBeanPostProcessor.getObjectMap().get(key);
                   if(objectList != null && objectList.size() > 0) {
                       for(Object object:objectList) {
                           if(null != object) {
                            for(Field field:object.getClass().getDeclaredFields()) {
                                Value value = field.getAnnotation(Value.class);
                                if(null != value && null != value.value() && value.value().replace("${", "").replace("}", "").length() > 0 && value.value().replace("${", "").replace("}", "").equalsIgnoreCase(key) && field.getType() == String.class) {
                                    field.setAccessible(true);
                                    try {
                                        field.set(object, dbProps.get(key));
                                    }
                                    catch(IllegalAccessException iae) {
                                        iae.printStackTrace();
                                    }
                                }
                            }
                           }
                       }
                   } 
               }
           }
       }
   }
   
   public void updateProperties() {
       loadProperties();
       updateObjects();
   }
   
   public static Properties getProperties() {
	   return(dbProps);
   } */
}