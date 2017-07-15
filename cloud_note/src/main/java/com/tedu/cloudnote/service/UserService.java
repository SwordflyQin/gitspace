package com.tedu.cloudnote.service;

import com.tedu.cloudnote.entity.User;
import com.tedu.cloudnote.util.NoteResult;

public interface UserService {
	//public NoteResult<泛型> 方法名(根据请求参数定义);
	public NoteResult<User> checkLogin(String name,String password);
	public NoteResult<Object> addUser(
		String name,String nick,String password);
}
