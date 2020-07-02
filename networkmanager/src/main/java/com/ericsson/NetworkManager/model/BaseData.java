package com.ericsson.NetworkManager.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name="base_data")
public class BaseData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int baseDataId;

	@Column(name = "date_time")
	private Date dateTime;
	
	@Column(name = "event_id")
	private String eventId;

	@Column(name = "failure_class")
	private String failureClass;

	@Column(name = "ue_type")
	private int UEType;

	@Column(name = "market")
	private int market;

	@Column(name = "operator")
	private int operator;
	
	@Column(name = "cell_id")
	private int cellId;
	
	@Column(name = "duration")
	private int duration;
	
	@Column(name = "cause_code")
	private String causeCode;
	
	@Column(name = "ne_version")
	private String NEVersion;
	
	@Column(name = "imsi")
	private String imsi;
	
	@Column(name = "hier3_id")
	private String hier3Id;
	
	@Column(name = "hier32_id")
	private String hier32Id;
	
	@Column(name = "hier321_id")
	private String hier321Id;

	public int getBaseDataId() {
		return baseDataId;
	}

	public void setBaseDataId(final int baseDataId) {
		this.baseDataId = baseDataId;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(final Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(final String eventId) {
		this.eventId = eventId;
	}

	public String getFailureClass() {
		return failureClass;
	}

	public void setFailureClass(final String failureClass) {
		this.failureClass = failureClass;
	}

	public int getUEType() {
		return UEType;
	}

	public void setUEType(final int uEType) {
		UEType = uEType;
	}

	public int getMarket() {
		return market;
	}

	public void setMarket(final int market) {
		this.market = market;
	}

	public int getOperator() {
		return operator;
	}

	public void setOperator(final int operator) {
		this.operator = operator;
	}

	public int getCellId() {
		return cellId;
	}

	public void setCellId(final int cellId) {
		this.cellId = cellId;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(final int duration) {
		this.duration = duration;
	}

	public String getCauseCode() {
		return causeCode;
	}

	public void setCauseCode(final String causeCode) {
		this.causeCode = causeCode;
	}

	public String getNEVersion() {
		return NEVersion;
	}

	public void setNEVersion(final String nEVersion) {
		NEVersion = nEVersion;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(final String imsi) {
		this.imsi = imsi;
	}

	public String getHier3Id() {
		return hier3Id;
	}

	public void setHier3Id(final String hier3Id) {
		this.hier3Id = hier3Id;
	}

	public String getHier32Id() {
		return hier32Id;
	}

	public void setHier32Id(final String hier32Id) {
		this.hier32Id = hier32Id;
	}

	public String getHier321Id() {
		return hier321Id;
	}

	public void setHier321Id(final String hier321Id) {
		this.hier321Id = hier321Id;
	}

	


}