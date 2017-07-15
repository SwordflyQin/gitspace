package com.tedu.cloudnote.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.tedu.cloudnote.dao.NoteDao;
import com.tedu.cloudnote.dao.ShareDao;
import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.entity.Share;
import com.tedu.cloudnote.util.NoteResult;
import com.tedu.cloudnote.util.NoteUtil;

@Service("shareService")
@Transactional(isolation=Isolation.SERIALIZABLE)
public class ShareServiceImpl implements ShareService{
	@Resource
	private ShareDao shareDao;
	@Resource
	private NoteDao noteDao;
	
	//3.指定标记,带标记的方法
	//会采用DataSourceTransactionManager控制事务
	@Transactional
	public NoteResult<Object> shareNote(
			String noteId) {
		//向分享cn_share表插入记录
		Share share = new Share();
		String shareId = NoteUtil.createId();
		share.setCn_share_id(shareId);//分享ID
		share.setCn_note_id(noteId);//笔记ID
		//根据笔记ID查找标题和内容
		Note note = noteDao.findById(noteId);
		share.setCn_share_title(
			note.getCn_note_title());//分享笔记标题
		share.setCn_share_body(
			note.getCn_note_body());//分享笔记内容
		shareDao.save(share);//保存分享记录
		//TODO 修改cn_note表type_id值,设置成分享类型
		//noteDao.updateType(noteId);
		//模拟发生异常
		String s = null;s.length();
		//创建返回结果
		NoteResult<Object> result = 
				new NoteResult<Object>();
		result.setStatus(0);
		result.setMsg("分享笔记成功");
		return result;
	}

	public NoteResult<List<Share>> searchNote(
			String keyword,int page) {
		//参数处理
		String title = "%";
		if(keyword!=null && !"".equals(keyword)){
			title = "%"+keyword.trim()+"%";
		}
		int begin = (page-1)*5;//计算抓取记录起点
		Map<String, Object> params = new HashMap();
		params.put("title", title);//对应#{title}
		params.put("begin", begin);//对应#{begin}
		//模糊搜索 
		List<Share> list = 
			shareDao.findLikeTitle(params);
		//创建返回对象
		NoteResult<List<Share>> result = 
				new NoteResult<List<Share>>();
		result.setStatus(0);
		result.setData(list);
		result.setMsg("搜索完毕");
		return result;
	}

	public NoteResult<Share> loadShare(
			String shareId) {
		//根据分享ID查询分享笔记信息
		Share share = shareDao.findById(shareId);
		//创建返回结果
		NoteResult<Share> result = 
				new NoteResult<Share>();
		result.setStatus(0);
		result.setMsg("查询完毕");
		result.setData(share);
		return result;
	}

}
