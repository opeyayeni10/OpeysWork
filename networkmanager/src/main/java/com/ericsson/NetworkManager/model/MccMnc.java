package com.ericsson.NetworkManager.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "mcc_mnc")
public class MccMnc {

	@EmbeddedId
	private MccMncPK mccMncPKId;

	@Column(name = "country")
	private String country;

	@Column(name = "operator")
	private String operator;

	public MccMncPK getId() {
		return mccMncPKId;
	}

	public void setId(final MccMncPK mccMncPKId) {
		this.mccMncPKId = mccMncPKId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(final String operator) {
		this.operator = operator;
	}

}
