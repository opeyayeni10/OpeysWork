package com.ait.exception;

public class MembershipDAOException extends ArtGalleryException {

	private static final long serialVersionUID = 334051992916748022L;

	public MembershipDAOException(final String artistIdentity) {
		super("Error getting membership status: "+ artistIdentity);
	}

}
