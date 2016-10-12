package com.taskconfig;

/**
 * 采集字段配置
 * 
 * @author ldm
 * @Date 2016年10月11日
 */
public class TaskFieldConfig {
	private int level; // 字段所属层级
	// 字段标题
	// task.page.1.field.1.name
	private String name;
	// 字段对应的xpath
	// task.page.1.field.1.XPath
	private String xPath;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getxPath() {
		return xPath;
	}

	public void setxPath(String xPath) {
		this.xPath = xPath;
	}

}
