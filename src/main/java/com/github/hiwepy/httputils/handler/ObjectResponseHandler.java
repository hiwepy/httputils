package com.github.hiwepy.httputils.handler;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.StatusLine;

import com.github.hiwepy.httputils.exception.HttpResponseException;
import com.thoughtworks.xstream.XStream;

public class ObjectResponseHandler implements ResponseHandler<Object> {

	protected XStream xstream = new XStream();
	
	@Override
	public void preHandle(HttpClient httpclient) {
		
	}
	
	@Override
	public Object handleResponse(HttpMethodBase httpMethod) throws IOException {
		StatusLine statusLine = httpMethod.getStatusLine();
		int status = statusLine.getStatusCode();
		if (status >= HttpStatus.SC_OK && status < HttpStatus.SC_MULTIPLE_CHOICES) {
			try(InputStream input = httpMethod.getResponseBodyAsStream();) {
				return xstream.fromXML(input);
			}
		} else {
			throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
		}
	}
 
}
