package com.tedu.cloudnote.service;

import java.util.List;

import com.tedu.cloudnote.entity.Book;
import com.tedu.cloudnote.util.NoteResult;

public interface BookService {
	public NoteResult<Book> addBook(String userId,String bookName);
	public NoteResult<List<Book>> 
		loadUserBooks(String userId);
}
