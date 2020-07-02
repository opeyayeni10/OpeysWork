package com.ait.dto;

public class MembershipStatusDto {

	private double deductable;
	private String payPalId;

	public double getDeductable() {
		return deductable;
	}

	public void setDeductable(double deductable) {
		this.deductable = deductable;
	}
	
	public String getPayPalId(){
		return payPalId;
	}
	
	public void setPayPalId(String payPalId) {
		this.payPalId = payPalId;
	}
}
