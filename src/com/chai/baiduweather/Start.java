package com.chai.baiduweather;

import java.util.Timer;

import com.chai.baiduweatherConstant.MyConstant;

public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//实例化常数
		MyConstant myConstant = new MyConstant();
		
		//使用定时器每隔一段时间读取百度的数据
		Timer timer = new Timer();
		timer.schedule(new MainWeather(), 1000, 60*1000);
		
	}

}
