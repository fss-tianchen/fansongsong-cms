package com.fansongsong.service;

import com.fansongsong.entity.Collect;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @ClassName: CollectService 
 * @Description: 收藏
 * @author: Creazy丿绝情
 * @date: 2019年11月25日 下午2:10:58
 */
public interface CollectService {

	/**
	 * 
	 * @Title: add 
	 * @Description: TODO
	 * @param collect
	 * @return
	 * @return: Integer
	 */
	Integer add(Collect collect);

	/**
	 * 
	 * @Title: list 
	 * @Description: TODO
	 * @param id
	 * @param page
	 * @return
	 * @return: PageInfo
	 */
	PageInfo list(Integer id, Integer page);

	/**
	 * 
	 * @Title: get 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: Object
	 */
	Object get(Integer id);

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
	 * @param collect
	 * @return
	 * @return: Integer
	 */
	Integer update( Collect collect);
}
