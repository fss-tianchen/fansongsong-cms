package com.fansongsong.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fansongsong.common.ConstantClass;
import com.fansongsong.entity.Article;
import com.fansongsong.entity.Comment;
import com.fansongsong.mapper.ArticleMapper;
import com.fansongsong.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ArticleServiceImpl  implements ArticleService{
	
	@Autowired
	ArticleMapper articleMapper;	

	@Override
	public List<Article> getNewArticles(Integer i) {
		// TODO Auto-generated method stub
		return articleMapper.newList(i);
	}

	@Override
	public PageInfo<Article> hotList(Integer page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, ConstantClass.PAGE_SIZE);
		return new PageInfo<Article>(articleMapper.hostList());
	}

	@Override
	public Article getById(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.getById(id);
	}

	@Override
	public PageInfo<Article> listByCat(Integer chnId, Integer categoryId, Integer page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, ConstantClass.PAGE_SIZE);
		return new PageInfo<Article>(articleMapper.listByCat(chnId,categoryId));
	}

	@Override
	public PageInfo<Article> listByUser(Integer page,Integer userId) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, ConstantClass.PAGE_SIZE);
		return new PageInfo<Article>(articleMapper.listByUser(userId));
	
	}

	@Override
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.delete(id);
	}

	/*
	 * (non Javadoc) 
	 * @Title: checkExist
	 * @Description: TODO
	 * @param id
	 * @return 
	 * @see com.fansongsong.service.ArticleService#checkExist(java.lang.Integer)
	 */
	@Override
	public Article checkExist(Integer id) {
		// TODO Auto-generated method stub
		return  articleMapper.checkExist(id);
	}

	/*
	 * (non Javadoc) 
	 * @Title: getPageList
	 * @Description: TODO
	 * @param status
	 * @param page
	 * @return 
	 * @see com.fansongsong.service.ArticleService#getPageList(int, java.lang.Integer)
	 */
	@Override
	public PageInfo<Article> getPageList(Integer status, Integer page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, ConstantClass.PAGE_SIZE);
		return new PageInfo<Article>(articleMapper.listByStatus(status));
	}
	
	@Override
	public Article getDetailById(Integer id) {
		// TODO Auto-generated method stub
		return  articleMapper.getDetailById(id);
	}

	@Override
	public Integer apply(Integer id, Integer status) {
		// TODO Auto-generated method stub
		return articleMapper.apply(id,status);
	}

	
	@Override
	public Integer setHot(Integer id, Integer status) {
		// TODO Auto-generated method stub
		return articleMapper.setHot(id,status);
	}

	@Override
	public Integer add(Article article) {
		// TODO Auto-generated method stub
		return articleMapper.add(article);
	}

	@Override
	public Integer update(Article article) {
		// TODO Auto-generated method stub
		return articleMapper.update(article);
	}

	/*@Override
	public Integer favarite(Integer userId, Integer artId) {
		// TODO Auto-generated method stub
		return articleMapper.favorite(userId, artId);
	}*/

	@Override
	public PageInfo<Comment> commentlist(Integer articleId, Integer page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page,10);
		return new PageInfo<Comment>(articleMapper.commentlist(articleId));
	}

	@Override
	public List<Article> getImgArticles(Integer num) {
		// TODO Auto-generated method stub
		return articleMapper.getImgArticles(num);
	}

	@Override
	public Integer comment(Integer userId, Integer articleId, String content) {
		// TODO Auto-generated method stub
		//插入评论表一条数据
		Integer result = articleMapper.addComment(userId, articleId, content);
		if(result>0) {
			// 让文章表中的评论数量自增1
			articleMapper.increaseCommentCnt(articleId);
		}else {
			return 0;
		}
		return result;
	}
}
