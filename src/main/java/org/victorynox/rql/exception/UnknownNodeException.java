package org.victorynox.rql.exception;

/**
 * @author victorynox
 * @version 0.1
 */
public class UnknownNodeException extends ParserException {
	public UnknownNodeException(String message) {
		super(message);
	}

	public UnknownNodeException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnknownNodeException(Throwable cause) {
		super(cause);
	}
}
