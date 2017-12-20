package org.victorynox.rql.exception;

/**
 * @author victorynox
 * @version 0.1
 */
public class SyntaxErrorException extends ParserException {

	public SyntaxErrorException(String message) {
		super(message);
	}

	public SyntaxErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public SyntaxErrorException(Throwable cause) {
		super(cause);
	}
}
