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

public class Server2 {
	private ServerSocket server;
	
	public static void main(String[] args) {
		Server2 server = new Server2();
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
	 * ���տͻ��˷���
	 */
	private void receive() {//д��privateֻ���ڲ�ʹ��
		try {
			Socket client = server.accept();
			StringBuilder sb = new StringBuilder();//����������Ϣ
			String msg = null;//��ʼ�������տͻ��˵�������Ϣ
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
	 * ֹͣ����
	 */
	public void stop() {
		
	}

}
