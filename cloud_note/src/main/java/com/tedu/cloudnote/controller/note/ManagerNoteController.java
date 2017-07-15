package com.tedu.cloudnote.controller.note;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.service.NoteService;
import com.tedu.cloudnote.util.NoteResult;

@Controller
@RequestMapping("/note")
public class ManagerNoteController {
	@Resource
	private NoteService noteService;
	
	@RequestMapping("/manager")
	@ResponseBody
	public NoteResult<List<Note>> execute(
		String title,String status,
		String begin,String end,String userId){
		NoteResult<List<Note>> result = 
			noteService.loadManager(
				title, status, begin, end, userId);
		return result;
	}
	
}
