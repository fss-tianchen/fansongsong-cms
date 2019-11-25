package com.fansongsong.entity;

import org.hibernate.validator.constraints.Length;

public class Url {
	
	@Length(max=20,min=5)
	private String url;
	
	@Length(max=8,min=3)
	private String name;

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
	
	

}
