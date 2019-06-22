/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.jms.model;

import java.io.Serializable;

/**
 *
 * @author sandeepkumar
 */
public class AppMessage implements Serializable {
    private Integer messageId;
    private String subject;
    private String sender;
    private String receiver;
    private String body;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "AppMessage{" + "messageId=" + messageId + ", subject=" + subject + ", sender=" + sender + ", receiver=" + receiver + ", body=" + body + '}';
    }
    
    
    
}
