package com.fansongsong.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fansongsong.common.CmsAssert;
import com.fansongsong.common.ConstantClass;
import com.fansongsong.common.MsgResult;
import com.fansongsong.entity.Article;
import com.fansongsong.entity.Channel;
import com.fansongsong.entity.Collect;
import com.fansongsong.entity.Image;
import com.fansongsong.entity.TypeEnum;
import com.fansongsong.entity.User;
import com.fansongsong.service.ArticleService;
import com.fansongsong.service.ChannelService;
import com.fansongsong.service.CollectService;
import com.fansongsong.service.UserService;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;

@Controller
@RequestMapping("user")
public class UserController {
	
	Logger log = Logger.getLogger(UserController.class);	
	
	@Value("${upload.path}")
	String updloadPath;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ArticleService articleService;	
	
	@Autowired
	private ChannelService channelService;
	
	@Autowired
	private CollectService collectService;

	private SimpleDateFormat dateFormat;

	
	//  httppxxxx://user/hello
	@RequestMapping(value="hello",method=RequestMethod.GET)
	public String tet(HttpServletRequest request) {
		request.setAttribute("info", "hello");
		
		return "user/test";
	}
	
	/**
	 * 
	 * @Title: register 
	 * @Description: 跳转到注册页面
	 * @param request
	 * @return
	 * @return: String
	 */
	@RequestMapping(value="register",method=RequestMethod.GET)
	public String register(HttpServletRequest request) {
		return "user/register";
	}
	
	/**
	 * 
	 * @Title: register 
	 * @Description: 处理提交的注册用户数据
	 * @param request
	 * @param user
	 * @return
	 * @return: String
	 */
	@RequestMapping(value="register",method=RequestMethod.POST)
	public String register(HttpServletRequest request,User user) {
		int result = userService.register(user);
		CmsAssert.AssertTrue(result>0,"用户注册失败，请稍后再试");
		return "redirect:/user/login";
	}
	
	/**
	 * 
	 * @Title: login 
	 * @Description: 跳转到登录页面
	 * @param request
	 * @return
	 * @return: String
	 */
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String login(HttpServletRequest request) {
		return "user/login";
	}
	
