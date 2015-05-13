package com.chai.baiduweatherConstant;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MyConstant {
	public static final String  ak = "&ak=OtCo2poN12KaQ0hnQX7CIk2b";
	public static final String uRLHTTP ="http://api.map.baidu.com/telematics/v3/weather?";
	public static final String location = "location=";
	public static final String cityName = "北京";
	public static final String returnType = "&output=xml";
	public static String curPath = "";

	public MyConstant(){
		
		   try {
			   
			   File directory = new File("."); 
			   String pathString = directory.getCanonicalPath();//得到的是C:/test 
			   curPath = pathString;
			   
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	public static final String getFullPath(){
		String returnValue= "";
		
		try {
			URLEncoder.encode(cityName,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		returnValue =uRLHTTP+location+cityName+returnType+ak;
		
		return returnValue;
	} 
}
