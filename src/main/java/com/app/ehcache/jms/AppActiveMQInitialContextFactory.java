/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.ehcache.jms;

import java.net.URISyntaxException;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.naming.Context;
import javax.naming.NamingException;
import org.apache.activemq.jndi.ActiveMQInitialContextFactory;
import net.sf.ehcache.distribution.jms.JMSUtil;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author sandeepkumar
 */
public class AppActiveMQInitialContextFactory extends ActiveMQInitialContextFactory {
    
    @Override
    protected ActiveMQConnectionFactory createConnectionFactory(Hashtable environment) throws URISyntaxException {
        ActiveMQConnectionFactory factory = super.createConnectionFactory(environment);
        factory.setTrustAllPackages(true);
        return factory;
    }
    
    @Override
    public Context getInitialContext(Hashtable environment) throws NamingException {
        Map<String, Object> data = new ConcurrentHashMap<String, Object>();
        String replicationTopicConnectionFactoryBindingName = (String) environment.get(JMSUtil.TOPIC_CONNECTION_FACTORY_BINDING_NAME);
        if (replicationTopicConnectionFactoryBindingName != null) {
            try {
                data.put(replicationTopicConnectionFactoryBindingName, createConnectionFactory(environment));
            } 
            catch (URISyntaxException e) {
                throw new NamingException("Error initialisating TopicConnectionFactory with message "+ e.getMessage());
            }
        }
        String getQueueConnectionfactoryBindingName = (String) environment.get(JMSUtil.GET_QUEUE_CONNECTION_FACTORY_BINDING_NAME);
        try {
            data.put(getQueueConnectionfactoryBindingName, createConnectionFactory(environment));
        }
        catch (URISyntaxException e) {
            throw new NamingException("Error initialisating TopicConnectionFactory with message "+ e.getMessage());
        }
        String replicationTopicBindingName = (String) environment.get(JMSUtil.REPLICATION_TOPIC_BINDING_NAME);
        String getQueueBindingName = (String) environment.get(JMSUtil.GET_QUEUE_BINDING_NAME);
        if (replicationTopicBindingName != null) {
            data.put(replicationTopicBindingName, createTopic(replicationTopicBindingName));
        }
        data.put(getQueueBindingName, createQueue(getQueueBindingName));
        return createContext(environment, data);
    }
}
