package com.ericsson.NetworkManager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
// @Stateless

@Table(name = "users")
public class User {

	@Id
	
	@Column(name = "user_name")
	private String name;

	@Column(name = "user_password")
	private String password;

	@Column(name = "user_type")
	private int type;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public int getType() {
		return type;
	}

	public void setType(final int type) {
		this.type = type;
	}

}
