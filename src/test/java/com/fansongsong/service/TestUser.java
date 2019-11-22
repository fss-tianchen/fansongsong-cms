package com.fansongsong.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fansongsong.entity.User;
import com.fansongsong.service.UserService;

public class TestUser  extends TestBase{
	
	@Autowired
	UserService userService;
	
	@Test
	public void testLogin() {
		User user = new User();
		user.setUsername("wangzhenlin");
		user.setPassword("123456");
		User login = userService.login(user);
		if(login==null)
			System.out.println("登录失败");
		else {
			System.out.println("登录成功");
		}
	}
	
	@Test
	public void testRegister() {
		User user = new User();
		user.setUsername("wangzhenlin");
		user.setPassword("123456");
		userService.register(user);
		User xiaojian = new User();
		xiaojian.setUsername("xiaojian");
		xiaojian.setPassword("123456");
		userService.register(xiaojian);
	}

}
