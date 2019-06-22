package com.app.models;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "MYAPPUSER", catalog = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MyappUser.findAll", query = "SELECT c FROM MyappUser c"),
    @NamedQuery(name = "MyappUser.findByMyappUserId", query = "SELECT c FROM MyappUser c WHERE c.userId = :userId"),
    @NamedQuery(name = "MyappUser.findByMyappLoginName", query = "SELECT c FROM MyappUser c WHERE c.loginName = :userName"),
})

public class MyappUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "USER_ID", nullable = false)
    private Integer userId;
    @Column(name = "LOGIN_NAME", length = 30)
    private String loginName;
    @Column(name = "LOGIN_PASSWORD", length = 30)
    private String loginPassword;
    @Column(name = "ENABLED", nullable = false)
    private Integer enabled;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ROLE", referencedColumnName = "ROLE_ID")
    private MyappRoles role;
    
    
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public MyappRoles getRole() {
		return role;
	}
	public void setRole(MyappRoles role) {
		this.role = role;
	}
}
