package com.github.hiwepy.httputils.handler;

import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.StatusLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.hiwepy.httputils.exception.HttpResponseException;

/**
 *  在JSP/Servlet编程中response.sendRedirect方法就是使用HTTP协议中的重定向机制。
 *      它与JSP中的jsp:forward的区别在于后者是在服务器中实现页面的跳转，
 *      也就是说应用容器加载了所要跳转的页面的内容并返回给客户端；
 *      而前者是返回一个状态码，这些状态码的可能值见下表，然后客户端读取需要跳转到的页面的URL并重新加载新的页面。
 *      就是这样一个过程，所以我们编程的时候就要通过HttpMethod
 *      .getStatusCode()方法判断返回值是否为下表中的某个值来判断是否需要跳转。
 *      如果已经确认需要进行页面跳转了，那么可以通过读取HTTP头中的location属性来获取新的地址。
 */
public class RedirectResponseHandler implements ResponseHandler<String> {

	protected static Logger LOG = LoggerFactory.getLogger(RedirectResponseHandler.class);

	@Override
	public void preHandle(HttpClient httpclient) {

	}

	@Override
	public String handleResponse(HttpMethodBase httpMethod) throws IOException {
		StatusLine statusLine = httpMethod.getStatusLine();
		// 检查是否重定向
		int statuscode = statusLine.getStatusCode();
		if ((statuscode == HttpStatus.SC_MOVED_TEMPORARILY)
				|| (statuscode == HttpStatus.SC_MOVED_PERMANENTLY)
				|| (statuscode == HttpStatus.SC_SEE_OTHER)
				|| (statuscode == HttpStatus.SC_TEMPORARY_REDIRECT)) {
			// 读取新的 URL 地址
			Header header = httpMethod.getResponseHeader("location");
			if (header != null) {
				// 从头中取出转向的地址
				String redirectURI = header.getValue();
				if ((redirectURI == null) || (redirectURI.equals(""))) {
					redirectURI = "/";
				}
				LOG.debug("Redirect:" + redirectURI);
				return redirectURI;
			} else {
				throw new HttpResponseException("Invalid redirect .");
			}
		} else {
			throw new HttpResponseException(statusLine.getStatusCode(),statusLine.getReasonPhrase());
		}
	}
}