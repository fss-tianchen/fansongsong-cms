package com.fansongsong.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

/**
 *  收藏
 * @author zhuzg
 *
 */

public class Collect {
	
	private Integer id;	 
	private Integer userId;
	 
	@Length(min=2,max=128)
	private String url;
	
	@Length(min=2,max=30)
	private String name; 
	private Date created;
 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	

}
