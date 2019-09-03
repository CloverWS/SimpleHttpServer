package com.clover.httpServer.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ��װrequest
 * @author Clove
 *
 */
public class Request {
	/**
	 * �������ݵ�һ�����£�get or post ��������
	 * GET /index.html?uname=sdskjd&pwd=sds HTTP/1.1
	 * GET /index.html HTTP/1.1
	 */
	//��������ʽ��get or post
	private String method;
	//������Դ ���磺index.html
	private String url;
	//���������get���ܴ��ڣ�post������������֣��ڷָ��к��棩
	private Map<String,List<String>> parameterMapValues;
	
	//�ڲ�
	public static final String CRLF = "\r\n";
	private InputStream is;
	//���տͻ��˷��͵���Ϣ
	private String requestInfo;
	
	
	//���죬�����ָ���쳣������ʹ�õ�����δ����ʼ����
	public Request() {
		method = "";
		url = "";
		parameterMapValues = new HashMap<String,List<String>>();
		
	}
	//�����Ǵ�����
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
		//����ͷ��Ϣ
		parseRequestInfo();
		
	}
	/**
	 * ����������Ϣ
	 */
	
	private void parseRequestInfo() {
		if(requestInfo == null) {
			return;
		}
		
		String paramString = "";//�����������
		String firstLine = requestInfo.substring(0, requestInfo.indexOf(CRLF));
		int idx = requestInfo.indexOf("/");
		this.method = firstLine.substring(0,idx).trim();
	    String urlStr = firstLine.substring(idx,firstLine.indexOf("HTTP")).trim();
		if(this.method.equalsIgnoreCase("post")) {
			this.url = urlStr;
			//�����post��������������������ڷָ��к�������subString�����Σ��������һ�����з���ʼ��
			paramString = requestInfo.substring(requestInfo.lastIndexOf(CRLF)).trim();
		}else if(this.method.equalsIgnoreCase("get")){
			if(urlStr.contains("?")) {//�Ƿ���ڲ���-------��Ϊget����Ҳ�ῼ�ǣ��Ƿ��б������
				                      //û�б��Ļ������㲻���ڡ�������Ҳ���������������
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
