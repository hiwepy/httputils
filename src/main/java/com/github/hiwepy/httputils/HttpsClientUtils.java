package com.github.hiwepy.httputils;

import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.SSLProtocolSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("deprecation")
public abstract class HttpsClientUtils extends HttpClientUtils {

	protected static Logger LOG = LoggerFactory.getLogger(HttpsClientUtils.class);
	
	static {

		try {
			
			Protocol easyhttps = new Protocol("https", new SSLProtocolSocketFactory(), 443);  
		    Protocol.registerProtocol("https", easyhttps);   
	        
		} catch (Exception e) {
			LOG.error(e.getLocalizedMessage());
		}
	}

}
