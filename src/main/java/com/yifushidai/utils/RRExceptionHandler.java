/*
package com.yifushidai.utils;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

*/
/**
 * 自定义异常处理器
 *//*

@Component
public class RRExceptionHandler implements HandlerExceptionResolver {
	protected Logger logger = LoggerFactory.getLogger(RRExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		R r = new R();
		try {
			response.setContentType("application/json;charset=utf-8");
			response.setCharacterEncoding("utf-8");

			if (ex instanceof RRException) {
				r.put("code", ((RRException) ex).getCode());
				r.put("msg", ((RRException) ex).getMessage());
			}else if(ex instanceof DuplicateKeyException){
				throw new RRException("数据已存在",508);
				*/
/*R r = R.error(508, "数据已存在");
				String json = JSON.toJSONString(r);
				response.getWriter().print(json);*//*

			}else if(ex instanceof MissingServletRequestParameterException){
				throw new RRException("缺少参数,"+ex.getMessage(),506);
				//R r = R.error(506,"缺少参数");
			}else if(ex instanceof MethodArgumentTypeMismatchException){
				throw new RRException("参数类型错误,"+ex.getMessage(),507);
				//R r = R.error(507,"参数类型错误");
			} else if(ex instanceof ConstraintViolationException){
				throw new RRException("参数校验错误,"+ex.getMessage(),511);
				//R r = R.error(511,"参数校验错误");
			}*/
/*else if(ex instanceof IllegalArgumentException ){

			}*//*
else{
				r = R.error();
			}

			//记录异常日志
			logger.error(ex.getMessage(), ex);
			String json = JSON.toJSONString(r);
			response.getWriter().print(json);
		} catch (Exception e) {
			logger.error("RRExceptionHandler 异常处理失败", e);
		}
		return new ModelAndView();
	}
}
*/
