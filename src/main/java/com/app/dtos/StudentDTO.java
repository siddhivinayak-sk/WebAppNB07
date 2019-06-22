package com.app.dtos;

import java.io.Serializable;

public class StudentDTO implements Serializable {
	final private static long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private Integer age;
	private String password;
	private String adderss;
	private boolean isIndian;
	private String[] bookTypes;
	private String gender;
	private String favNumber;
	private String country;
	private String[] states;
	private String hiddenName;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAdderss() {
		return adderss;
	}
	public void setAdderss(String adderss) {
		this.adderss = adderss;
	}
	public boolean isIndian() {
		return isIndian;
	}
	public boolean getIsIndian() {
		return isIndian;
	}
	public void setIndian(boolean isIndian) {
		this.isIndian = isIndian;
	}
	public String[] getBookTypes() {
		return bookTypes;
	}
	public void setBookTypes(String[] bookTypes) {
		this.bookTypes = bookTypes;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getFavNumber() {
		return favNumber;
	}
	public void setFavNumber(String favNumber) {
		this.favNumber = favNumber;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String[] getStates() {
		return states;
	}
	public void setStates(String[] states) {
		this.states = states;
	}
	public String getHiddenName() {
		return hiddenName;
	}
	public void setHiddenName(String hiddenName) {
		this.hiddenName = hiddenName;
	}
	
	
}
