package com.tedu.cloudnote.controller.share;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnote.entity.Share;
import com.tedu.cloudnote.service.ShareService;
import com.tedu.cloudnote.util.NoteResult;

@Controller
@RequestMapping("/share")
public class ShareSearchController {
	@Resource
	private ShareService shareService;
	
	@RequestMapping("/search")
	@ResponseBody
	public NoteResult<List<Share>> execute(
			String keyword,int page){
		NoteResult<List<Share>> result = 
			shareService.searchNote(keyword,page);
		return result;
	}
	
}



