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
@Table(name = "failure_class")
public class FailureClass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int failureClassId;
	
	@Column(name = "failure_class")
	private String failureClass;

	@Column(name = "description")
	private String description;
	
	public int getId() {
		return failureClassId;
	}

	public void setId(final int failureClassId) {
		this.failureClassId = failureClassId;
	}

	public String getFailureClass() {
		return failureClass;
	}

	public void setFailureClass(final String failureClass) {
		this.failureClass = failureClass;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

}
