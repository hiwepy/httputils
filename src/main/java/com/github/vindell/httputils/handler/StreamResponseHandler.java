package com.github.vindell.httputils.handler;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.StatusLine;

import com.github.vindell.httputils.exception.HttpResponseException;

/**
 * 
 * @className	： StreamResponseHandler
 * @description	： http请求响应处理：返回ByteArrayInputStream对象
 * @author 		： <a href="https://github.com/vindell">vindell</a>
 * @date		： 2017年6月13日 下午9:14:51
 * @version 	V1.0
 */
public class StreamResponseHandler implements ResponseHandler<ByteArrayInputStream> {

	@Override
	public void preHandle(HttpClient httpclient) {
		
	}

	@Override
	public ByteArrayInputStream handleResponse(HttpMethodBase httpMethod) throws IOException {
		StatusLine statusLine = httpMethod.getStatusLine();
		int status = statusLine.getStatusCode();
		if (status >= HttpStatus.SC_OK && status < HttpStatus.SC_MULTIPLE_CHOICES) {
			try {
				// 响应内容
				return new ByteArrayInputStream(httpMethod.getResponseBody());
			} finally {
				
			}
		} else {
			throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
		}
	}

}
