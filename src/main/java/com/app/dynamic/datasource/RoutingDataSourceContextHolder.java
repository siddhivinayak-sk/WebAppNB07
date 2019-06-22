/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.dynamic.datasource;

/**
 *
 * @author sandeep.kumar
 */
public class RoutingDataSourceContextHolder {
    private static final ThreadLocal<RoutingDataSourceType> contextHolder = new ThreadLocal<RoutingDataSourceType>() {
        @Override
        protected RoutingDataSourceType initialValue() {
            return RoutingDataSourceType.DB02;
        }
    };
    
    public static void setDB(RoutingDataSourceType type) {
        contextHolder.set(type);
    }
    
    public static RoutingDataSourceType getDB() {
        return contextHolder.get();
    }
    
    public static void clearDB() {
        contextHolder.remove();
    }
}
