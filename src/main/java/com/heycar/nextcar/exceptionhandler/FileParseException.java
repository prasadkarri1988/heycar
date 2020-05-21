package com.heycar.nextcar.exceptionhandler;

public class FileParseException extends RuntimeException {
	
	private static final long serialVersionUID = -4278057746617473364L;
	
	public FileParseException() {
		super();
	}
	public FileParseException(String errorDescription) {
		super(errorDescription);
	}

}
