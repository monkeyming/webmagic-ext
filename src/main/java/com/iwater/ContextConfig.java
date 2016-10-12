package com.iwater;

import com.utils.ConfigUtils;

/**
 * 全局配置
 * 
 * @author ldm
 * @Date 2016年10月10日
 */
public class ContextConfig {
	private static ContextConfig context;

	/**
	 * 全局单一
	 * 
	 * @author ldm
	 * @Date 2016年10月10日
	 */
	private ContextConfig() {
		this.taskDir = ConfigUtils.readConfig("task.dir");
	}

	public static ContextConfig getInstance() {
		if (context == null) {
			context = new ContextConfig();
		}
		return context;
	}

	private String taskDir;

	public String getTaskDir() {
		return taskDir;
	}

	public void setTaskDir(String taskDir) {
		this.taskDir = taskDir;
	}

}
