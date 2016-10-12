package com.iwater;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.PatternFilenameFilter;
import com.utils.TaskConfigQuene;

/**
 * 入口类
 * 
 * @author ldm
 * @Date 2016年10月10日
 */
public class WaterScrapy {
	private static final Logger logger = LoggerFactory.getLogger(WaterScrapy.class);

	public static void main(String[] args) {
		String taskDir = ContextConfig.getInstance().getTaskDir();
		if (StringUtils.isEmpty(taskDir)) {
			logger.info("读取任务目录失败...");
			return;
		}
		File taskFile = new File(taskDir);
		if (!taskFile.isDirectory()) {
			logger.info("配置不是一个目录");
			return;
		}
		logger.info("当前任务目录：" + taskDir);

		FilenameFilter filter = new PatternFilenameFilter(".*\\.properties");
		String[] list = taskFile.list(filter);
		logger.info(Arrays.toString(list));
		if (list != null && list.length <= 0) {
			logger.info("没读取到任务配置...");
			return;
		}
		logger.info("读取到" + list.length + " 个任务");
		TaskConfigQuene.setQuene(list);

		Thread thread = new Thread(new ProcessorFactory());
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
