package com.fansongsong.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fansongsong.StringUtils;
import com.fansongsong.common.MsgResult;
import com.fansongsong.entity.Link;
import com.fansongsong.service.LinkService;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @author zhuzg
 *
 */
@Controller
@RequestMapping("link")
public class LinkController {
	
	@Autowired
	private LinkService linkService;
	
	/**
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("list")
	public String list(HttpServletRequest request, 
			@RequestParam(defaultValue="1") Integer page) {
		PageInfo info = linkService.list(page);
		request.setAttribute("info", info);
		return "amdin/link/list";
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@GetMapping("add")
	public String add(HttpServletRequest request) {
		request.setAttribute("link", new Link());
		return "amdin/link/add";	 
	}
	
	
	@GetMapping("update")
	public String add(HttpServletRequest request,Integer id) {
		request.setAttribute("link", linkService.get(id));
		return "amdin/link/update";	 
	}
	
	
	@ResponseBody
	@RequestMapping("delete")
	public MsgResult delete(HttpServletRequest request,int id) {
		int result = linkService.delete(id);
		if(result<1)
			return new MsgResult(2,"删除失败",null);
		
		return new MsgResult(1,"删除失败",null);
		
	}
	
	/**
	 * 
	 * @param request
	 * @param link
	 * @param result
	 * @return
	 */
	@PostMapping("update")
	public String update(HttpServletRequest request,
			@Valid  @ModelAttribute("link") Link link,
			BindingResult result
			) {
		
		if(!StringUtils.isHttpUrl(link.getUrl())) {
			result.rejectValue("url", "不是合法的url", "不是合法的url");
		}
		
		// 有错误 还在原来的页面
		if(result.hasErrors()) {
			//request.setAttribute("link", link);
			return "amdin/link/update";	
		}
		
		linkService.update(link);
		
		// 没有错误跳转到列表页面
		return "redirect:list";
	}
	
	
	/**
	 * 
	 * @param request
	 * @param link
	 * @param result
	 * @return
	 */
	@PostMapping("add")
	public String add(HttpServletRequest request,
			@Valid  @ModelAttribute("link") Link link,
			BindingResult result
			) {
		if(!StringUtils.isHttpUrl(link.getUrl())) {
			result.rejectValue("url", "不是合法的url", "不是合法的url");
		}
		
		// 有错误 还在原来的页面
		if(result.hasErrors()) {			
			request.setAttribute("link", link);
			return "amdin/link/add";	
		}
		linkService.add(link);		
		// 没有错误跳转到列表页面
		return "redirect:list";
	}

}
