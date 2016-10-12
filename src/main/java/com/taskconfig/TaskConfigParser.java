package com.taskconfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 任务配置解析器
 * 
 * @author ldm
 * @Date 2016年10月11日
 */
public class TaskConfigParser {
	private static final Logger logger = LoggerFactory.getLogger(TaskConfigParser.class);
	// 配置路径
	private String configPath;

	public TaskConfigParser(String configPath) {
		this.configPath = configPath;
	}

	public TaskConfig load() {
		File file = new File(configPath);
		if (!(file.exists() && file.isFile())) {
			logger.info("配置文件不存在");
			return null;
		}
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(file));
		} catch (Exception e) {
			logger.info("加载配置文件失败");
			logger.info(e.getMessage());
		}
		TaskConfig taskConfig = new TaskConfig();
		taskConfig.setName(properties.getProperty("task.name"));
		taskConfig.setStartUrl(properties.getProperty("task.url"));
		taskConfig.setDesc(properties.getProperty("task.desc"));

		Map<Integer, TaskPageConfig> pagesMap = loadPages(properties);

		taskConfig.setPages(pagesMap);
		return taskConfig;
	}

	/**
	 * 加载所有页面配置
	 * 
	 * @author ldm
	 * @Date 2016年10月11日
	 * @param properties
	 * @return
	 */
	private Map<Integer, TaskPageConfig> loadPages(Properties properties) {
		Map<Integer, TaskPageConfig> pagesMap = new HashMap<Integer, TaskPageConfig>();
		int pageLevel = 1;
		do {
			String isLast = properties.getProperty("task.page." + pageLevel + ".islast");
			if (isLast == null) {
				// 已经没有页面配置了
				logger.info("加载页面配置完成，最高级别为：" + (pageLevel - 1));
				break;
			} else {
				TaskPageConfig taskPageConfig = new TaskPageConfig();
				taskPageConfig.setPageLevel(pageLevel);
				taskPageConfig.setLast(isLast.equals("1"));
				taskPageConfig.setUrlPattern(properties.getProperty("task.page." + pageLevel + ".urlPattern"));
				taskPageConfig.setStartXPath(properties.getProperty("task.page." + pageLevel + ".startXPath"));
				taskPageConfig.setDownload("1".equals(properties.getProperty("task.page." + pageLevel + ".download")));
				taskPageConfig
						.setDownloadPattern(properties.getProperty("task.page." + pageLevel + ".downloadPattern"));

				Map<Integer, List<TaskFieldConfig>> fields = new HashMap<Integer, List<TaskFieldConfig>>();
				List<TaskFieldConfig> list = loadLevelFields(properties, pageLevel);
				fields.put(pageLevel, list);
				taskPageConfig.setFields(fields);

				pagesMap.put(pageLevel, taskPageConfig);
			}
			pageLevel++;
		} while (true);
		return pagesMap;
	}

	/**
	 * 加载页面的字段
	 * 
	 * @author ldm
	 * @Date 2016年10月11日
	 * @param properties
	 * @param pageLevel
	 * @return
	 */
	private List<TaskFieldConfig> loadLevelFields(Properties properties, int pageLevel) {
		List<TaskFieldConfig> list = new ArrayList<TaskFieldConfig>();
		int fieldIndex = 1;
		while (true) {
			String name = properties.getProperty("task.page." + pageLevel + ".field." + fieldIndex + ".name");
			if (name == null) {
				// 没有字段
				logger.info("字段加载完成...");
				break;
			} else {
				TaskFieldConfig taskFieldConfig = new TaskFieldConfig();
				taskFieldConfig.setLevel(pageLevel);
				taskFieldConfig.setName(name);
				taskFieldConfig
						.setxPath(properties.getProperty("task.page." + pageLevel + ".field." + fieldIndex + ".XPath"));
				list.add(taskFieldConfig);
			}
			fieldIndex++;
		}
		return list;
	}
}
