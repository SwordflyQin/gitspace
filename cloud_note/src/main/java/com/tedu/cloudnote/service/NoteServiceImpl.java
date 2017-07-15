package com.tedu.cloudnote.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tedu.cloudnote.dao.NoteDao;
import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.util.NoteResult;
import com.tedu.cloudnote.util.NoteUtil;
@Service("noteService")
@Transactional
public class NoteServiceImpl implements NoteService{
	@Resource
	private NoteDao noteDao;
	
	@Transactional(readOnly=true)
	public NoteResult<List<Map>> 
			loadBookNotes(String bookId) {
		//根据笔记本ID查询笔记信息
		List<Map> list = 
			noteDao.findByBookId(bookId);
		//创建返回结果
		NoteResult<List<Map>> result = 
				new NoteResult<List<Map>>();
		result.setStatus(0);
		result.setData(list);
		result.setMsg("加载笔记信息完成");
		return result;
	}

	@Transactional(readOnly=true)
	public NoteResult<Note> loadNote(String noteId) {
		//按笔记ID查询笔记内容
		Note note = noteDao.findById(noteId);
		//构建返回结果
		NoteResult<Note> result = 
				new NoteResult<Note>();
		if(note == null){
			result.setStatus(1);
			result.setMsg("未找到数据");
			return result;
		}else{
			result.setStatus(0);
			result.setData(note);
			result.setMsg("查询完毕");
			return result;
		}
	}

	@Transactional//默认可读可写
	public NoteResult<Object> updateNote(
		String noteId, String title, String body) {
		//创建note参数
		Note note = new Note();
		note.setCn_note_id(noteId);//笔记ID
		note.setCn_note_title(title);//笔记标题
		note.setCn_note_body(body);//笔记内容
		Long time = System.currentTimeMillis();
		note.setCn_note_last_modify_time(time);//修改时间
		//更新数据库
//		int rows = noteDao.update(note);
		int rows = noteDao.dynamicUpdate(note);
		//创建返回结果
		NoteResult<Object> result = 
				new NoteResult<Object>();
		if(rows==1){
			result.setStatus(0);
			result.setMsg("保存笔记成功");
			return result;
		}else{//==0
			result.setStatus(1);
			result.setMsg("未更新记录");
			return result;
		}
	}

	public NoteResult<Note> addNote(
		String userId, String bookId, String title) {
		Note note = new Note();
		note.setCn_user_id(userId);//用户ID
		note.setCn_notebook_id(bookId);//笔记本ID
		note.setCn_note_title(title);//笔记标题
		String noteId = NoteUtil.createId();
		note.setCn_note_id(noteId);//笔记ID
		note.setCn_note_body("");//笔记内容
		Long time = System.currentTimeMillis();
		note.setCn_note_create_time(time);//创建时间
		note.setCn_note_last_modify_time(time);//最后修改时间
		note.setCn_note_status_id("1");//状态,1:normal;2:recycle
		note.setCn_note_type_id("1");//类型,1:normal;2:favor收藏;3:分享
		noteDao.save(note);//保存笔记
		//创建返回结果
		NoteResult<Note> result = 
				new NoteResult<Note>();
		result.setStatus(0);
		result.setMsg("创建笔记成功");
		result.setData(note);
		return result;
	}

	public NoteResult<Object> deleteNote(
			String noteId) {
		//更新笔记的状态
//		int rows = noteDao.updateStatus(noteId);
		Note note = new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_status_id("2");
		int rows = noteDao.dynamicUpdate(note);
		//创建返回结果
		NoteResult<Object> result = 
				new NoteResult<Object>();
		if(rows==1){
			result.setStatus(0);
			result.setMsg("删除笔记成功");
		}else{//==0
			result.setStatus(1);
			result.setMsg("删除笔记失败");
		}
		return result;
	}

	public NoteResult<Object> moveNote(
		String noteId, String bookId) {
		//更新笔记本ID
//		Map<String,Object> params = 
//			new HashMap<String, Object>();
//		params.put("noteId", noteId);
//		params.put("bookId", bookId);
//		int rows = noteDao.updateBookId(params);
		Note note = new Note();
		note.setCn_notebook_id(bookId);
		note.setCn_note_id(noteId);
		int rows = noteDao.dynamicUpdate(note);
		//创建返回结果
		NoteResult<Object> result = 
				new NoteResult<Object>();
		if(rows==1){
			result.setStatus(0);
			result.setMsg("转移笔记成功");
		}else{
			result.setStatus(1);
			result.setMsg("转移笔记失败");
		}
		return result;
	}

	public NoteResult<List<Note>> loadManager(
		String title, String status, 
		String begin, String end,String userId) {
		//根据传入的请求参数设置SQL参数
		Map<String,Object> params = 
			new HashMap<String, Object>();
		params.put("userId", userId);
		//判断标题条件,不是空时有效
		if(title!=null && !"".equals(title)){
			params.put("title", "%"+title+"%");
		}
		//判断状态条件,不是"全部"时有效
		if(status!=null && !"0".equals(status)){
			params.put("status", status);
		}
		//判断开始日期条件,不是空有效
		if(begin!=null && !"".equals(begin)){
			Date beginDate = Date.valueOf(begin);
			params.put("begin", beginDate.getTime());
		}
		//判断结束日期条件,不是空有效
		if(end!=null && !"".equals(end)){
			Date endDate = Date.valueOf(end);
			params.put("end", endDate.getTime());
		}
		//执行查询
		List<Note> list = 
			noteDao.findNotes(params);
		//创建返回结果
		NoteResult<List<Note>> result = 
			new NoteResult<List<Note>>();
		result.setStatus(0);
		result.setData(list);
		result.setMsg("搜索完毕");
		return result;
	}

}


