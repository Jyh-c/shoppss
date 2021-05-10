package com.ambow.pss.controller;

import com.ambow.pss.model.SysUser;
import com.ambow.pss.service.SysUserService;
import com.ambow.pss.service.impl.SysUserServiceImpl;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @author JYH
 * @version 1.0
 * @date 2020/9/5 21:46
 */
@WebServlet(name = "SysUserServlet",urlPatterns = "/sysuser.do")
public class SysUserServlet extends HttpServlet {

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if("update".equals(action)){
            update(request, response);
        }else if("delete".equals(action)){
            delete(request, response);
        }else if("queryById".equals(action)){
            queryById(request, response);
        }else if("queryBlurry".equals(action)){
            queryBlurry(request, response);
        }else if("queryAll".equals(action)){
            queryAll(request, response);
        }else if("addUser".equals(action)){
            addUser(request, response);
        }
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        Date date = new Date();
        String userName = request.getParameter("user_name");
        int roleId = Integer.valueOf(request.getParameter("role_id"));
        String loginAccount = request.getParameter("login_account");
        String password = request.getParameter("password");
        String tel = request.getParameter("tel");
        String wxAccount = request.getParameter("wx_account");
        String idNo = request.getParameter("id_no");
        String address = request.getParameter("address");

        SysUser sysUser = new SysUser(0,roleId,userName,loginAccount,password,tel,
                wxAccount,idNo,address, date,date,true,null,null,null);
        sysUserService.addUser(sysUser);

        session.setAttribute("userList",sysUserService.queryAll(""));
        request.getRequestDispatcher("view/user/user_list.jsp").forward(request,response);
    }

    private void queryAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        session.setAttribute("userList", sysUserService.queryAll(""));
        request.getRequestDispatcher("view/user/user_list.jsp").forward(request, response);
    }

    private void queryBlurry(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        String keyword = request.getParameter("keyword");
        session.setAttribute("userList",sysUserService.queryAll(keyword));
        request.getRequestDispatcher("view/user/user_list.jsp").forward(request,response);
    }

    private void queryById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int  id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        SysUser user = sysUserService.queryById(id);
        session.setAttribute("user",user);
        request.getRequestDispatcher("view/user/user_modify.jsp").forward(request,response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        Date date = new Date();
        int id = Integer.parseInt(request.getParameter("id"));
        String userName = request.getParameter("user_name");
        String loginAccount = request.getParameter("login_account");
        String password = request.getParameter("password");
        String tel = request.getParameter("tel");
        String wxAccount = request.getParameter("wx_account");
        String idNo = request.getParameter("id_no");
        String address = request.getParameter("address");
        int roleId = Integer.valueOf(request.getParameter("role_id"));
        SysUser sysUser = new SysUser(id,roleId,userName,loginAccount,password,tel,
                wxAccount,idNo,address, null,date,true,null,null,null);
        sysUserService.update(sysUser);
        System.out.println(sysUser);
        session.setAttribute("userList",sysUserService.queryAll(""));
        request.getRequestDispatcher("view/user/user_list.jsp").forward(request,response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        int  id = Integer.parseInt(request.getParameter("id"));
        sysUserService.delete(id);
        System.out.println("删除的id为"+id);
        List<SysUser> userList = sysUserService.queryAll("");
        session.setAttribute("userList",userList);
        request.getRequestDispatcher("view/user/user_list.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private SysUserService sysUserService = new SysUserServiceImpl();
}
