package com.taskconfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 具体页面的配置
 * 
 * @author ldm
 * @Date 2016年10月11日
 */
public class TaskPageConfig {
	// 页面层级
	// 至少从1开始
	// 从key中提取
	private int pageLevel;
	// 是否是最终页面
	// 如果是最终页面，不提取网址，直接提取字段
	// 读取配置如：task.page.1.islast
	private boolean isLast;
	// 提取网址的正则表达式
	// task.page.1.urlPattern
	private String urlPattern;
	// 起始xpath
	// task.page.1.startXPath
	private String startXPath;
	// 是否下载
	// task.page.1.download
	private boolean isDownload;
	// 下载后缀判断
	// task.page.1.downloadPattern
	private String downloadPattern;
	// 需要采集的字段
	public Map<Integer, List<TaskFieldConfig>> fields = null;

	/**
	 * 设置所有采集字段
	 * 
	 * @author ldm
	 * @Date 2016年10月11日
	 * @param fields
	 */
	public void setFields(Map<Integer, List<TaskFieldConfig>> fields) {
		this.fields = fields;
	}

	/**
	 * 添加采集字段
	 * 
	 * @author ldm
	 * @Date 2016年10月11日
	 * @param field
	 */
	public void setLevelFields(int level, List<TaskFieldConfig> field) {
		if (fields == null) {
			fields = new HashMap<Integer, List<TaskFieldConfig>>();
		}
		fields.put(level, field);
	}

	/**
	 * 添加采集字段
	 * 
	 * @author ldm
	 * @Date 2016年10月11日
	 * @param field
	 */
	public void addField(int level, TaskFieldConfig field) {
		if (fields == null) {
			fields = new HashMap<Integer, List<TaskFieldConfig>>();
		}
		List<TaskFieldConfig> list = fields.get(level);
		if (list == null) {
			setLevelFields(level, new ArrayList<TaskFieldConfig>());
		}

		fields.get(level).add(field);
	}

	public int getPageLevel() {
		return pageLevel;
	}

	public void setPageLevel(int pageLevel) {
		this.pageLevel = pageLevel;
	}

	public boolean isLast() {
		return isLast;
	}

	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}

	public String getUrlPattern() {
		return urlPattern;
	}

	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}

	public String getStartXPath() {
		return startXPath;
	}

	public void setStartXPath(String startXPath) {
		this.startXPath = startXPath;
	}

	public boolean isDownload() {
		return isDownload;
	}

	public void setDownload(boolean isDownload) {
		this.isDownload = isDownload;
	}

	public String getDownloadPattern() {
		return downloadPattern;
	}

	public void setDownloadPattern(String downloadPattern) {
		this.downloadPattern = downloadPattern;
	}

}
