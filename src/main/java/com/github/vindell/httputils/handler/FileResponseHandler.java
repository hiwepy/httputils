package com.github.vindell.httputils.handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.StatusLine;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Validate;

import com.github.vindell.httputils.exception.HttpResponseException;

public class FileResponseHandler implements ResponseHandler<File> {

	private File destFile;
	
	public FileResponseHandler( File destFile) {
		Validate.notNull(destFile, "destFile is null ");
		this.destFile = destFile;
	}

	@Override
	public void preHandle(HttpClient httpclient) {
		
	}
	
	@Override
	public File handleResponse(HttpMethodBase httpMethod) throws IOException {
		StatusLine statusLine = httpMethod.getStatusLine();
		int status = statusLine.getStatusCode();
		if (status >= HttpStatus.SC_OK && status < HttpStatus.SC_MULTIPLE_CHOICES) {
			
			InputStream input = null;
			FileOutputStream output = null;
			// 先存为临时文件，等全部下完再改回原来的文件名
			File storeFile = new File(destFile.getParent() , destFile.getName()  + ".tmp"); 
			try {
				output = new FileOutputStream(storeFile);
				input = httpMethod.getResponseBodyAsStream();
				IOUtils.copy(input, output);
			} finally {
				// 释放资源
				IOUtils.closeQuietly(input);
				IOUtils.closeQuietly(output);
			}
			storeFile.renameTo(destFile);
			return destFile;
		} else {
			throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
		}
	}

}
