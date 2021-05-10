package com.ambow.pss.filter;

import com.ambow.pss.model.SysUser;
import com.ambow.pss.util.Constant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(filterName = "MyFilter", urlPatterns = "/*")
public class MyFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 修改编码方式
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String uri = req.getRequestURI();
//		resp.setContentType("text/html;charset=utf-8");
		if(uri.endsWith(".css")) {
			resp.setContentType("text/css;charset=utf-8");
		} else {
			resp.setContentType("text/html;charset=utf-8");
		}
		//放过login请求
		String action = req.getParameter("action");
		// 拦截未登录用户
		SysUser user = (SysUser) req.getSession().getAttribute("user");
		if(uri.endsWith(".do")) {
			if(Constant.LOGIN.equalsIgnoreCase(action)) {
				chain.doFilter(request, response);
			} else if(user != null) {
				chain.doFilter(request, response);
			} else {
				resp.sendRedirect("login.jsp");
			}
		} else if(uri.endsWith(".jsp")) {
			if(uri.endsWith("login.jsp")) {
				chain.doFilter(request, response);
			} else if(user != null) {
				chain.doFilter(request, response);
			} else {
				resp.sendRedirect("login.jsp");
			}
		} else {
			chain.doFilter(request, response);
		}
	}

}
