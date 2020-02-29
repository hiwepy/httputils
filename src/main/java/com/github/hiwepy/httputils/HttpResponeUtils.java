package com.github.hiwepy.httputils;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpMethodBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class HttpResponeUtils{
	
	protected static Logger LOG = LoggerFactory.getLogger(HttpResponeUtils.class);

	public static String getContentType(HttpMethodBase httpMethod) {
		Header header = httpMethod.getResponseHeader(HttpHeaders.CONTENT_TYPE);
		/*HeaderElement[] elements = header.getElements();
		for (HeaderElement elem : elements) {
			LOG.debug(elem.getName() + " = " + elem.getValue());
			if ("gzip".equalsIgnoreCase(elem.getName())) {
				contentType = elem.getValue();
				break;
			}
		}*/
		return header.getValue();
	}
}
