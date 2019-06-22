package com.app.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
@Table(name = "MYAPPROLES", catalog = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MyappRoles.findAll", query = "SELECT c FROM MyappRoles c"),
    @NamedQuery(name = "MyappRoles.findByMyappRolesId", query = "SELECT c FROM MyappRoles c WHERE c.roleId = :roleId")
})
public class MyappRoles implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "ROLE_ID", nullable = false)
    private Integer roleId;
    @Column(name = "ROLE_NAME", length = 30)
    private String roleName;
    @OneToMany(mappedBy="roleId", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@Fetch(value = FetchMode.SUBSELECT)
    private List<MyappPermissions> myappPermissions;
    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<MyappUser> users;
    

    public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<MyappPermissions> getMyappPermissions() {
		return myappPermissions;
	}
	public void setMyappPermissions(List<MyappPermissions> myappPermissions) {
		this.myappPermissions = myappPermissions;
	}
}