	/***
	 * 
	 * @Title: login 
	 * @Description: 登录到用户页面
	 * @param request
	 * @param user
	 * @return
	 * @return: String
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String login(HttpServletRequest request,User user) {
		User loginUser  = userService.login(user);
		// 用户存在 登录成功
		if(loginUser!=null) {
			request.getSession().setAttribute(ConstantClass.USER_KEY, loginUser);
			
			//return "redirect:/";
			return loginUser.getRole()==ConstantClass.USER_ROLE_ADMIN
					?"redirect:/admin/index":"redirect:/user/home";
		}else {
			request.setAttribute("errorMsg", "用户名或密码错误！！");
			request.setAttribute("user", user);
			return "user/login";
		}
	}
	
	/**
	 * 
	 * @Title: logout 
	 * @Description: 用户注销
	 * @param request
	 * @return
	 * @return: String
	 */
	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute(ConstantClass.USER_KEY);
		return "redirect:/";
	}
	
	/**
	 * 
	 * @Title: checkname 
	 * @Description: TODO
	 * @param username
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@RequestMapping("checkname")
	public boolean checkname(String username) {
		return null==userService.findByName(username);
	}
	
	/**
	 * 
	 * @Title: home 
	 * @Description: TODO
	 * @param request
	 * @return
	 * @return: String
	 */
	@RequestMapping("home")
	public String home(HttpServletRequest request) {
		return "/user/home";
	}
	
	/**
	 * 
	 * @Title: updateArticle 
	 * @Description: TODO
	 * @param request
	 * @param id
	 * @return
	 * @return: String
	 */
	@GetMapping("updateArticle")
	public String updateArticle(HttpServletRequest request,int id) {
		// 获取文章的详情 用于回显
		Article article = articleService.getDetailById(id);
		request.setAttribute("article", article);
		request.setAttribute("content1", htmlspecialchars(article.getContent()));
		
		System.out.println(" 将要修改文章 " + article);
		 
		// 获取所有的频道
		List<Channel> channels =  channelService.list();
		request.setAttribute("channels", channels);
		
		return "article/update";
	}
	
	/**
	 * 
	 * @Title: updateArticle 
	 * @Description: TODO
	 * @param request
	 * @param file
	 * @param article
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 * @return: MsgResult
	 */
	@ResponseBody
	@PostMapping("updateArticle")
	public MsgResult updateArticle(HttpServletRequest request,
			MultipartFile file, Article article) throws IllegalStateException, IOException {
		//文章id 是否存在
		
		//用户是否有权限修改
		
		//
		if(!file.isEmpty()) {
			String picUrl = processFile(file);
			article.setPicture(picUrl);
		}
		
		Integer result = articleService.update(article);
		
		if(result>0) {
			// 成功
			return new MsgResult(1,"",null);
		}else {
			return new MsgResult(2,"失败",null);
		}
		
	}
	
	/**
	 * 
	 * @Title: postArticle 
	 * @Description: 进入发表文章界面
	 * @param request
	 * @return
	 * @return: String
	 */
	@GetMapping("postArticle")
	public String postArticle(HttpServletRequest request) {
		
		// 获取所有的频道
		List<Channel> channels =  channelService.list();
		request.setAttribute("channels", channels);
		return "article/publish";
	}
	
	/**
	 * 
	 * @Title: postArticle 
	 * @Description: TODO
	 * @param request
	 * @param file
	 * @param article
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 * @return: MsgResult
	 */
	@ResponseBody
	@PostMapping("postArticle")
	public MsgResult postArticle(HttpServletRequest request, MultipartFile file,Article article) throws IllegalStateException, IOException{
		
		if(!file.isEmpty()) {
			String fileUrl = processFile(file);
			article.setPicture(fileUrl);
		}
		User loginUser  = (User)request.getSession().getAttribute(ConstantClass.USER_KEY);
		article.setUserId(loginUser.getId());
		
		int result = articleService.add(article);
		if(result>0) {
			return new MsgResult(1, "处理成功",null);
		}else {
			return new MsgResult(2, "添加失败，请稍后再试试！",null);
		}
	}
	
	/**
	 * 
	 * @Title: processFile 
	 * @Description: 保存文件的相对路径
	 * @param file
	 * @return
	 * @return: String
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
    private String processFile(MultipartFile file) throws IllegalStateException, IOException {
    	
    	log.info("updloadPath is "  + updloadPath);
    	//1 求扩展名  "xxx.jpg"
    	String suffixName =  file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
    	String fileNamePre = UUID.randomUUID().toString();
    	// 计算出新的文件名称
    	String fileName = fileNamePre + suffixName;
    	
    	dateFormat = new SimpleDateFormat("yyyyMMdd");
    	String path = dateFormat.format(new Date());
    	File pathFile  = new File(updloadPath + "/" + path);
    	if(!pathFile.exists()) {
    		pathFile.mkdirs();
    	}
    	String newFileName = updloadPath + "/"+ path + "/" + fileName;
    	file.transferTo(new File(newFileName));
    	
    	return path + "/" + fileName ;
    }
    
    /**
	 * 
	 * @Title: myarticles 
	 * @Description: 获取文章列表
	 * @param request
	 * @param page
	 * @return
	 * @return: String
	 */
	@RequestMapping("myarticles")
	public String myarticles(HttpServletRequest request,
			@RequestParam(defaultValue="1") int page) {
		
		User loginUser = (User)request.getSession().getAttribute(ConstantClass.USER_KEY);
		
		PageInfo<Article> pageInfo=  articleService.listByUser(page,loginUser.getId());
		request.setAttribute("pageInfo", pageInfo);
		return "user/myarticles";
	}
	
	/**
	 * 
	 * @Title: delArticle 
	 * @Description: 删除文章
	 * @param request
	 * @param id
	 * @return
	 * @return: MsgResult
	 */
	@ResponseBody
	@RequestMapping("delArticle")
	public MsgResult delArticle(HttpServletRequest request,int id){
		
		CmsAssert.AssertTrue(id>0, "文章id必须大于0");
		Article article =  articleService.checkExist(id);
		CmsAssert.AssertTrue(article!=null, "该文章不存在");
		
		User loginUser = (User)request.getSession().getAttribute(ConstantClass.USER_KEY);
		CmsAssert.AssertTrue(
				loginUser.getRole()==ConstantClass.USER_ROLE_ADMIN 
				|| loginUser.getId()==article.getUserId(),
				"只有管理员和文章的作者能删除文章");
		
		int result = articleService.delete(id);
		CmsAssert.AssertTrue(result>0,"文章删除失败");
		return new MsgResult(1,"删除成功",null);
		
	}
	
	private String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}
	
	/*@ResponseBody
	@RequestMapping("favarite")
	public MsgResult favarite(HttpServletRequest request, Integer id) {
		CmsAssert.AssertTrue(id>0, "id不合法");
		User user = (User)request.getSession().getAttribute(ConstantClass.USER_KEY);
		CmsAssert.AssertTrue(user!=null, "亲，您尚未登录");
		Integer result = articleService.favarite(user.getId(), id);
		CmsAssert.AssertTrue(result>0, "很遗憾，收藏失败");
		return new MsgResult(1, "恭喜，收藏成功", null);
	}*/
	
	/**
	 * 
	 * @Title: postImg 
	 * @Description: TODO
	 * @param request
	 * @return
	 * @return: String
	 */
	@GetMapping("postImg")
	public String postImg(HttpServletRequest request) {		
		// 获取所有的频道
		List<Channel> channels =  channelService.list();
		request.setAttribute("channels", channels);	
		return "article/postimg";		
	}
	
	@ResponseBody
	@RequestMapping(value = "postImg",method=RequestMethod.POST)
	public MsgResult postImg(HttpServletRequest request,Article article,
			MultipartFile file[],String desc[]) throws IllegalStateException, IOException {
		
		User loginUser = (User)request.getSession().getAttribute(ConstantClass.USER_KEY);
		List<Image> list = new ArrayList<>();
		// 遍历处理每个上传图片 并存入list
		for (int i = 0; i < file.length && i < desc.length; i++) {
			String url = processFile(file[i]);
			Image image = new Image();
			image.setDesc(desc[i]);
			image.setUrl(url);
			list.add(image);
		}
		Gson gson = new Gson();		
		//设置作者
		article.setUserId(loginUser.getId());
		article.setContent(gson.toJson(list));
		//设置文章类型 是图片
		article.setArticleType(TypeEnum.IMG);
		
		Integer add = articleService.add(article);
		if(add > 0) {
			return new MsgResult(1,"发布成功11",null);
		}else {
			return new MsgResult(2,"发布失败11",null);
		}	
	}
	
	/**
	 * 
	 * @Title: collect 
	 * @Description: TODO
	 * @param request
	 * @param collect
	 * @return
	 * @return: MsgResult
	 */
	/*@ResponseBody
	@RequestMapping("collect")
	public MsgResult collect(HttpServletRequest request, Collect collect) {
		//CmsAssert.AssertTrue(id>0, "id 不合法");
		User loginUser = (User)request.getSession().getAttribute(ConstantClass.USER_KEY);
		CmsAssert.AssertTrue(loginUser!=null, "亲，您尚未登录！！");
		
		if(collect.getName().length()>20) {
			collect.setName(collect.getName().substring(0, 20) + "...");
		}
		collect.setUserId(loginUser.getId());
		Integer result = collectService.add(collect);
		
		CmsAssert.AssertTrue(result>0, "很遗憾，加入收藏失败！！");
		return new MsgResult(1,"恭喜，收藏成功",null);
		
	}*/
}
