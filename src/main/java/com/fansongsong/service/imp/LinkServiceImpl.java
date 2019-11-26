package com.fansongsong.service.imp;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fansongsong.entity.Link;
import com.fansongsong.mapper.LinkMapper;
import com.fansongsong.service.LinkService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @ClassName: LinkServiceImpl 
 * @Description: TODO
 * @author: Creazy丿绝情
 * @date: 2019年11月26日 下午6:21:56
 */
@Service
public class LinkServiceImpl  implements LinkService{
	
	@Autowired
	private LinkMapper linkMapper;

	@Override
	public PageInfo<Link> list(Integer page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page,10);
		return new PageInfo<Link>(linkMapper.list());
	}

	@Override
	public Link get(Integer id) {
		// TODO Auto-generated method stub
		return linkMapper.get(id);
	}

	@Override
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return linkMapper.delete(id);
	}

	@Override
	public Integer update(@Valid Link link) {
		// TODO Auto-generated method stub
		return linkMapper.update(link);
	}

	@Override
	public Integer add(@Valid Link link) {
		// TODO Auto-generated method stub
		return linkMapper.add(link);
	}


}
