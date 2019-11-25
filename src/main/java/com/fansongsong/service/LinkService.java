package com.fansongsong.service;

import javax.validation.Valid;

import com.fansongsong.entity.Link;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @ClassName: LinkService 
 * @Description: TODO
 * @author: Creazy丿绝情
 * @date: 2019年11月25日 下午2:11:36
 */
public interface LinkService {

	/**
	 * 
	 * @Title: list 
	 * @Description: TODO
	 * @param i
	 * @return
	 * @return: PageInfo<Link>
	 */
	PageInfo<Link> list(Integer i);

	/**
	 * 
	 * @Title: get 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: Object
	 */
	Link get(Integer id);

	/**
	 * 
	 * @Title: delete 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: Integer
	 */
	Integer delete(Integer id);

	/**
	 * 
	 * @Title: update 
	 * @Description: TODO
	 * @param link
	 * @return: void
	 */
	Integer update(@Valid Link link);

	/**
	 * 
	 * @Title: add 
	 * @Description: TODO
	 * @param link
	 * @return: void
	 */
	Integer add(@Valid Link link);

}
