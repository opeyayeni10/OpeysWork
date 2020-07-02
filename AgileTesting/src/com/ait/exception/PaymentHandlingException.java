package com.ait.exception;

public class PaymentHandlingException extends ArtGalleryException {

	private static final long serialVersionUID = 334051992916748022L;

	public PaymentHandlingException(final String id) {
		super("Problem connecting to Paypal : "+ id);
	}

}
