package com.github.vindell.httputils;

import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.SSLProtocolSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @className	： HttpsClientUtils
 * @description	： TODO(描述这个类的作用)
 * @author 		： <a href="https://github.com/vindell">vindell</a>
 * @date		： 2017年6月13日 下午9:14:17
 * @version 	V1.0
 */
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
