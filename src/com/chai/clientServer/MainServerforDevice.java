package com.chai.clientServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

public class MainServerforDevice {

	public class ClientHandler implements Runnable{

		Socket socket = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		
		public ClientHandler(Socket socket){
			this.socket = socket;
		}
		
		public void run() {
			// TODO Auto-generated method stub
			
			try {
				inputStream = socket.getInputStream();
				outputStream= socket.getOutputStream();
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream,"utf-8"));
				
				//读取数据
				StringBuilder sb = new StringBuilder();				
				String lineString = null;				
				while ((lineString = reader.readLine())!=null) {
					sb.append(lineString+"\n");
					
				}				
				inputStream.close();
				String inputString = sb.toString();
				// 字符串转XML
				Document doc = DocumentHelper.parseText(inputString);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
	
	
	public void startServer(){
		
		try {
			
			ServerSocket server = new ServerSocket(8000);
			
			while(true){
				Socket client = server.accept();
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
