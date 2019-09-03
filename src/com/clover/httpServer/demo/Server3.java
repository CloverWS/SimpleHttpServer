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


public class Server3 {
	private ServerSocket server;
	private static final String CRLF = "\r\n";
	private static final String BLANK = " ";
	public static void main(String[] args) {
		Server3 server = new Server3();
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
			StringBuilder responseContext = new StringBuilder();
			responseContext.append("<html>\r\n" + 
					"<head>\r\n" + 
					"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n" + 
					"<title>HTML5实例</title>\r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					"<p>HTML!Hello</p>\r\n" + 
					"</body>\r\n" + 
					"</html>");
			
			StringBuilder response = new StringBuilder();
			//1) HTTP协议版本、状态代码、描述
			response.append("HTTP/1.1").append(BLANK).append("200").append(BLANK).append("OK").append(CRLF);
			//2) 响应头（Response Head）
			response.append("Server:localhost Server/0.0.1").append(CRLF);
			response.append("Date:").append(new Date()).append(CRLF);
			response.append("Content-type:text/html;charset = UTF-8").append(CRLF);
			//正文长度：字节长度
			response.append("Content-Length:").append(responseContext.toString().getBytes().length).append(CRLF);
			//3）正文之前，空行
			response.append(CRLF);
			//4)正文
			response.append(responseContext);
			
			
			//输出流
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			bw.write(response.toString());
			bw.flush();
			bw.close();
				
				
			
			System.out.println(requestInfo);
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
