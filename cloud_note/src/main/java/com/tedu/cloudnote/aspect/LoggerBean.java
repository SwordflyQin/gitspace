package com.tedu.cloudnote.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//封装共同处理的组件
@Component//扫描,等价于<bean>定义
@Aspect//等价于<aop:aspect>定义
public class LoggerBean {

	//等价于<aop:before>定义
	//在Controller方法执行前,先执行logController处理
@Before("within(com.tedu.cloudnote.controller..*)")
	public void logController(){
		System.out.println("进入Controller处理请求");
	}

@Before("within(com.tedu.cloudnote.service..*)")
	public void logService(){
		System.out.println("进入Service处理请求");
	}

	
}
