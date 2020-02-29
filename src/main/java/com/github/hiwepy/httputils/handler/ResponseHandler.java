package com.github.hiwepy.httputils.handler;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;

/**
 * Handler that encapsulates the process from a {@link HttpClient}.
 */
public interface ResponseHandler<T> {

	/*
	 * 对HttpClient进行预处理
	 */
	void preHandle(HttpClient httpclient);
	
    T handleResponse(HttpMethodBase httpMethod) throws IOException;

}
