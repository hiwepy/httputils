package com.github.vindell.httputils.exception;

import java.io.IOException;

@SuppressWarnings("serial")
public class HttpResponseException extends IOException {
	
	private int statusCode = 200;

	public HttpResponseException(String message) {
		super(message);
	}
	
	public HttpResponseException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public HttpResponseException(final int statusCode, final String s) {
		super(s);
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return this.statusCode;
	}

}
