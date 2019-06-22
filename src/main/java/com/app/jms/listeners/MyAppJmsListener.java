/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.jms.listeners;

import com.app.jms.model.AppMessage;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

/**
 *
 * @author sandeepkumar
 */
@Component
public class MyAppJmsListener implements SessionAwareMessageListener {

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    
    private String clientName;
    
    public void onMessage(Message message, Session session) {
        ObjectMessage objMessage = (ObjectMessage)message;
        try {
            AppMessage appMessage = (AppMessage)objMessage.getObject();
            System.out.println(clientName + " : " + appMessage);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
