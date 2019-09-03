package com.clover.httpServer.demo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

/**
 * ��װResponse
 * @author Clove
 *
 */
public class Response {
	//������������
	private static final String CRLF = "\r\n";
	private static final String BLANK = " ";
	//��
	private BufferedWriter bw;
	
	//����
	private StringBuilder content;
	//����ͷ��Ϣ
	private StringBuilder headInfo;
	//�������ĳ���
	private int len;
	/**
	 * ���캯��
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
	 * ��������
	 */
	public Response print(String info) {
		content.append(info);
		len += info.getBytes().length;
		return this;
	}
	
	/**
	 * ��������+�س�
	 */
	public Response println(String info) {
		content.append(info).append(CRLF);
		len += (info+CRLF).getBytes().length;
		return this;
	}
	
	/**
	 * ������Ӧͷ
	 */
	private void creatHeadInfo(int code) {
		//1) HTTPЭ��汾��״̬���롢����
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
		//2) ��Ӧͷ��Response Head��
		headInfo.append("Server:localhost Server/0.0.1").append(CRLF);
		headInfo.append("Date:").append(new Date()).append(CRLF);
		headInfo.append("Content-type:text/html;charset = UTF-8").append(CRLF);
		//���ĳ��ȣ��ֽڳ���
		headInfo.append("Content-Length:").append(len).append(CRLF);
		//���ķָ�
		headInfo.append(CRLF);
	}
	
	void pushToclient(int code) throws IOException {
		
		
		creatHeadInfo(code);
		//ͷ��Ϣ+�ָ���
		bw.append(headInfo.toString());
		System.out.println(headInfo.toString());
		//����
		bw.append(content.toString());
		bw.flush();
		bw.close();
		
	}
	
}
