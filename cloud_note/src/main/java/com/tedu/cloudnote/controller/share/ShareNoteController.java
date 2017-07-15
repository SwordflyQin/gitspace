package com.tedu.cloudnote.controller.share;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnote.service.ShareService;
import com.tedu.cloudnote.util.NoteResult;

@Controller
@RequestMapping("/share")
public class ShareNoteController {
	@Resource
	private ShareService shareService;
	
	@RequestMapping("/add")
	@ResponseBody
	public NoteResult<Object> execute(String noteId){
		NoteResult<Object> result = 
			shareService.shareNote(noteId);
		return result;
	}
	
}



