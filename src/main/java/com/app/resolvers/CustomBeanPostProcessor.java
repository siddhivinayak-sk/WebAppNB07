/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.resolvers;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author skumar2
 */
/*
public class CustomBeanPostProcessor implements BeanPostProcessor {
    
    public static Map<String, ArrayList<Object>> objectMap;
    
    public Object postProcessBeforeInitialization(Object o, String string) throws BeansException {
        return(o);
    }

    public Object postProcessAfterInitialization(Object o, String string) throws BeansException {
    	if(objectMap == null) {
    		objectMap = new HashMap<String, ArrayList<Object>>();
    	}
        if(null == o) {
            return(o);
        }
        if(AopUtils.isAopProxy(o) && o instanceof Advised) {
			Class targetClass = ((Advised)o).getTargetClass();
			System.out.println(targetClass.getName());
			Object object =  null;
			try {
				Object target = ((Advised)o).getTargetSource().getTarget();
				object = targetClass.cast(target);
	            for(Field field:object.getClass().getDeclaredFields()) {
	                Value value = field.getAnnotation(Value.class);
	                if(null != value && null != value.value() && value.value().replace("${", "").replace("}", "").length() > 0) {
	                    updateObjectMap(value.value().replace("${", "").replace("}", ""), object);
	                }
	            }
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
        }
        else {
            for(Field field:o.getClass().getDeclaredFields()) {
                Value value = field.getAnnotation(Value.class);
                if(null != value && null != value.value() && value.value().replace("${", "").replace("}", "").length() > 0) {
                    updateObjectMap(value.value().replace("${", "").replace("}", ""), o);
                }
            }
        }
        return(o);
    }
    
    private void updateObjectMap(String key, Object object) {
        ArrayList<Object> objectList = objectMap.get(key);
        if(null == objectList) {
            objectList = new ArrayList<Object>();
            objectList.add(object);
            objectMap.put(key, objectList);
        }
        else {
            objectList.add(object);
        }
    }
    
    public Map<String, ArrayList<Object>> getObjectMap() {
        return objectMap;
    }

    public void setObjectMap(Map<String, ArrayList<Object>> objectMap) {
        this.objectMap = objectMap;
    }
}
*/