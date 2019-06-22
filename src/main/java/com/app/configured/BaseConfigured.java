/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.configured;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.app.services.BaseService;


@Configurable
public class BaseConfigured {
	
	@Autowired
	BaseService baseService;
	
	public String getBaseMessage() {
		return baseService.getBaseMessage();
	}

}
