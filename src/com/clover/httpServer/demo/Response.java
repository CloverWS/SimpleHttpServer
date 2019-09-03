package com.clover.httpServer.demo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

/**
 * 封装Response
 * @author Clove
 *
 */
public class Response {
	//定义两个常量
	private static final String CRLF = "\r\n";
	private static final String BLANK = " ";
	//流
	private BufferedWriter bw;
	
	//正文
	private StringBuilder content;
	//储存头信息
	private StringBuilder headInfo;
	//储存正文长度
	private int len;
	/**
	 * 构造函数
	 */
	public Response() {
		headInfo = new StringBuilder();
		content = new StringBuilder();
		len = 0;
	}
	
	public Response(OutputStream os) {
		headInfo = new StringBuilder();
		content = new StringBuilder();
		len = 0;
		bw = new BufferedWriter(new OutputStreamWriter(os));
	}
	
	public Response(Socket client) {
		headInfo = new StringBuilder();
		content = new StringBuilder();
		len = 0;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch (IOException e) {
			headInfo = null;
		}
	}
	/**
	 * 构建正文
	 */
	public Response print(String info) {
		content.append(info);
		len += info.getBytes().length;
		return this;
	}
	
	/**
	 * 构建正文+回车
	 */
	public Response println(String info) {
		content.append(info).append(CRLF);
		len += (info+CRLF).getBytes().length;
		return this;
	}
	
	/**
	 * 构建响应头
	 */
	private void creatHeadInfo(int code) {
		//1) HTTP协议版本、状态代码、描述
		headInfo.append("HTTP/1.1").append(BLANK).append(code).append(BLANK);
		switch(code) {
		case 200:
			headInfo.append("OK");
			break;
		case 404:
			headInfo.append("NOT FOUND");
			break;
		case 500:
			headInfo.append("SERVER ERROR");
			break;
		}
		headInfo.append(CRLF);
		//2) 响应头（Response Head）
		headInfo.append("Server:localhost Server/0.0.1").append(CRLF);
		headInfo.append("Date:").append(new Date()).append(CRLF);
		headInfo.append("Content-type:text/html;charset = UTF-8").append(CRLF);
		//正文长度：字节长度
		headInfo.append("Content-Length:").append(len).append(CRLF);
		//正文分割
		headInfo.append(CRLF);
	}
	
	void pushToclient(int code) throws IOException {
		
		
		creatHeadInfo(code);
		//头信息+分隔符
		bw.append(headInfo.toString());
		System.out.println(headInfo.toString());
		//正文
		bw.append(content.toString());
		bw.flush();
		bw.close();
		
	}
	
}
