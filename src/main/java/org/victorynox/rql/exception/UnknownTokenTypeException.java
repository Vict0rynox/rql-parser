package org.victorynox.rql.exception;

/**
 * @author victorynox
 * @version 0.1
 */
public class UnknownTokenTypeException extends ParserException {

	public UnknownTokenTypeException(String message) {
		super(message);
	}

	public UnknownTokenTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnknownTokenTypeException(Throwable cause) {
		super(cause);
	}
}
