package com.tedu.cloudnote.dao;

import java.util.List;
import java.util.Map;

import com.tedu.cloudnote.entity.Note;

public interface NoteDao {
	public int deleteNotes(String[] ids);
	
	public int dynamicUpdate(Note note);
	
	public List<Note> findNotes(Map params);
	
	/**
	 * 按笔记ID做条件更新笔记本ID
	 * @param params noteId笔记ID;bookId笔记本ID
	 * @return
	 */
	//public int updateBookId(Map params);
	//public int updateStatus(String noteId);
	public void save(Note note);
	//public int update(Note note);
	public Note findById(String noteId);
	public List<Map> findByBookId(String bookId);
}
