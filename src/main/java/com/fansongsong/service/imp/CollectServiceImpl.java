package com.fansongsong.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fansongsong.entity.Collect;
import com.fansongsong.mapper.CollectMapper;
import com.fansongsong.service.CollectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @author zhuzg
 *
 */
@Service
public class CollectServiceImpl  implements CollectService{
	
	@Autowired
	private CollectMapper collectMapper;

	@Override
	public Integer add(Collect collect) {
		// TODO Auto-generated method stub
		return collectMapper.add(collect);
	}

	@Override
	public PageInfo list(Integer userId, Integer page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page,10);
		return new PageInfo<Collect>(collectMapper.list(userId));
	}

	@Override
	public Collect get(Integer id) {
		// TODO Auto-generated method stub
		return collectMapper.get(id);
	}

	@Override
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return collectMapper.delete(id);
	}

	@Override
	public Integer update(Collect collect) {
		// TODO Auto-generated method stub
		return collectMapper.update(collect);
	}

}
