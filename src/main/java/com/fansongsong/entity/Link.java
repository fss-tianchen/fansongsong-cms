package com.fansongsong.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

public class Link {
	
	private Integer id;
	
	@Length(max=255,min=5,message="长度超出范围")
	//@URL
	private String url;
	
	@Length(max=10,min=2)
	private String name;
	
	private Date created;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
