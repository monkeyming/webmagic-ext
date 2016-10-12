package com.iwater.ext;

import java.util.Properties;

import us.codecraft.webmagic.Request;

/**
 * 扩展请求对象
 * 
 * @author ldm
 * @Date 2016年10月10日
 */
public class ExtRequest extends Request {
	private Properties properties;

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}
