package com.app.models;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "MYAPPPERMISSIONS", catalog = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MyappPermissions.findAll", query = "SELECT c FROM MyappPermissions c"),
    @NamedQuery(name = "MyappPermissions.findByMyappPermissionsId", query = "SELECT c FROM MyappPermissions c WHERE c.id = :id"),
    @NamedQuery(name = "MyappPermissions.findByMyappPermissionsRoleId", query = "SELECT c FROM MyappPermissions c WHERE c.roleId = :roleId"),
    @NamedQuery(name = "MyappPermissions.findByMyappPermissionsPermissions", query = "SELECT c FROM MyappPermissions c WHERE c.permissions = :permissions")
})
public class MyappPermissions implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "ROLE_ID")
    private Integer roleId;
    @Column(name = "PERMISSIONS", length = 30)
    private String permissions;

    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getPermissions() {
		return permissions;
	}
	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
}
