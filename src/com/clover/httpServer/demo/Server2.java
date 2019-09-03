package com.clover.httpServer.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *  创建服务器，并启动。
 * @author Clove
 *
 */

public class Server2 {
	private ServerSocket server;
	
	public static void main(String[] args) {
		Server2 server = new Server2();
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
	 * 接收客户端方法
	 */
	private void receive() {//写成private只供内部使用
		try {
			Socket client = server.accept();
			StringBuilder sb = new StringBuilder();//储存请求信息
			String msg = null;//初始化，接收客户端的请求信息
			BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			
			while((msg = br.readLine()).length() > 0) {
				
				sb.append(msg);
				sb.append("\r\n");
				if(msg == null) {
					break;
				}
				
				
				
			}
			System.out.println(sb.toString());
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
