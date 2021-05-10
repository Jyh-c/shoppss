package com.ambow.pss.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author JYH
 * @version 1.0
 * @date 2020/8/25 0:15
 */
@WebFilter(
        filterName="CharacterEncodingFilter",
        urlPatterns="/*",
        initParams= {@WebInitParam(name="characterEncoding",value="utf-8")}
)
/*
@WebFilter(filterName = "CharacterEncodingFilter",urlPatterns = "")
@WebInitParam(name="characterEncoding",value="utf-8")
*/
public class CharacterEncodingFilter implements Filter {
    private String characterEncoding;
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        if(null!=characterEncoding) {
            req.setCharacterEncoding(characterEncoding);
            resp.setCharacterEncoding(characterEncoding);
        }
/*        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        if(user==null) {
            //session.setAttribute("weidenglu", "<script>alter('用户未登录，请先登录系统');</script>");
            response.sendRedirect(request.getContextPath()+"/login");
            System.out.println("当前未登录，过滤器执行跳转登录页过滤操作");
        }*/

        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        characterEncoding = config.getInitParameter("characterEncoding");
    }

}
