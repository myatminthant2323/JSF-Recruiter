package com.mmit.config;

import javax.ejb.ApplicationException;

@ApplicationException
public class AppException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public AppException(String message) {
		super(message);
	}
	

}
