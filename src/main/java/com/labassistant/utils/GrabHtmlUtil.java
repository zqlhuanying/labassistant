package com.labassistant.utils;


import java.io.IOException;
import java.net.SocketTimeoutException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.labassistant.exception.MyRuntimeException;

/**
 * 获取网页数据
 * @author zql
 * @date 2015/09/08
 */
public final class GrabHtmlUtil {
	
	// 不允许实例化
	private GrabHtmlUtil(){}
	
	/**
	 * 根据url返回html网页，html亦即Document文档
	 * @param url
	 * @return
	 */
	public static Document grabHtmlByUrl(String url){
		try {
			return Jsoup.connect(url).get();
		} catch (SocketTimeoutException e) {
			throw new MyRuntimeException("链接超时");
		} catch (IOException e) {
			throw new MyRuntimeException("无法打开url");
		}
	}
	
	/**
	 * 支持类似于CSS或jQuery的语法来查找和操作元素
	 * 在Jsoup中Document为Element的子类 
	 * 类似Document的操作
	 * 根据选择器返回特定的节点
	 * @param doc 
	 * @param selector 
	 * @return
	 */
	public static Elements grabElements(Element doc, String selector){
		return doc.select(selector);
	}
	
	public static Elements grabElements(String url, String selector){
		return grabElements(grabHtmlByUrl(url), selector);
	}
	
	/**
	 * 通过标签的属性来查找元素，正则表达式
	 * @param key 标签的属性
	 * @param regex 正则表达式
	 * @return
	 */
	public static Elements grabElementsByAttributeValueMatching(Element doc, String key, String regex){
		return doc.getElementsByAttributeValueMatching(key, regex);
	}
	
	/**
	 * 根据doc节点获取index位置处的节点
	 * @param doc
	 * @param selector
	 * @param index
	 * @return
	 */
	public static Element grabElement(Element doc, String selector, int index){
		Elements elements = grabElements(doc, selector);
		if(elements == null) {
			throw new NullPointerException();
		}
		if(index < 0 || index >= elements.size()) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return elements.get(index);
	}
	
	public static Element grabElement(Element doc, String selector){
		return grabElement(doc, selector, 0);
	}
	
	// test
	public static void main(String[] args){
		String url = "http://news.baidu.com/";
		String selectorBody = "div#body";
		String selectorA = "a[href^=http]";
		Document doc = grabHtmlByUrl(url);
		Elements elements = grabElements(grabElement(doc, selectorBody), selectorA);
		String regex = "((http|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?";
		
		/*for(Element e : elements){
			System.out.println("url:" + e.attr("href") + " content:" + e.text());
		}*/
		
		//System.out.println(grabElement(doc, selectorA));
		
		System.out.println(doc.getElementsByAttributeValueMatching("href", regex));
	}
}
