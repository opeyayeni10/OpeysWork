package com.ait.transactions;

import com.ait.exception.PayPalException;


public interface PayPalFacade {

	void sendAdvice(String description, double amount, String payPalId)throws PayPalException;

}
