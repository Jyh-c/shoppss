package com.ambow.pss.controller;

import com.ambow.pss.model.SysUser;
import com.ambow.pss.service.SysUserService;
import com.ambow.pss.service.impl.SysUserServiceImpl;
import com.ambow.pss.util.Constant;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * Servlet implementation class UserServlet
 * @author JYH
 */
@WebServlet(urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SneakyThrows
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		//登陆
		if (Constant.LOGIN.equalsIgnoreCase(action)) {
			login(request, response);
		} else if (Constant.ADD.equalsIgnoreCase(action)) {
		}
	}

	/**
	 * 登陆
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//接收参数
		String loginAccount = request.getParameter("loginAccount");
		String password = request.getParameter("password");
		//调用service层，执行登陆
		SysUser user = sysUserService.login(loginAccount, password);
		//将user放入session作用域
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		//用户登录成功，重定向到系统首页
		response.sendRedirect("index.jsp");
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private Date date = new Date();
	private SysUserService sysUserService = new SysUserServiceImpl();

}
