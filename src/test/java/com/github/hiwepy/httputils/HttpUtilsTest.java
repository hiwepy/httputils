/*
 * Copyright (c) 2018 (https://github.com/hiwepy).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.github.hiwepy.httputils;

import java.io.File;

import org.junit.Test;

import com.github.hiwepy.httputils.handler.FileResponseHandler;
import com.github.hiwepy.httputils.handler.PlainTextResponseHandler;
import com.github.hiwepy.httputils.handler.ResponseHandler;

import junit.framework.TestCase;

public class HttpUtilsTest extends TestCase{

	protected ResponseHandler<String> textHandler = new PlainTextResponseHandler();
	protected ResponseHandler<File> fileHandler = new FileResponseHandler(new File("d://baidu.html"));
	
	@Test
	public void testHttpUtil()  {
		
		try {
			String baseURL = "http://www.baidu.com ";
			
			//String resultHtml = HttpClientUtils.httpRequestWithGet(baseURL, textHandler);
			
			//System.out.println(resultHtml);
			
			File resultFile = HttpClientUtils.httpRequestWithGet(baseURL, fileHandler);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
}
