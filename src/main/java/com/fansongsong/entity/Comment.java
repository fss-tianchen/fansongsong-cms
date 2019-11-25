package com.fansongsong.entity;

import java.util.Date;

/**
 * 评论
 * 
 * @author zhuzg
 *
 */
public class Comment {
	
	private Integer id;
	private Integer articleId;
	private Integer userId;
	private String content;
	private Date created;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	

}
