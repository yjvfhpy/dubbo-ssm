package com.mical.provider.main;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;


//先启动这个 注册到zookpeer里面
public class Runmethod {
	private static ClassPathXmlApplicationContext context;
	public static void main(String[] args) throws IOException {
		context = new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});
		context.start();
		System.out.println("按任意键退出");
		System.in.read();
	}
}
