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
 * @ClassName: LinkController 
 * @Description: TODO
 * @author: Creazy丿绝情
 * @date: 2019年11月26日 下午6:14:05
 */
@Controller
@RequestMapping("link")
public class LinkController {
	
	@Autowired
	private LinkService linkService;
	
	/**
	 * 
	 * @Title: list 
	 * @Description: 链接列表
	 * @param request
	 * @param page
	 * @return
	 * @return: String
	 */
	@RequestMapping("list")
	public String list(HttpServletRequest request, @RequestParam(defaultValue="1") Integer page) {
		PageInfo info = linkService.list(page);
		request.setAttribute("info", info);
		return "admin/link/list";
	}
	
	/**
	 * 
	 * @Title: add 
	 * @Description: 跳转到添加友情链接页面
	 * @param request
	 * @return
	 * @return: String
	 */
	@GetMapping("add")
	public String add(HttpServletRequest request) {
		request.setAttribute("link", new Link());
		return "admin/link/add";	 
	}
	
	/**
	 * 
	 * @Title: add 
	 * @Description: 跳转到修改友情链接页面
	 * @param request
	 * @param id
	 * @return
	 * @return: String
	 */
	@GetMapping("update")
	public String add(HttpServletRequest request, Integer id) {
		request.setAttribute("link", linkService.get(id));
		return "admin/link/update";	 
	}
	
	/**
	 * 
	 * @Title: delete 
	 * @Description: 删除友情链接
	 * @param request
	 * @param id
	 * @return
	 * @return: MsgResult
	 */
	@ResponseBody
	@RequestMapping("delete")
	public MsgResult delete(HttpServletRequest request, Integer id) {
		Integer result = linkService.delete(id);
		if(result<1) {
			return new MsgResult(2,"删除失败",null);
		}else {
			return new MsgResult(1,"删除失败",null);
		}
	}
	
	/**
	 * 
	 * @Title: add 
	 * @Description: 修改友情链接
	 * @param request
	 * @param id
	 * @return
	 * @return: String
	 */
	@PostMapping("update")
	public String update(HttpServletRequest request, @Valid  @ModelAttribute("link") Link link,	BindingResult result) {
		if(!StringUtils.isHttpUrl(link.getUrl())) {
			result.rejectValue("url", "不是合法的url", "不是合法的url");
		}
		// 有错误 还在原来的页面
		if(result.hasErrors()) {
			//request.setAttribute("link", link);
			return "admin/link/update";	
		}
		
		linkService.update(link);
		
		// 没有错误跳转到列表页面
		return "redirect:list";
	}
	
	
	/**
	 * 
	 * @Title: add 
	 * @Description: 添加友情链接
	 * @param request
	 * @param link
	 * @param result
	 * @return
	 * @return: String
	 */
	@PostMapping("add")
	public String add(HttpServletRequest request, @Valid  @ModelAttribute("link") Link link, BindingResult result) {
		if(!StringUtils.isHttpUrl(link.getUrl())) {
			result.rejectValue("url", "不是合法的url", "不是合法的url");
		}
		// 有错误 还在原来的页面
		if(result.hasErrors()) {
			request.setAttribute("link", link);
			return "admin/link/add";	
		}
		linkService.add(link);
		// 没有错误跳转到列表页面
		return "redirect:list";
	}
	
	

}
