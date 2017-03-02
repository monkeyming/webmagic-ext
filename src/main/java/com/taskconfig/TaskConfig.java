package com.taskconfig;

import java.util.HashMap;
import java.util.Map;

/**
 * 任务配置
 * 
 * @author ldm
 * @Date 2016年10月11日
 */
public class TaskConfig {
	// 任务名称
	// 读取 task.name
	private String name;
	// 任务描述
	// 读取task.desc
	private String desc;
	// 任务起始位置
	// 读取 task.url
	private String startUrl;
	// 层级页面
	public Map<Integer, TaskPageConfig> pages = null;

	/**
	 * 设置所有层级页面配置
	 * 
	 * @author ldm
	 * @Date 2016年10月11日
	 * @param pages
	 */
	public void setPages(Map<Integer, TaskPageConfig> pages) {
		this.pages = pages;
	}

	/**
	 * 添加单个层级页面
	 * 
	 * @author ldm
	 * @Date 2016年10月11日
	 * @param level
	 * @param page
	 */
	public void addPage(int level, TaskPageConfig page) {
		if (pages == null) {
			pages = new HashMap<Integer, TaskPageConfig>();
		}
		pages.put(level, page);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getStartUrl() {
		return startUrl;
	}

	public void setStartUrl(String startUrl) {
		this.startUrl = startUrl;
	}

}
