/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.appconfig;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 *
 * @author sandeep.kumar
 */
public class CustomSecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
    public CustomSecurityWebApplicationInitializer() {
        super(SpringConfig.class);
    }
}
