package com.app.controllers;

import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

@ControllerAdvice("com.app.controllers")
public class ControllersAdvice {

	@ModelAttribute("boxedExample")
	public Object modelInit() {
//		System.out.println("Controller Advice");
		return(new String("BoxedExample Text"));
	}
        
        
        @ExceptionHandler(Throwable.class)
        private ModelAndView onException(Throwable th) {
            ModelAndView view = new ModelAndView("/");
            if(th instanceof DataAccessException) {
                view = new ModelAndView("dataaccessfailure");
            }
            else if(th instanceof NoSuchRequestHandlingMethodException) {
                view = new ModelAndView("resourcenotfound");
            }
            else if(th instanceof TypeMismatchException) {
                view = new ModelAndView("timemismatch");
            }
            else if(th instanceof MissingServletRequestParameterException) {
                view = new ModelAndView("parametermissingfound");
            }
            else if(th instanceof AccessDeniedException) {
                view = new ModelAndView("unauthorizedaccess");
            }
            else if(th instanceof NoHandlerFoundException) {
                view = new ModelAndView("nohandler");
            }
            else {
                view = new ModelAndView("defaulterrorview");
            }
            return view;
        }
        
}
