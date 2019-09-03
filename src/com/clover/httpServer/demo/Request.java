package com.clover.httpServer.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 封装request
 * @author Clove
 *
 */
public class Request {
	/**
	 * 请求内容第一行如下，get or post 会有区别
	 * GET /index.html?uname=sdskjd&pwd=sds HTTP/1.1
	 * GET /index.html HTTP/1.1
	 */
	//储存请求方式：get or post
	private String method;
	//请求资源 例如：index.html
	private String url;
	//请求参数（get可能存在，post不在首行里出现，在分割行后面）
	private Map<String,List<String>> parameterMapValues;
	
	//内部
	public static final String CRLF = "\r\n";
	private InputStream is;
	//接收客户端发送的信息
	private String requestInfo;
	
	
	//构造，避免空指针异常（存在使用的数据未被初始化）
	public Request() {
		method = "";
		url = "";
		parameterMapValues = new HashMap<String,List<String>>();
		
	}
	//这里是传参数
	public Request(InputStream is) {
		this();
		this.is = is;
		try {
			byte[] data = new byte[20480];
			int len = is.read(data);
			requestInfo = new String(data,0,len).trim();
		} catch (IOException e) {
			return;
		}
		//分析头信息
		parseRequestInfo();
		
	}
	/**
	 * 分析请求信息
	 */
	
	private void parseRequestInfo() {
		if(requestInfo == null) {
			return;
		}
		
		String paramString = "";//接收请求参数
		String firstLine = requestInfo.substring(0, requestInfo.indexOf(CRLF));
		int idx = requestInfo.indexOf("/");
		this.method = firstLine.substring(0,idx).trim();
	    String urlStr = firstLine.substring(idx,firstLine.indexOf("HTTP")).trim();
		if(this.method.equalsIgnoreCase("post")) {
			this.url = urlStr;
			//如果是post方法，则请求参数出现在分割行后，所有用subString（单参），从最后一个换行符开始。
			paramString = requestInfo.substring(requestInfo.lastIndexOf(CRLF)).trim();
		}else if(this.method.equalsIgnoreCase("get")){
			if(urlStr.contains("?")) {//是否存在参数-------因为get方法也会考虑，是否有表单，如果
				                      //没有表单的话，它便不存在“？”，也不存在请求参数。
				String[] urlArray = urlStr.split("\\?");
				this.url = urlArray[0];
				paramString = urlArray[1];
			}else {
				this.url = urlStr;
			}
		}else {
			System.out.println("ERROR METHOD!");
		}
	}

}
