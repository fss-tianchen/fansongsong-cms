package com.fansongsong.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @ClassName: ControllerInterceptor 
 * @Description: TODO
 * @author: Creazy丿绝情
 * @date: 2019年11月19日 下午2:11:19
 */
@ControllerAdvice
public class ControllerInterceptor {
	
	@ResponseBody
	@ExceptionHandler(CmcException.class)
	public MsgResult  interceptorException(CmcException excetion) {
		System.out.println(" 错误是 "  + excetion);
		return new MsgResult(100,excetion.getMessage(),null);
	}	
	
	@ExceptionHandler(CmcExceptionHtml.class)
	public ModelAndView  interceptorExceptionHtml(CmcExceptionHtml excetion) {		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error");// 跳转到jsp
		modelAndView.addObject("errorInfo", excetion.getMessage());
		return modelAndView;		
	}	
	
	
	
}
