package com.clover.httpServer.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *  ��������������������
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
	 *�������� 
	 */
	
	public void start() {
		try {
			server = new ServerSocket(8888);//����8888����˿�
			this.receive();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ���տͻ��˷���   ������get�����Ľ��գ�����ȴ��������Post����
	 */
	private void receive() {//д��privateֻ���ڲ�ʹ��
		try {
			Socket client = server.accept();
			String msg = null;//��ʼ�������տͻ��˵�������Ϣ
			
			byte[] data = new byte[20480];
			int len = client.getInputStream().read(data);
			
			String requestInfo = new String(data,0,len).trim();
				
				
			
			System.out.println(requestInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ֹͣ����
	 */
	public void stop() {
		
	}

}
