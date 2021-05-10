package com.ambow.pss.init;

import com.ambow.pss.util.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Servlet implementation class InitServlet
 * @author JYH
 */
@WebServlet(urlPatterns = "/init.do", loadOnStartup = 0)
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		try {
			super.init();
			System.out.println("开始加载数据库配置资源...");
			InputStream is = InitServlet.class.getResourceAsStream("/db.properties");
			Properties pro = new Properties();
			pro.load(is);
			JDBCUtil jdbcUtil = JDBCUtil.getInstance();
			jdbcUtil.initFromProperties(pro);
			System.out.println("完成加载数据库配置资源...");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
