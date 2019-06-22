/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.appconfig;

import com.app.jms.listeners.MyAppJmsListener;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

/**
 *
 * @author sandeep.kumar
 */
@Configuration
public class JMSConfig {
    
    @Bean
    public ActiveMQConnectionFactory jmsFactory() {
        ActiveMQConnectionFactory amqcf = new ActiveMQConnectionFactory();
        amqcf.setBrokerURL("tcp://localhost:61616");
        amqcf.setTrustAllPackages(true);
        return amqcf;
    }
    
    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate temp = new JmsTemplate();
        temp.setConnectionFactory(jmsFactory());
        temp.setDefaultDestinationName("TestQueue1");
        return temp;
    }
    
    
    @Bean
    public DefaultMessageListenerContainer messageListenerContainer1() {
        MyAppJmsListener temp = new MyAppJmsListener();
        temp.setClientName("Client 1");
        DefaultMessageListenerContainer dmlc = new DefaultMessageListenerContainer();
        dmlc.setConnectionFactory(jmsFactory());
        dmlc.setDestinationName("TestQueue1");
        dmlc.setMessageListener(temp);
        return dmlc;
    }
    
    @Bean
    public DefaultMessageListenerContainer messageListenerContainer2() {
        MyAppJmsListener temp = new MyAppJmsListener();
        temp.setClientName("Client 2");
        DefaultMessageListenerContainer dmlc = new DefaultMessageListenerContainer();
        dmlc.setConnectionFactory(jmsFactory());
        dmlc.setDestinationName("TestQueue1");
        dmlc.setMessageListener(temp);
        return dmlc;
    }

    @Bean
    public JmsTemplate jmsTemplate2() {
        JmsTemplate temp = new JmsTemplate();
        temp.setConnectionFactory(jmsFactory());
        temp.setDefaultDestinationName("TestTopic1");
        temp.setPubSubDomain(true);
        return temp;
    }
    
    @Bean
    public DefaultMessageListenerContainer messageListenerContainer3() {
        MyAppJmsListener temp = new MyAppJmsListener();
        temp.setClientName("Client 3");
        DefaultMessageListenerContainer dmlc = new DefaultMessageListenerContainer();
        dmlc.setConnectionFactory(jmsFactory());
        dmlc.setDestinationName("TestTopic1");
        dmlc.setPubSubDomain(true);
        dmlc.setMessageListener(temp);
        return dmlc;
    }
    
    @Bean
    public DefaultMessageListenerContainer messageListenerContainer4() {
        MyAppJmsListener temp = new MyAppJmsListener();
        temp.setClientName("Client 4");
        DefaultMessageListenerContainer dmlc = new DefaultMessageListenerContainer();
        dmlc.setConnectionFactory(jmsFactory());
        dmlc.setDestinationName("TestTopic1");
        dmlc.setPubSubDomain(true);
        dmlc.setMessageListener(temp);
        return dmlc;
    }
}
