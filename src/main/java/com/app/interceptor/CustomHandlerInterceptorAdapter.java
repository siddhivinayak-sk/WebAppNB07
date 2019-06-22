/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.interceptor;

import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author sandeepkumar
 */
public class CustomHandlerInterceptorAdapter extends HandlerInterceptorAdapter {
    
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Pre-Handle: " + System.currentTimeMillis());
        PrintWriter pw = response.getWriter();
        Enumeration e = request.getHeaderNames();
        while(e.hasMoreElements()) {
            Object ob = e.nextElement();
            pw.println(ob + " : " + request.getHeader(ob.toString()));
        }
        pw.println("-----------------------------------------------------------------------------");
        Enumeration e1 = request.getLocales();
        while(e1.hasMoreElements()) {
            Object ob = e1.nextElement();
            pw.println(ob);
        }
        pw.println("-----------------------------------------------------------------------------");
        Enumeration e2 = request.getParameterNames();
        while(e2.hasMoreElements()) {
            Object ob = e2.nextElement();
            pw.println(ob + " : " + request.getParameter(ob.toString()));
        }
        pw.println("-----------------------------------------------------------------------------");
        pw.println("Method: " + request.getMethod());
        pw.println("Auth Type: " + request.getAuthType());
        pw.println("Char Encoding: " + request.getCharacterEncoding());
        pw.println("Content Type: " + request.getContentType());
        pw.println("Context Path: " + request.getContextPath());
        pw.println("Local Address: " + request.getLocalAddr());
        pw.println("Local Name: " + request.getLocalName());
        pw.println("Local Port: " + request.getLocalPort());
        pw.println("Path Info: " + request.getPathInfo());
        pw.println("Path Translated: " + request.getPathTranslated());
        pw.println("Protocol: " + request.getProtocol());
        pw.println("Query String: " + request.getQueryString());
        pw.println("Remote Addr: " + request.getRemoteAddr());
        pw.println("Remote Host: " + request.getRemoteHost());
        pw.println("Remote User: " + request.getRemoteUser());
        pw.println("Remote Port: " + request.getRemotePort());
        pw.println("Requested URI: " + request.getRequestURI());
        pw.println("Requested SessionId: " + request.getRequestedSessionId());
        pw.println("Scheme: " + request.getScheme());
        pw.println("Server Name: " + request.getServerName());
        pw.println("Server Port: " + request.getServerPort());
        pw.println("Servlet Path: " + request.getServletPath());
        response.setStatus(500);
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Post-Handle: " + System.currentTimeMillis());
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("After Completion: " + System.currentTimeMillis());
    }

    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("After Concurrent Handling Started: " + System.currentTimeMillis());
    }
    
}
