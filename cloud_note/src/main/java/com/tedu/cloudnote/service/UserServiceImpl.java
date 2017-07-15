package com.tedu.cloudnote.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tedu.cloudnote.dao.UserDao;
import com.tedu.cloudnote.entity.User;
import com.tedu.cloudnote.util.NoteResult;
import com.tedu.cloudnote.util.NoteUtil;

@Service("userService")//扫描到spring容器
@Transactional
public class UserServiceImpl implements UserService{
	@Resource//注入dao对象
	private UserDao userDao;
	
	public NoteResult<User> checkLogin(
			String name, String password) {
		NoteResult<User> result = 
			new NoteResult<User>();
		//检测用户名
		User user = userDao.findByName(name);
		if(user==null){
			result.setStatus(1);
			result.setMsg("用户名不存在");
			return result;
		}
		//检测密码
		String md5Password = NoteUtil.md5(password);
		if(!user.getCn_user_password().equals(md5Password)){
			result.setStatus(2);
			result.setMsg("密码错误");
			return result;
		}
		//用户名和密码正确
		result.setStatus(0);
		result.setMsg("登录成功");
		result.setData(user);
		return result;
	}

	public NoteResult<Object> addUser(
		String name, String nick, String password) {
		NoteResult<Object> result = 
				new NoteResult<Object>();
		//执行用户名检测
		User hasUser = userDao.findByName(name);
		if(hasUser!=null){//用户名存在
			result.setStatus(1);
			result.setMsg("用户名已被占用");
			return result;
		}
		//执行用户添加逻辑
		User user = new User();
		user.setCn_user_name(name);//设置用户名
		//密码加密
		String md5Password = NoteUtil.md5(password);
		user.setCn_user_password(md5Password);//设置密码
		user.setCn_user_nick(nick);//设置昵称
		//创建id
		String id = NoteUtil.createId();
		user.setCn_user_id(id);//设置用户ID
		userDao.save(user);//保存user
		//构建返回结果
		result.setStatus(0);
		result.setMsg("注册成功!");
		return result;
	}

}
