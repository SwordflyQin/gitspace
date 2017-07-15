package com.tedu.cloudnote.dao;

import com.tedu.cloudnote.entity.User;

public interface UserDao {
	public User findByName(String name);
	public void save(User user);
}
