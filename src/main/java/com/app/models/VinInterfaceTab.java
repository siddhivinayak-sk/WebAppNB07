package com.app.models;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

@Entity
@Table(name = "VinInterfaceTab")
public class VinInterfaceTab implements Serializable {
	final private static long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tid")
	private String id;
	
	@Column(name = "tmodule")
    private String module;
	
	@Column(name = "tclass")
    private String className;
	
	@Column(name = "ttype")
    private String type;
	
	@Column(name = "tstatus")
    private String status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "tcreatedat")
    private Date tcrtdate;
	
	@Column(name = "tlocation")
    private String location;
	
	@Column(name = "ttries")
    private Integer tries;
	
	@Column(name = "texception")
    private String exception;
	
	@Column(name = "tcrtby")
    private String crtby;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "tcrtdate")
    private Date crtdate;

	@Column(name = "tupdby")
    private String updby;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "tupddate")
    private Date upddate;
	
	@Column(name = "tsender")
    private String sender;
	
	@Column(name = "tapikey")
    private String apikey;
	
	@Column(name = "tapiowner")
    private String apiowner;
	
	@Lob
	@Column(name = "tdata")
    private StringBuilder data;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTcrtdate() {
		return tcrtdate;
	}

	public void setTcrtdate(Date tcrtdate) {
		this.tcrtdate = tcrtdate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getTries() {
		return tries;
	}

	public void setTries(Integer tries) {
		this.tries = tries;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getCrtby() {
		return crtby;
	}

	public void setCrtby(String crtby) {
		this.crtby = crtby;
	}

	public Date getCrtdate() {
		return crtdate;
	}

	public void setCrtdate(Date crtdate) {
		this.crtdate = crtdate;
	}

	public String getUpdby() {
		return updby;
	}

	public void setUpdby(String updby) {
		this.updby = updby;
	}

	public Date getUpddate() {
		return upddate;
	}

	public void setUpddate(Date upddate) {
		this.upddate = upddate;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

	public String getApiowner() {
		return apiowner;
	}

	public void setApiowner(String apiowner) {
		this.apiowner = apiowner;
	}

	public StringBuilder getData() {
		return data;
	}

	public void setData(StringBuilder data) {
		this.data = data;
	}

	public <T> T getObject(Class<T> c) {
		T t = null;
		if(null != className && !className.isEmpty() && null != data) {
			t = new JSONDeserializer<T>().deserialize(data.toString(), c);
		}
		return t;
	}

	public <T> void setObject(T object) {
		if(null != className && !className.isEmpty()) {
			data = new StringBuilder();
			new JSONSerializer().deepSerialize(object, data);
		}
	}

	
}
