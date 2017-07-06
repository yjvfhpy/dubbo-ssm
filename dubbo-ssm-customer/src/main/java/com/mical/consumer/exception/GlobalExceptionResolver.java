/**
 * 
 */
package com.mical.consumer.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description:全局异常捕获
 * @author micalliu
 * @date 2017年6月28日
 */
public class GlobalExceptionResolver implements HandlerExceptionResolver {

	/*
	 * 
	 * 这里有2种选择 1.跳转到定制化的错误页面 2.返回json格式的错误信息
	 *
	 */
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		System.out.println("访问" + request.getRequestURI() + "&" + request.getQueryString() + "发生错误, 错误信息:" + ex.getMessage());
		System.out.println(getExceptionDetail(ex));
		ModelAndView view = new ModelAndView();
		view.addObject("exMsg", ex.getMessage());
		view.addObject("exType", ex.getClass().getSimpleName().replace("\"", "'"));

		view.setViewName("/common/error");

		return view;
	}

	/**
	 * 获取exception详情信息
	 * @param e  Excetipn type
	 * @return String 
	 */
	public static String getExceptionDetail(Exception e) {
		StringBuffer msg = new StringBuffer("null");
		if (e != null) {
			msg = new StringBuffer("");
			String message = e.toString();
			int length = e.getStackTrace().length;
			if (length > 0) {
				msg.append(message + "\n");
				for (int i = 0; i < length; i++) {
					msg.append("\t" + e.getStackTrace()[i] + "\n");
				}
			} else {
				msg.append(message);
			}
		}
		return msg.toString();
	}

}
