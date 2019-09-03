package com.clover.httpServer.demo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *  创建服务器，并启动。
 * @author Clove
 *
 */


public class Server {
	private ServerSocket server;
	public static void main(String[] args) {
		Server server = new Server();
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
			Request req = new Request(client.getInputStream());
			
		   //响应
			
		   Response rep = new Response(client.getOutputStream());
		   rep.println("<html><head><title>200响应实</title>");
		   rep.println("</head><body></body></html>");
		   rep.pushToclient(200);
		   
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
