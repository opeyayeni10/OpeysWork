package com.ericsson.NetworkManager.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class InvalidEvent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int invalidEventId;
	private Date date_time;
	private String event_id;
	private String failure_class;
	private int ue_type, market; 
	private int operator; 
	private int cell_id; 
	private int duration;
	private String cause_code; 
	private String ne_version;
	private String imsi;
	private String hier3_id;
	private String hier32_id; 
	private String hier321_id;
	public int getId() {
		return invalidEventId;
	}
	public void setId(final int invalidEventId) {
		this.invalidEventId = invalidEventId;
	}
	public Date getDate_time() {
		return date_time;
	}
	public void setDate_time(final Date date_time) {
		this.date_time = date_time;
	}
	public String getEvent_id() {
		return event_id;
	}
	public void setEvent_id(final String event_id) {
		this.event_id = event_id;
	}
	public String getFailure_class() {
		return failure_class;
	}
	public void setFailure_class(final String failure_class) {
		this.failure_class = failure_class;
	}
	public int getUe_type() {
		return ue_type;
	}
	public void setUe_type(final int ue_type) {
		this.ue_type = ue_type;
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
	public int getCell_id() {
		return cell_id;
	}
	public void setCell_id(final int cell_id) {
		this.cell_id = cell_id;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(final int duration) {
		this.duration = duration;
	}
	public String getCause_code() {
		return cause_code;
	}
	public void setCause_code(final String cause_code) {
		this.cause_code = cause_code;
	}
	public String getNe_version() {
		return ne_version;
	}
	public void setNe_version(final String ne_version) {
		this.ne_version = ne_version;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(final String imsi) {
		this.imsi = imsi;
	}
	public String getHier3_id() {
		return hier3_id;
	}
	public void setHier3_id(final String hier3_id) {
		this.hier3_id = hier3_id;
	}
	public String getHier32_id() {
		return hier32_id;
	}
	public void setHier32_id(final String hier32_id) {
		this.hier32_id = hier32_id;
	}
	public String getHier321_id() {
		return hier321_id;
	}
	public void setHier321_id(final String hier321_id) {
		this.hier321_id = hier321_id;
	}
	
}
