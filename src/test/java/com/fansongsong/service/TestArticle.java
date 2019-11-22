package com.fansongsong.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fansongsong.entity.Article;
import com.fansongsong.service.ArticleService;
import com.github.pagehelper.PageInfo;

public class TestArticle extends TestBase{

	@Autowired
	ArticleService articleService;
	
	@Test
	public void testHotList() {
		
		PageInfo<Article> hotList = articleService.hotList(1);
		List<Article> list = hotList.getList();
		list.forEach(a->{
			System.out.println(" a is " + a);
		});
		
		
	}
}
