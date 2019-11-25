package com.fansongsong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.fansongsong.entity.Collect;

public interface CollectMapper {

	/**
	 * 
	 * @Title: list 
	 * @Description: TODO
	 * @param userId
	 * @return
	 * @return: List<Collect>
	 */
	@Select("SELECT * FROM cms_collect "
			+ " WHERE userId=#{userId} "
			+ " ORDER BY created DESC")
	List<Collect> list(Integer userId);

	/**
	 * 
	 * @Title: get 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: Collect
	 */
	@Select("SELECT * FROM cms_collect WHERE id=#{value} ")
	Collect get(Integer id);

	/**
	 * 
	 * @Title: delete 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: Integer
	 */
	@Delete("DELETE  FROM cms_collect WHERE id=#{value} ")
	Integer delete(Integer id);

	/**
	 * 
	 * @Title: add 
	 * @Description: TODO
	 * @param collect
	 * @return
	 * @return: Integer
	 */
	@Insert("INSERT INTO cms_collect (userId,url,name,created) "
			+ " VALUES(#{userId},#{url},#{name},now())")
	Integer add(Collect collect);

	/**
	 * 
	 * @Title: update 
	 * @Description: TODO
	 * @param collect
	 * @return
	 * @return: Integer
	 */
	@Update("UPDATE cms_collect set url=#{url},name=#{name} "
			+ "	WHERE id=#{id}")
	Integer update(Collect collect);

}
