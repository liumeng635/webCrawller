/**   
 * Copyright © 2018 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.yinian.crawller.main 
 * @author: 刘猛  
 * @date: 2018年7月20日 下午5:34:48 
 */
package com.yinian.crawller.main;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;

import com.yinian.crawller.util.UrlPicUtil;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

/**
 * Copyright: Copyright (c) 2018 liumeng
 * 
 * @ClassName: NewsCrawler.java
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: 刘猛
 * @date: 2018年7月20日 下午5:34:48
 */
public class NewsCrawler extends BreadthCrawler {
	
	public static StringBuilder sb = new StringBuilder();
	AtomicInteger i = new AtomicInteger(0);
//	private static String regex1 = "http://news.hfut.edu.cn/show-.*html";
	private static String regex1 = "http://588ku.com/sucai/.*html";
	/* 不获取这样的格式 jpg|png|gif */
	private static String regex2 = "-.*\\\\.(jpg|png|gif).*";
	/* 也不要这样的 # */
	private static String regex3 = "-.*#.*";
	
	public NewsCrawler(String crawlPath, boolean autoParse,int pageSize) {
		super(crawlPath, autoParse);
		/* 页面开始 */
		for(int i = 1; i <= pageSize; i++) {
            this.addSeed("http://588ku.com/sucai/page"+i+"/");
        }
		this.addRegex(regex1);
		this.addRegex(regex2);
		this.addRegex(regex3);
	}
	
	@Override
	public void visit(Page page, CrawlDatums next) {
		String url = page.url();
		/* 如果是一个新的页面 */
		if (page.matchUrl(regex1)) {
			/* 用JSOUP去解析这个页面 */
			Document doc = page.doc();

			/* 的提取新闻和标题的css选择器 */
			String img = page.select("div[class=img-l-box] img").attr("src");

			if (StringUtils.isNotBlank(img) && img.startsWith("http")) {
				System.out.println("img:\n" + img);
				sb.append(img + "\n");
				try {
					UrlPicUtil.getUrlPic(img, "D://urlPic");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		NewsCrawler crawler = new NewsCrawler("crawl", true,1067);
		crawler.setThreads(50);
		crawler.setMaxExecuteCount(100);
		// crawler.setResumable(true);
		/* 网页爬取深度为4 */
		crawler.start(4);
		FileUtils.writeStringToFile(new File("C:\\Users\\Administrator\\Desktop\\img.txt"), sb.toString(), "UTF-8");
	}

}
