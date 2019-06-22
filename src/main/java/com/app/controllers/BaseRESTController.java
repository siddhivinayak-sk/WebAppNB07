/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controllers;

import com.app.daos.MyappUserDAO1;
import com.app.daos.MyappUserDAO2;
import com.app.models.MyappUser;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sandeepkumar
 */
@RestController
@RequestMapping("/rest")
public class BaseRESTController {
    
    //@Autowired
    //MyappUserDAO1 dao1;
    
    @Autowired
    MyappUserDAO2 dao2;
    
    @RequestMapping("r1")
    public ResponseEntity rest1(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Response r1 = new Response(101, "M-101");
        if(1 == 1) {
            throw new RuntimeException("New Exception");
        }
        return new ResponseEntity(r1, HttpStatus.IM_USED);
    }

    @RequestMapping("r2")
    public ResponseEntity rest2(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam(value = "in", required = true)String in) {
        Response r1 = new Response(101, in);
        return new ResponseEntity(r1, HttpStatus.IM_USED);
    }

    @RequestMapping("r3")
    public ResponseEntity rest3(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam(value = "in", required = true)Integer in) {
        MyappUser user = dao2.getUserByUserId(in);
        return new ResponseEntity(user, HttpStatus.IM_USED);
    }

    @RequestMapping(value = "r4", produces = "application/json; charset=UTF-8")
    public ResponseEntity rest4(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam(value = "in", required = true)String in) {
        MyappUser user = dao2.getUserByLoginName(in);
        return new ResponseEntity(user, HttpStatus.IM_USED);
    }

    @RequestMapping("r5")
    public ResponseEntity rest5(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam(value = "in", required = true)Integer in) {
        MyappUser user = dao2.getUserByUserId(in);
        return new ResponseEntity(user, HttpStatus.IM_USED);
    }

    @RequestMapping(value = "r6", produces = "application/json; charset=UTF-8")
    public ResponseEntity rest6(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam(value = "in", required = true)String in) {
        MyappUser user = dao2.getUserByLoginName(in);
        return new ResponseEntity(user, HttpStatus.IM_USED);
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    static class Response {
        private Integer statusId;
        private String message;

        Response() {
        }
        
        Response(Integer i, String s) {
            this.statusId = i;
            this.message = s;
        }
        
        public Integer getStatusId() {
            return statusId;
        }

        public void setStatusId(Integer statusId) {
            this.statusId = statusId;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
