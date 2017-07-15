package com.tedu.cloudnote.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnote.service.NoteService;
import com.tedu.cloudnote.util.NoteResult;
@Controller
@RequestMapping("/note")
public class MoveNoteController {
	@Resource
	private NoteService noteService;
	
	@RequestMapping("/move")
	@ResponseBody
	public NoteResult<Object> execute(
		String noteId,String bookId){
		NoteResult<Object> result = 
			noteService.moveNote(noteId, bookId);
		return result;
	}
	
}
