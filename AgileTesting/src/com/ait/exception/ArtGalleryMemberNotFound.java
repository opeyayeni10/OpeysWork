
package com.ait.exception;

public class ArtGalleryMemberNotFound extends ArtGalleryException {

	private static final long serialVersionUID = 334051992916748022L;

	public ArtGalleryMemberNotFound(final String id) {
		super("Developer Id not found : "+ id);
	}

}
