package com.ericsson.NetworkManager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "user_equipment")
public class UserEquipment {
	
	@Id
	@Column(name = "tac")
	private int userEquipmentID;
	
	@Column(name = "marketing_name")
	private String marketingName;
	
	@Column(name = "manufacturer")
	private String manufacturer;
	
	@Column(name = "access_capability")
	private String accessCapability;
	
	@Column(name = "model")
	private String model;
	
	@Column(name = "vendor_name")
	private String vendorName;
	
	@Column(name = "ue_type")
	private String userEquipmanetType;
	
	@Column(name = "os")
	private String operatingSystem;
	
	@Column(name = "input_mode")
	private String inputMode;

	
	public int getUserEquipmentID() {
		return userEquipmentID;
	}

	public void setUserEquipmentID(final int userEquipmentID) {
		this.userEquipmentID = userEquipmentID;
	}

	public String getMarketingName() {
		return marketingName;
	}

	public void setMarketingName(final String marketingName) {
		this.marketingName = marketingName;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(final String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getAccessCapability() {
		return accessCapability;
	}

	public void setAccessCapability(final String accessCapability) {
		this.accessCapability = accessCapability;
	}

	public String getModel() {
		return model;
	}

	public void setModel(final String model) {
		this.model = model;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(final String vendorName) {
		this.vendorName = vendorName;
	}

	public String getUserEquipmanetType() {
		return userEquipmanetType;
	}

	public void setUserEquipmanetType(final String userEquipmanetType) {
		this.userEquipmanetType = userEquipmanetType;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(final String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getInputMode() {
		return inputMode;
	}

	public void setInputMode(final String inputMode) {
		this.inputMode = inputMode;
	}
}