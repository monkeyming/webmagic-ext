package com.utils;

import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 缓存任务配置文件
 * 
 * @author ldm
 * @Date 2016年10月10日
 */
public class TaskConfigQuene {
	static ConcurrentLinkedQueue<String> quene = new ConcurrentLinkedQueue<String>();

	/**
	 * 读取第一个任务，然后移除
	 * 
	 * @author ldm
	 * @Date 2016年10月10日
	 * @return
	 */
	public static String poll() {
		return quene.poll();
	}

	/**
	 * 设置任务
	 * 
	 * @author ldm
	 * @Date 2016年10月10日
	 * @param taskFile
	 */
	public static void setQuene(String... taskConfigPaths) {
		quene.addAll(Arrays.asList(taskConfigPaths));
	}

	/**
	 * 添加单个任务
	 * 
	 * @author ldm
	 * @Date 2016年10月10日
	 * @param taskConfigPath
	 */
	public static void addTask(String taskConfigPath) {
		quene.add(taskConfigPath);
	}

	/**
	 * 是否还有任务
	 * 
	 * @author ldm
	 * @Date 2016年10月10日
	 * @return
	 */
	public static boolean hasNext() {
		return !quene.isEmpty();
	}
}
