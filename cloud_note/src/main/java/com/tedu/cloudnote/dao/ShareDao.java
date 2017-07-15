package com.tedu.cloudnote.dao;

import java.util.List;
import java.util.Map;

import com.tedu.cloudnote.entity.Share;

public interface ShareDao {
	public Share findById(String shareId);
	/**
	 * 搜索分享笔记
	 * @param params key1:title,key2:begin
	 * @return 满足条件的分享笔记
	 */
	public List<Share> findLikeTitle(Map params);
	public void save(Share share);
}
