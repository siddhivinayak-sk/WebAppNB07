/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.appconfig;

import com.app.aop.BasicAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author sandeep.kumar
 */
@Configuration
public class AOPConfig {
    
    @Bean
    public BasicAspect myAspect2() {
        return new BasicAspect();
    }
}
