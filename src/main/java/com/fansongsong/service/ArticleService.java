package com.fansongsong.service;

import java.util.List;

import com.fansongsong.entity.Article;
import com.fansongsong.entity.Comment;
import com.github.pagehelper.PageInfo;

public interface ArticleService {

	/**
	 * 
	 * @Title: getNewArticles 
	 * @Description: 获取最新文章
	 * @param i  获取个数
	 * @return
	 * @return: List<Article>
	 */
	List<Article> getNewArticles(Integer i);

	/**
	 * 
	 * @Title: hotList 
	 * @Description: 获取热门文章
	 * @param page
	 * @return
	 * @return: PageInfo<Article>
	 */
	PageInfo<Article> hotList(Integer page);

	/**
	 * 
	 * @Title: getById 
	 * @Description: 根据文章Id获取文章内容
	 * @param id
	 * @return
	 * @return: Article
	 */
	Article getById(Integer id);

	/**
	 * 
	 * @Title: listBuCat 
	 * @Description: 根据频道或者分类获取文章
	 * @param chnId
	 * @param categoryId
	 * @param page
	 * @return
	 * @return: PageInfo<Article>
	 */
	PageInfo<Article> listByCat(Integer chnId, Integer categoryId, Integer page);

	/**
	 * 
	 * @Title: listByUser 
	 * @Description: 获取文章列表
	 * @param page
	 * @param id
	 * @return
	 * @return: PageInfo<Article>
	 */
	PageInfo<Article> listByUser(Integer page,Integer userId);

	/**
	 * 
	 * @Title: delete 
	 * @Description: 根据ID删除文章
	 * @param id
	 * @return
	 * @return: Integer
	 */
	Integer delete(Integer id);

	/**
	 * 
	 * @Title: checkExist 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: Article
	 */
	Article checkExist(Integer id);

	/**
	 * 
	 * @Title: getPageList 
	 * @Description: 文章审核
	 * @param status
	 * @param page
	 * @return
	 * @return: PageInfo<Article>
	 */
	PageInfo<Article> getPageList(Integer status, Integer page);

	/**
	 * 
	 * @Title: getDetailById 
	 * @Description: 根据id获取文章
	 * @param id
	 * @return
	 * @return: Article
	 */
	Article getDetailById(Integer id);

	/**
	 * 
	 * @Title: apply 
	 * @Description: 处理文章
	 * @param id
	 * @param status
	 * @return
	 * @return: Integer
	 */
	Integer apply(Integer id, Integer status);

	/**
	 * 
	 * @Title: setHot 
	 * @Description: 设置热门与否
	 * @param id
	 * @param status
	 * @return
	 * @return: Integer
	 */
	Integer setHot(Integer id, Integer status);
	
	/**
	 * 
	 * @Title: add 
	 * @Description: 发布文章
	 * @param article
	 * @return
	 * @return: Integer
	 */
	Integer add(Article article);

	/**
	 * 
	 * @Title: update 
	 * @Description: TODO
	 * @param article
	 * @return
	 * @return: Integer
	 */
	Integer update(Article article);

	/**
	 * 
	 * @Title: favarite 
	 * @Description: 用户收藏文章
	 * @param id
	 * @param id2
	 * @return
	 * @return: Integer
	 */
	Integer favarite(Integer id, Integer id2);

	/**
	 * 
	 * @Title: commentlist 
	 * @Description: 获取评论
	 * @param id
	 * @param page
	 * @return
	 * @return: PageInfo<Comment>
	 */
	PageInfo<Comment> commentlist(Integer id, Integer page);

	/**
	 * 
	 * @Title: getImgArticles 
	 * @Description: 获取最新的10篇图片文章
	 * @param i
	 * @return
	 * @return: List<Article>
	 */
	List<Article> getImgArticles(Integer i);
	
	Integer comment(Integer userId, Integer articleId, String content);
}
