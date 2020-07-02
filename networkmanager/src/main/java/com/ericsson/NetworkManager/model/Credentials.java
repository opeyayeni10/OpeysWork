package com.ericsson.NetworkManager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Credentials implements Serializable{
	@Id
	@Column(name = "user_name")
	private String name;
	@Column(name = "user_password")
	private String password;
	public String getName() {
		return name;
	}
	public void setUserID(final String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(final String password) {
		this.password = password;
	}	
}
