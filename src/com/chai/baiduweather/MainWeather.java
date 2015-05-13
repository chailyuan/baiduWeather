package com.chai.baiduweather;


import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.TimerTask;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.chai.baiduweatherConstant.MyConstant;

public class MainWeather extends TimerTask{

	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		new MainWeather().getXmlFromServer();
		
		
	}
	public void getXmlFromServer(){
		String pathUrl = MyConstant.getFullPath();
		//服务器地址
//		System.out.println(pathUrl);
		
		try {
			URL url=new URL(pathUrl);
			HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
			
			httpConn.setConnectTimeout(5*1000);
			
			httpConn.setRequestMethod("GET");
			httpConn.connect();
			
			InputStream inStream= httpConn.getInputStream();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStream,"utf-8"));
			StringBuilder sb = new StringBuilder();
			
			String lineString = null;
			
			while ((lineString = reader.readLine())!=null) {
				sb.append(lineString+"\n");
				
			}
			
			inStream.close();
			
			String outfile = sb.toString();
			//输出接收到的所哟数据
//			System.out.println(outfile);
			
			
			// 字符串转XML
			Document doc = DocumentHelper.parseText(outfile);
			Element root = doc.getRootElement();
			String tmp = root.getName();
			System.out.println(tmp);
			
			//先读取错误代码
			Iterator iter= root.elementIterator("error");
			Element element = (Element)iter.next();
			System.out.println("Server Return Code: "+element.getData());
			
			if (element.getData().equals("0")) {
				saveXml(doc);
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void saveXml(Document doc){
		//保存xml文件到磁盘
		   try {
			   
			  
			   String pathString ="";
			   pathString = MyConstant.curPath+"\\weather.xml";
			   System.out.println("xml saved on: "+pathString);
	            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(pathString),"UTF-8");   
	            OutputFormat format = OutputFormat.createPrettyPrint();
	            format.setEncoding("UTF-8");    // 指定XML编码       
	            XMLWriter writer = new XMLWriter(osw,format);
	            
	           
	            writer.write(doc);
	            writer.close();
	            
	            Date now = new Date(); 
	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	            String curTime = dateFormat.format(now);
	            System.out .println("Save Time: "+curTime);
	            
	        } catch (Exception e) {
	            System.out.println("无法将注册用户信息存储到文件中，原因为"+e.getMessage());
	            e.printStackTrace();
	        }
	}

}
