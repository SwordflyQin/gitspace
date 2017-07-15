package com.tedu.cloudnote.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnote.entity.User;
import com.tedu.cloudnote.service.UserService;
import com.tedu.cloudnote.util.NoteResult;

@Controller//扫描到Spring容器
@RequestMapping("/user")
public class UserLoginController {
	@Resource//注入Service对象
	private UserService userService;
	
	@RequestMapping("/login.do")
	@ResponseBody//JSON输出
	public NoteResult<User> execute(
		String name,String password){
		//调用UserService处理登录
		System.out.println(name+":"+password);
		NoteResult<User> result = 
		userService.checkLogin(name, password);
		return result;
	}
	
}
