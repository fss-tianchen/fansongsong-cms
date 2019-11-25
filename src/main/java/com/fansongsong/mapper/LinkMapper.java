package com.fansongsong.mapper;

import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.fansongsong.entity.Link;

public interface LinkMapper {

	/**
	 * 
	 * @Title: list 
	 * @Description: TODO
	 * @return
	 * @return: List<Link>
	 */
	@Select("SELECT * FROM cms_link ORDER BY created DESC")
	List<Link> list();

	/**
	 * 
	 * @Title: get 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: Link
	 */
	@Select("SELECT * FROM cms_link WHERE id=#{value} ")
	Link get(Integer id);

	/**
	 * 
	 * @Title: delete 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: Integer
	 */
	@Delete("DELETE  FROM cms_link WHERE id=#{value} ")
	Integer delete(Integer id);

	/**
	 * 
	 * @Title: update 
	 * @Description: TODO
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
	 * @Description: TODO
	 * @param link
	 * @return
	 * @return: Integer
	 */
	@Insert("INSERT INTO cms_link (url,name,created) "
			+ " VALUES(#{url},#{name},now())")
	Integer add(@Valid Link link);

}
