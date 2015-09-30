package com.labassistant.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.labassistant.utils.GrabHtmlUtil;

/**
 * 抓取网页新闻链接
 * @author zql
 * @date 2015/09/28
 */
@Service
public class GrabNewsServiceImpl implements GrabNewsService {

	private final String[] URLS = new String[]{"http://www.dxy.cn/", "http://www.bioon.com/"};
	
	@Override
	public List<Object> grabNews(){
		String key = "href";
		String regex = "((http|https)://)(.*?)/article+(/[\\w- ./?%&=]*)?";
		List<Object> lists = new ArrayList<Object>();
		for(String url : URLS){
			// 获取到指定网站的文档流
			Document doc = GrabHtmlUtil.grabHtmlByUrl(url); 
			Elements elements = GrabHtmlUtil.grabElementsByAttributeValueMatching(doc, key, regex);
			for(Element element : elements){
				if(contentNotNull(element.text()) && minContentLength(element.text())){
					Map<String, String> map = new HashMap<String, String>();
					map.put("url", element.attr(key));
					map.put("content", element.text());
					lists.add(map);
				}
			}
		}
		return lists;
	}
	
	/**
	 * 获取链接时，不允许内容为空
	 * @param text
	 * @return
	 */
	private Boolean contentNotNull(String text){
		return !"".equals(text)? true : false; 
	}
	
	/**
	 * 获取链接时，文本的内容的长度不少于3
	 * @param text
	 * @return
	 */
	private Boolean minContentLength(String text){
		return text.length() >= 3;
	}
}
