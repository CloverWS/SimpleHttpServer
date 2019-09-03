package com.clover.httpServer.demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 *  创建服务器，并启动。
 * @author Clove
 *
 */


public class Server4 {
	private ServerSocket server;
	private static final String CRLF = "\r\n";
	private static final String BLANK = " ";
	public static void main(String[] args) {
		Server4 server = new Server4();
		server.start();
	}
	
	/**
	 *启动方法 
	 */
	
	public void start() {
		try {
			server = new ServerSocket(8888);//监听8888这个端口
			this.receive();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 接收客户端方法   适用于get方法的接收，但是却不适用于Post方法
	 */
	private void receive() {//写成private只供内部使用
		try {
			Socket client = server.accept();
			String msg = null;//初始化，接收客户端的请求信息
			
			byte[] data = new byte[20480];
			int len = client.getInputStream().read(data);
			
			String requestInfo = new String(data,0,len).trim();
			
		   //响应
			
		   Response rep = new Response(client.getOutputStream());
		   rep.println("<html><head><title>505响应实</title>");
		   rep.println("</head><body></body></html>");
		   rep.pushToclient(500);
		   
		   //System.out.println(requestInfo);
				
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 停止方法
	 */
	public void stop() {
		
	}

}
