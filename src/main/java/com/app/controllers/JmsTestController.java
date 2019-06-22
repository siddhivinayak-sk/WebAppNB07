/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controllers;

import com.app.jms.model.AppMessage;
import com.app.jms.senders.MessagePublisher;
import com.app.jms.senders.MessageSender;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author sandeepkumar
 */
@Controller
@RequestMapping("/jms")
public class JmsTestController {
    
    @Autowired
    MessageSender messageSender;
    
    @Autowired
    MessagePublisher messagePublisher;
    
    @RequestMapping("/sendmessage")
    @ResponseBody
    public AppMessage sendMessage1(@RequestParam(value = "message", required = false, defaultValue = "")String body) {
        AppMessage message = new AppMessage();
        message.setBody(body);
        try {
            message.setSubject(Calendar.getInstance().getTime().toString());
            messageSender.sendAppMessage(message);
        }
        catch(Exception e) {
            message.setSubject(e.getMessage());
        }
        finally {
            return message;
        }
    }

    @RequestMapping("/sendtopic")
    @ResponseBody
    public AppMessage sendMessage2(@RequestParam(value = "message", required = false, defaultValue = "")String body) {
        AppMessage message = new AppMessage();
        message.setBody(body);
        try {
            message.setSubject(Calendar.getInstance().getTime().toString());
            messagePublisher.publishMessage(message);
        }
        catch(Exception e) {
            message.setSubject(e.getMessage());
        }
        finally {
            return message;
        }
    }
}
