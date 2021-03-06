/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.jms.senders;

import com.app.jms.model.AppMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

/**
 *
 * @author sandeepkumar
 */
@Component
public class MessagePublisher {
    
    @Autowired
    JmsTemplate jmsTemplate2;
    
    public void publishMessage(final AppMessage messsage) {
        jmsTemplate2.send(new MessageCreator() {
            @Override
            public Message createMessage(Session sn) throws JMSException {
                return sn.createObjectMessage(messsage);
            }
        });
    }
    
}
