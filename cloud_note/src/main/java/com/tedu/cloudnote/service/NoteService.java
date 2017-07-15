package com.tedu.cloudnote.service;

import java.util.List;
import java.util.Map;

import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.util.NoteResult;

public interface NoteService {
	public NoteResult<List<Note>> loadManager(
		String title,String status,
		String begin,String end,String userId);
	public NoteResult<Object> moveNote(
			String noteId,String bookId);
	public NoteResult<Object> 
			deleteNote(String noteId);
	public NoteResult<Note> addNote(
		String userId,String bookId,String title);
	public NoteResult<Object> updateNote(
		String noteId,String title,String body);
	public NoteResult<Note> 
			loadNote(String noteId);
	public NoteResult<List<Map>> 
			loadBookNotes(String bookId);
}
