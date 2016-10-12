package com.iwater;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iwater.pageprocessor.CommonProcessor;
import com.taskconfig.TaskConfig;
import com.taskconfig.TaskConfigParser;
import com.utils.TaskConfigQuene;

import us.codecraft.webmagic.Spider;

/**
 * 任务分发
 * 
 * @author ldm
 * @Date 2016年10月10日
 */
public class ProcessorFactory implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(ProcessorFactory.class);

	public void run() {
		logger.info("读取任务配置...");
		while (TaskConfigQuene.hasNext()) {
			createProcessor(TaskConfigQuene.poll());
		}
	}

	/**
	 * 读取配置文件
	 * 
	 * @author ldm
	 * @Date 2016年10月10日
	 * @param configPath
	 * @return
	 */
	private TaskConfig getTaskConfig(String configPath) {
		String realPath = ContextConfig.getInstance().getTaskDir() + File.separator + configPath;
		TaskConfigParser taskConfigParser = new TaskConfigParser(realPath);
		return taskConfigParser.load();
	}

	@Deprecated
	private Properties getTaskConfigOld(String configPath) {
		String realPath = ContextConfig.getInstance().getTaskDir() + File.separator + configPath;
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(realPath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;
	}

	/**
	 * 创建processor
	 * 
	 * @author ldm
	 * @Date 2016年10月10日
	 */
	private void createProcessor(String configPath) {
		TaskConfig taskConfig = getTaskConfig(configPath);

		CommonProcessor commonProcessor = new CommonProcessor(taskConfig);
		Spider.create(commonProcessor).addUrl(taskConfig.getStartUrl()).thread(1).run();
	}
}
