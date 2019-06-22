package com.app.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.app.controllers.validators.StudentValidator;
import com.app.dtos.FileUploadDTO;
import com.app.dtos.StudentDTO;

@Controller
@RequestMapping("/form")
public class FormTestController {
	
	@Autowired
	private WebApplicationContext applicationContext;
	
	@Autowired
	@Qualifier("studentValidator")
	private Validator validator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.addValidators(validator);
	}

	@RequestMapping(value = "/", method = {RequestMethod.GET})
	public ModelAndView form() {
		ModelAndView mav = new ModelAndView("form");
		StudentDTO st = new StudentDTO();
		st.setHiddenName("X1");
		mav.addObject("command", st);
		return mav;
	}
	
	@RequestMapping(value ="/add", method = {RequestMethod.POST})
	public ModelAndView add(@ModelAttribute("SpringWeb") @Validated StudentDTO st, ModelMap model, BindingResult bind) {
		ModelAndView mav = new ModelAndView("form");
		mav.addObject("command", st);
		
		if(bind.hasErrors()) {
			return mav;
		}
		
		mav.addObject("id", st.getId());
		mav.addObject("name", st.getName());
		mav.addObject("age", st.getAge());
		mav.addObject("password", st.getPassword());
		mav.addObject("adderss", st.getAdderss());
		mav.addObject("isIndian", st.isIndian());
		mav.addObject("blist", st.getBookTypes());
		mav.addObject("gender", st.getGender());
		mav.addObject("favNumber", st.getFavNumber());
		mav.addObject("country", st.getCountry());
		mav.addObject("states", st.getStates());
		mav.addObject("hiddenName", st.getHiddenName());
		return mav;
	}
	
	@ModelAttribute("getbooklist")
	public List<String> getBookList() {
		List<String> bookList = new ArrayList<String>();
		bookList.add("B 1");
		bookList.add("B 2");
		bookList.add("B 3");
		bookList.add("B 4");
		bookList.add("B 5");
		return bookList;
	}
	
	@ModelAttribute("getFavNumbers")
	public List<String> getFavList() {
		List<String> list = new ArrayList<String>();
		list.add("A1");
		list.add("A2");
		list.add("A3");
		list.add("A4");
		list.add("A5");
		return list;
	}
	
	@ModelAttribute("getCountryList")
	public Map<String, String> getCountryList() {
		Map<String, String> map = new HashMap<>();
		map.put("IN", "India");
		map.put("USA", "United States");
		map.put("GB", "British");
		map.put("FR", "France");
		map.put("GR", "Germany");
		return map;
	}

	@ModelAttribute("getStateList")
	public Map<String, String> getStateList() {
		Map<String, String> map = new HashMap<>();
		map.put("S1", "State 1");
		map.put("S2", "State 2");
		map.put("S3", "State 3");
		map.put("S4", "State 4");
		map.put("S5", "State 5");
		map.put("S6", "State 6");
		map.put("S7", "State 7");
		map.put("S8", "State 8");
		map.put("S9", "State 9");
		map.put("S10", "State 10");
		return map;
	}

	@RequestMapping("upload")
	private ModelAndView upload() {
		ModelAndView view = new ModelAndView("upload");
		FileUploadDTO dto = new FileUploadDTO();
		view.addObject("command", dto);
		return view;
	}

	@RequestMapping("uploadfile")
	private ModelAndView uploadFile(@Validated FileUploadDTO fileUpload, BindingResult bind, ModelMap map) {
		ModelAndView view = new ModelAndView("upload");
		view.addObject("command", fileUpload);
		return view;
	}
}
