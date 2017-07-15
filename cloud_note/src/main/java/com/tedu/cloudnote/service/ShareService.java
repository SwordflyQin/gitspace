package com.tedu.cloudnote.service;

import java.util.List;

import com.tedu.cloudnote.entity.Share;
import com.tedu.cloudnote.util.NoteResult;

public interface ShareService {
	public NoteResult<Share> 
			loadShare(String shareId);
	public NoteResult<List<Share>> 
			searchNote(String keyword,int page);
	public NoteResult<Object> 
			shareNote(String noteId);
}
