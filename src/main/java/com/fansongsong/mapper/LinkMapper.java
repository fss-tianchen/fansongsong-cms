package com.fansongsong.mapper;

import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.fansongsong.entity.Link;

/**
 * 
 * @ClassName: LinkMapper 
 * @Description: TODO
 * @author: Creazy丿绝情
 * @date: 2019年11月26日 下午6:23:55
 */
public interface LinkMapper {

	/**
	 * 
	 * @Title: get 
	 * @Description: 查询友情链接
	 * @param id
	 * @return
	 * @return: Link
	 */
	@Select("SELECT * FROM cms_link WHERE id=#{value} ")
	Link get(Integer id);

	/**
	 * 
	 * @Title: list 
	 * @Description: 获取友情链接列表
	 * @return
	 * @return: List<Link>
	 */
	@Select("SELECT * FROM cms_link ORDER BY created DESC")
	List<Link> list();

	/**
	 * 
	 * @Title: delete 
	 * @Description: 删除友情链接
	 * @param id
	 * @return
	 * @return: Integer
	 */
	@Delete("DELETE  FROM cms_link WHERE id=#{value} ")
	Integer delete(Integer id);

	/**
	 * 
	 * @Title: update 
	 * @Description: 修改友情链接
	 * @param link
	 * @return
	 * @return: Integer
	 */
	@Update("UPDATE cms_link set url=#{url},name=#{name} "
			+ "	WHERE id=#{id}")
	Integer update(@Valid Link link);

	/**
	 * 
	 * @Title: add 
	 * @Description: 添加友情链接
	 * @param link
	 * @return
	 * @return: Integer
	 */
	@Insert("INSERT INTO cms_link (url,name,created) "
			+ " VALUES(#{url},#{name},now())")
	Integer add(@Valid Link link);

}
