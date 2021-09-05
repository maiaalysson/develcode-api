package com.gabrielmaia.develcode.domain.exception;

public class PictureNotFound extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PictureNotFound(String message) {
		super(message);
	}

}
