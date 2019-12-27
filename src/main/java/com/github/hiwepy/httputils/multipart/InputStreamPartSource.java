package com.github.hiwepy.httputils.multipart;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.methods.multipart.PartSource;

/**
 * 
 * @className	： InputStreamPartSource
 * @description	： TODO(描述这个类的作用)
 * @author 		： <a href="https://github.com/hiwepy">hiwepy</a>
 * @date		： 2017年9月12日 下午10:40:26
 * @version 	V1.0
 */
public class InputStreamPartSource implements PartSource {


    /** Stream part input. */
    private InputStream input = null;

    /** File part file name. */
    private String fileName = null;
 
	public InputStreamPartSource( String fileName , InputStream input) {
		super();
		this.fileName = fileName;
		this.input = input;
	}

	@Override
	public long getLength() {
		if (this.input != null) {
            try {
				return this.input.available();
			} catch (IOException e) {
				//	e.printStackTrace();
				return 0;
			}
        } else {
            return 0;
        }
	}

	@Override
	public String getFileName() {
		 return (fileName == null) ? "noname" : fileName;
	}

	@Override
	public InputStream createInputStream() throws IOException {
		return input;
	}

}
