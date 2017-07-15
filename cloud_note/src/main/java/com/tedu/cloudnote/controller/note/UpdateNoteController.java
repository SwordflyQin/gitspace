package com.tedu.cloudnote.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnote.service.NoteService;
import com.tedu.cloudnote.util.NoteResult;
@Controller
@RequestMapping("/note")
public class UpdateNoteController {
	@Resource
	private NoteService noteService;
	
	@RequestMapping("/update")
	@ResponseBody
	public NoteResult<Object> execute(
		String noteId,String title,String body){
		//调用业务组件更新笔记处理
		NoteResult<Object> result = 
			noteService.updateNote(
				noteId, title, body);
		return result;
	}
	
}
