package com.tedu.cloudnote.controller.share;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnote.entity.Share;
import com.tedu.cloudnote.service.ShareService;
import com.tedu.cloudnote.util.NoteResult;

@Controller
@RequestMapping("/share")
public class LoadShareController {
	@Resource
	private ShareService shareService;
	
	@RequestMapping("/load")
	@ResponseBody
	public NoteResult<Share> execute(
			String shareId){
		NoteResult<Share> result = 
		  shareService.loadShare(shareId);
		return result;
	}
	
}




