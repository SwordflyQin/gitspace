package com.tedu.cloudnote.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnote.service.UserService;
import com.tedu.cloudnote.util.NoteResult;
import com.tedu.cloudnote.util.NoteUtil;
@Controller
@RequestMapping("/user")
public class UserRegistController {
	@Resource
	private UserService userService;
	@RequestMapping("/add.do")
	@ResponseBody
	public NoteResult<Object> execute(
		String name,String nick,String password){
		//打桩
		NoteResult<Object> result = 
			userService.addUser(
				name, nick, password);
		return result;
	}
	
}
