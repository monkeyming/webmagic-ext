package com.iwater.pageprocessor;

import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selector;
import us.codecraft.webmagic.utils.UrlUtils;

/**
 * 通用的请求处理器
 * 
 * @author ldm
 * @Date 2016年10月10日
 */
public class CommonProcessorOld implements PageProcessor {
	private static final Logger logger = LoggerFactory.getLogger(CommonProcessorOld.class);
	private Site site;
	private Properties taskConfig;
	private ThreadLocal<Integer> level = new ThreadLocal<Integer>();

	public CommonProcessorOld(Properties taskConfig) {
		this.site = Site.me().setDomain(UrlUtils.getDomain(taskConfig.getProperty("task.url")));
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
		String islast = taskConfig.getProperty("task.page." + level.get() + ".islast");
		if (islast == null) {
			logger.info("没有读取到标志位...");
			return;
		}
		if (islast.equals("1")) {
			Selector selector;
			page.getHtml().select(selector);
			page.putField("", "");

		} else {
			List<String> urls = page.getHtml().links().regex(taskConfig.getProperty("task.page." + level.get() + ".urlPattern")).all();
			page.addTargetRequests(urls);
		}
	}

	public Site getSite() {
		logger.info("site");
		return site;
	}

}
