package com.app.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.annotation.After;
import org.hibernate.Hibernate;
import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.app.configured.BaseConfigured;
import com.app.resolvers.DbPropertySourcesPlaceholderConfigurer;
import com.app.models.MyappRoles;
import com.app.models.MyappUser;
import com.app.services.MyappUserService;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
//@RequestMapping("/")
public class LoginController {
	
	@Autowired
	MyappUserService myappUserService;
	
	@Autowired
	private RequestMappingHandlerMapping handlerMapping;
	 
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
	WebApplicationContext  webApplicationContext;
	
	@RequestMapping(value = {"/"})
	public String root() {
            return("login");
	}

	
	@RequestMapping(value = "/login")
	public ModelAndView login(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout) {
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}
		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");
		return model;
	}	
	
	@PreAuthorize("hasAuthority('P1')")
	@RequestMapping(value = "/home**", method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Custom Login Form");
		model.addObject("message", "This is protected page!");
		model.setViewName("home");
		return model;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logoutPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("logout");
		return model;
	}
	
	@RequestMapping(value = "/accessdenied", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView accessdeniedPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("accessdenied");
		return model;
	}
	
	
	/* Test */
	@RequestMapping("/login_user")
	public String root3(@RequestParam("loginId")String loginId, @RequestParam("password")String password, HttpSession session, HttpServletRequest servletRequest, HttpServletResponse servletResponse, ModelMap modelMap) {
		MyappUser myappUser = myappUserService.getUserByLoginName(loginId);
		if(null != myappUser && myappUser.getLoginPassword().equals(password)) {
			return("home");
		}
		else {
			modelMap.addAttribute("errorMessage", "Login attempt fail, please try again with correct user id and password");
			return("login");
		}
	}
	
	@Value("${version:100}")
	private String version;
	
	@Value("${max}")
	private String max;

	@Value("${min}")
	private String min;
	
	@RequestMapping("/at1")
	@ResponseBody
	public String annotationTesting() {
		String retVal = version + " - " + min + " - " + max;
		return(retVal);
	}
	
	
	//@Autowired
	//DbPropertySourcesPlaceholderConfigurer dpspc;
	
	@RequestMapping("/update")
	@ResponseBody
	public String annotationTesting1() {
		//dpspc.updateProperties();
		String retVal = version;
		return(retVal);
	}
	
	@ModelAttribute
	private Object initModels() {
		System.out.println("LoginController Model Init");
		return(new Object());
	}
}
