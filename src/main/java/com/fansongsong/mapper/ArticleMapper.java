package com.fansongsong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.fansongsong.entity.Article;
import com.fansongsong.entity.Comment;

public interface ArticleMapper {

	/**
	 * 
	 * @Title: newList 
	 * @Description: 获取最新文章
	 * @param i
	 * @return
	 * @return: List<Article>
	 */
	List<Article> newList(Integer i);

	/**
	 * 
	 * @Title: hostList 
	 * @Description: 获取热门文章
	 * @return
	 * @return: List<Article>
	 */
	List<Article> hostList();

	/**
	 * 
	 * @Title: getById 
	 * @Description:  获取文章
	 * @param id
	 * @return
	 * @return: Article
	 */
	Article getById(Integer id);

	/**
	 * 
	 * @Title: listByCat 
	 * @Description: 根据频道获取文章
	 * @param chnId
	 * @param categoryId
	 * @return
	 * @return: List<Article>
	 */
	List<Article> listByCat(@Param("chnId") Integer chnId,@Param("categoryId") int categoryId);

	/**
	 * 
	 * @Title: listByUser 
	 * @Description: TODO
	 * @param userId
	 * @return
	 * @return: List<Article>
	 */
	List<Article> listByUser(Integer userId);

	@Update(" UPDATE cms_article SET  deleted=1 WHERE id=#{value} ")
	Integer delete(Integer id);

	/** 
	 * @Title: checkExist 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: Article
	 */
	@Select("SELECT id, title,user_id AS userId FROM cms_article WHERE id = #{value}")
	@ResultType(Article.class)
	Article checkExist(Integer id);

	/** 
	 * 
	 * @Title: listByStatus 
	 * @Description: 管理员根据状态查询文章
	 * @param status
	 * @return
	 * @return: List<Article>
	 */
	List<Article> listByStatus(Integer status);

	/**
	 * 
	 * @Title: getDetailById 
	 * @Description: 获取文章详情，不考虑状态
	 * @param id
	 * @return
	 * @return: Article
	 */
	Article getDetailById(Integer id);

	/**
	 * 
	 * @Title: apply 
	 * @Description:  审核文章
	 * @param id
	 * @param status
	 * @return
	 * @return: Integer
	 */
	@Update(" UPDATE cms_article SET  status=#{status} "
			+ " WHERE id=#{id} ")
	Integer apply(@Param("id") Integer id,@Param("status") Integer status);

	/**
	 * 
	 * @Title: setHot 
	 * @Description: 设置热门
	 * @param id
	 * @param status
	 * @return
	 * @return: Integer
	 */
	@Update(" UPDATE cms_article SET  hot=#{status} "
			+ " WHERE id=#{id} ")
	Integer setHot(@Param("id") Integer id,@Param("status") Integer status);

	/**
	 * 
	 * @Title: add 
	 * @Description: 添加文章
	 * @param article
	 * @return
	 * @return: Integer
	 */
	@Insert("INSERT INTO cms_article("
			+ " title,content,picture,channel_id,category_id,"
			+ " user_id,hits,hot,status,deleted,"
			+ " created,updated,commentCnt,articleType) "
			+ " values("
			+ " #{title},#{content},#{picture},#{channelId},#{categoryId},"
			+ "#{userId},#{hits},#{hot},#{status},#{deleted},"
			+ "now(),now(),#{commentCnt},"
			+ "#{articleType,typeHandler=org.apache.ibatis.type.EnumOrdinalTypeHandler,"
			+ "jdbcType=INTEGER,javaType=com.fansongsong.entity.TypeEnum})")
	Integer add(Article article);

	/**
	 * 
	 * @Title: update 
	 * @Description: TODO
	 * @param article
	 * @return
	 * @return: Integer
	 */
	@Update("UPDATE cms_article SET title=#{title},content=#{content},"
			+ "picture=#{picture},channel_id=#{channelId},"
			+ "category_id=#{categoryId},status=0,updated=now() WHERE id=#{id}")
	Integer update(Article article);

	/**
	 * 
	 * @Title: favorite 
	 * @Description: 用户收藏文章
	 * @param userId
	 * @param artId
	 * @return
	 * @return: Integer
	 */
	/*@Insert(" REPLACE cms_favorite(user_id,article_id,created) "
			+ "VALUES(#{userId},#{articleId},now())")
	Integer favorite(@Param("userId")Integer userId, @Param("articleId")Integer artId);*/

	/**
	 * 
	 * @Title: getImgArticles 
	 * @Description: 获取10篇图片文章
	 * @param num
	 * @return
	 * @return: List<Article>
	 */
	List<Article> getImgArticles(Integer num);

	/**
	 * 
	 * @Title: commentlist 
	 * @Description: TODO
	 * @param articleId
	 * @return
	 * @return: List<Comment>
	 */
	@Select("SELECT * FROM cms_comment WHERE articleId=#{value}")
	List<Comment> commentlist(Integer articleId);

	/**
	 * 
	 * @Title: addComment 
	 * @Description: 添加评论
	 * @param userId
	 * @param articleId
	 * @param content
	 * @return
	 * @return: Integer
	 */
	@Insert("INSERT INTO cms_comment (articleId,userId,content,created)"
			+ " VALUES(#{articleId},#{userId},#{content},now())")
	Integer addComment(Integer userId, Integer articleId, String content);

	/**
	 * 
	 * @Title: increaseCommentCnt 
	 * @Description: 评论数目自增一
	 * @param articleId
	 * @return: void
	 */
	@Update("UPDATE cms_article set commentCnt=commentCnt+1 WHERE id=#{value} ")
	void increaseCommentCnt(Integer articleId);

}
