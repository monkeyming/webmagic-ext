package com.simple.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class DemoPageProcessor implements PageProcessor {

	// 1、配置爬虫
	public Site getSite() {
		return Site.me().setRetrySleepTime(3).setSleepTime(100);
	}

	// 定制核心接口
	public void process(Page page) {
		// 2、定义抽取规则
		page.putField("key", page.getHtml().xpath("").toString());
		// 3、发现新网址
		page.addTargetRequest("");
	}

}
