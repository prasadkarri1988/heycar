package com.heycar.nextcar.exceptionhandler;

public class ApplicationProcessException extends  RuntimeException  {

	private static final long serialVersionUID = -2795964680203572396L;

	public ApplicationProcessException(String exception) {
	    super(exception);
	  }
}
