package com.tedu.cloudnote.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tedu.cloudnote.dao.BookDao;
import com.tedu.cloudnote.entity.Book;
import com.tedu.cloudnote.util.NoteResult;
import com.tedu.cloudnote.util.NoteUtil;

@Service("bookService")//扫描
@Transactional
public class BookServiceImpl 
	implements BookService{
	@Resource//注入
	private BookDao bookDao;
	
	public NoteResult<List<Book>> 
			loadUserBooks(String userId) {
		//按用户ID查询笔记本信息
		List<Book> list = 
			bookDao.findByUserId(userId);
		//构建返回结果
		NoteResult<List<Book>> result = 
			new NoteResult<List<Book>>();
		result.setStatus(0);
		result.setData(list);
		result.setMsg("查询笔记本完成");
		return result;
	}

	public NoteResult<Book> addBook(
			String userId, String bookName) {
		Book book = new Book();
		book.setCn_user_id(userId);//用户ID
		book.setCn_notebook_name(bookName);//笔记本名
		String bookId = NoteUtil.createId();
		book.setCn_notebook_id(bookId);//笔记本ID
		Timestamp time = new Timestamp(
			System.currentTimeMillis());
		book.setCn_notebook_createtime(time);//创建时间
		book.setCn_notebook_type_id("5");//笔记本类型,normal
		bookDao.save(book);//保存
		//创建返回结果
		NoteResult<Book> result = 
				new NoteResult<Book>();
		result.setStatus(0);
		result.setMsg("创建笔记本成功");
		result.setData(book);
		return result;
	}

}
