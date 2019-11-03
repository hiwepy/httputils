package com.github.vindell.httputils;

import java.util.Properties;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

@SuppressWarnings("serial")
public class HttpConnectionConfig extends HttpConnectionManagerParams {
	
	public static final String TIMEOUT_INTERVAL = "http.timeout.interval"; 
	//定时清除失效连接心跳线程执行周期(单位毫秒)，默认5000
	public static final int  DEFAULT_TIMEOUT_INTERVAL = 5000;
	
	public HttpConnectionConfig(Properties conf) {
		// 设置httpclient是否使用NoDelay策略;默认 true
		this.setTcpNoDelay(StringUtils.getSafeBoolean(conf.getProperty(TCP_NODELAY), "1"));
		// 通过网络与服务器建立连接的超时时间。Httpclient包中通过一个异步线程去创建与服务器的socket连接，这就是该socket连接的超时时间(单位毫秒)，默认30000
		this.setConnectionTimeout(StringUtils.getSafeInt(conf.getProperty(CONNECTION_TIMEOUT), "30000"));
		// 连接读取数据超时时间(单位毫秒)，默认60000
		this.setSoTimeout(StringUtils.getSafeInt(conf.getProperty(SO_TIMEOUT), "60000"));
		// 每个HOST的最大连接数量
		this.setDefaultMaxConnectionsPerHost(StringUtils.getSafeInt(conf.getProperty(MAX_HOST_CONNECTIONS), "20"));
		// 连接池的最大连接数
		this.setMaxTotalConnections(StringUtils.getSafeInt(conf.getProperty(MAX_TOTAL_CONNECTIONS), "60"));
		//socket发送数据的缓冲大小
		this.setSendBufferSize(StringUtils.getSafeInt(conf.getProperty(SO_SNDBUF), "1048576"));
		//socket接收数据的缓冲大小
		this.setReceiveBufferSize(StringUtils.getSafeInt(conf.getProperty(SO_RCVBUF), "1048576"));
		//检查连接是否有效的心跳周期
		this.setTimeoutInterval(StringUtils.getSafeInt(conf.getProperty(TIMEOUT_INTERVAL), "5000"));
		// 使用系统提供的默认的恢复策略
		this.setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
	}
	
	public static HttpConnectionConfig initConfig(Properties conf,HttpConnectionConfig config){
		// 设置httpclient是否使用NoDelay策略;默认 true
		config.setTcpNoDelay(StringUtils.getSafeBoolean(conf.getProperty(TCP_NODELAY), "1"));
		// 通过网络与服务器建立连接的超时时间。Httpclient包中通过一个异步线程去创建与服务器的socket连接，这就是该socket连接的超时时间(单位毫秒)，默认30000
		config.setConnectionTimeout(StringUtils.getSafeInt(conf.getProperty(CONNECTION_TIMEOUT), "30000"));
		// 连接读取数据超时时间(单位毫秒)，默认60000
		config.setSoTimeout(StringUtils.getSafeInt(conf.getProperty(SO_TIMEOUT), "60000"));
		// 每个HOST的最大连接数量
		config.setDefaultMaxConnectionsPerHost(StringUtils.getSafeInt(conf.getProperty(MAX_HOST_CONNECTIONS), "20"));
		// 连接池的最大连接数
		config.setMaxTotalConnections(StringUtils.getSafeInt(conf.getProperty(MAX_TOTAL_CONNECTIONS), "60"));
		//socket发送数据的缓冲大小
		config.setSendBufferSize(StringUtils.getSafeInt(conf.getProperty(SO_SNDBUF), "1048576"));
		//socket接收数据的缓冲大小
		config.setReceiveBufferSize(StringUtils.getSafeInt(conf.getProperty(SO_RCVBUF), "1048576"));
		//检查连接是否有效的心跳周期
		config.setTimeoutInterval(StringUtils.getSafeInt(conf.getProperty(TIMEOUT_INTERVAL), "5000"));
		// 使用系统提供的默认的恢复策略
		config.setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
		return config;
	}
	 
	/**
	 * @return the timeoutInterval
	 */
	public int getTimeoutInterval() {
		return getIntParameter(TIMEOUT_INTERVAL,DEFAULT_TIMEOUT_INTERVAL);
	}
	
	/**
	 * @param timeoutInterval the timeoutInterval to set
	 */
	public void setTimeoutInterval(int timeoutInterval) {
		 setIntParameter(TIMEOUT_INTERVAL,timeoutInterval);
	}
	
	
}
