package com.iwater.pageprocessor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taskconfig.TaskConfig;
import com.taskconfig.TaskFieldConfig;
import com.taskconfig.TaskPageConfig;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.UrlUtils;

/**
 * 通用的请求处理器
 * 
 * @author ldm
 * @Date 2016年10月10日
 */
public class CommonProcessor implements PageProcessor {
	private static final Logger logger = LoggerFactory.getLogger(CommonProcessor.class);
	private Site site;
	private TaskConfig taskConfig;
	private ThreadLocal<Integer> level = new ThreadLocal<Integer>();

	public CommonProcessor(TaskConfig taskConfig) {
		this.site = Site.me().setDomain(UrlUtils.getDomain(taskConfig.getStartUrl()));
		this.taskConfig = taskConfig;
	}

	public void process(Page page) {
		logger.info("processor");
		// 判断当前页面的层级
		if (level.get() == null) {
			level.set(1);
		} else {
			level.set(level.get() + 1);
		}
		logger.info(level.get().toString());

		TaskPageConfig taskPageConfig = taskConfig.pages.get(level.get());
		if (taskPageConfig.isLast()) {
			List<TaskFieldConfig> list = taskPageConfig.fields.get(taskPageConfig.getPageLevel());
			for (TaskFieldConfig taskFieldConfig : list) {
				page.putField(taskFieldConfig.getName(), taskFieldConfig.getxPath());
			}
		} else {
			List<String> urls = page.getHtml().links().regex(taskPageConfig.getUrlPattern()).all();
			page.addTargetRequests(urls);
		}
	}

	public Site getSite() {
		logger.info("site");
		return site;
	}

}
