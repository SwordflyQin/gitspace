package com.tedu.cloudnote.aspect;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.print.attribute.standard.PagesPerMinute;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component//扫描到Spring容器
@Aspect//将组件指定为切面组件
public class ExceptionBean {

	//e就是目标组件方法抛出的异常对象
	@AfterThrowing(throwing="e", pointcut="within(com.tedu.cloudnote.controller..*)")
	public void execute(Exception e){
		try{
			//将异常信息写入文件中
			FileWriter fw = new FileWriter(
					"D:\\note_error.log",true);
			PrintWriter pw = new PrintWriter(fw);
			//利用pw对象写信息
			Date time = new Date();
			SimpleDateFormat sdf = 
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String timeStr = sdf.format(time);
			pw.println("********************************");
			pw.println("*异常类型："+e);
			pw.println("*发生时间："+timeStr);
			pw.println("************异常详情*************");
			e.printStackTrace(pw);
			pw.close();
			fw.close();
		}catch(Exception ex){
			System.out.println("记录异常失败");
		}
	}
	
}
