package com.ait.exception;

public class PayPalException extends ArtGalleryException {

	private static final long serialVersionUID = 334051992916748022L;

	public PayPalException(final String id) {
		super("Problem connecting to Paypal : "+ id);
	}

}
