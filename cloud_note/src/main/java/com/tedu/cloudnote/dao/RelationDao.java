package com.tedu.cloudnote.dao;

import java.util.List;

import com.tedu.cloudnote.entity.Book;
import com.tedu.cloudnote.entity.User;

public interface RelationDao {
	//集合关联加载
	public User findUserAndBooks1(String userId);
	public User findUserAndBooks(String userId);
	//单个对象关联加载
	public List<Book> findBookAndUser();
	public List<Book> findBookAndUser1();
}


