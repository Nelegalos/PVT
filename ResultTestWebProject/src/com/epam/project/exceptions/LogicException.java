package com.epam.project.exceptions;

public class LogicException extends Exception {
	private static final long serialVersionUID = 1L;

	public LogicException() {
	}

	public LogicException(String message) {
		super(message);
	}

	public LogicException(String arg0, Exception arg1) {
		super(arg0, arg1);
	}

	public LogicException(Exception arg0) {
		super(arg0);
	}

}
