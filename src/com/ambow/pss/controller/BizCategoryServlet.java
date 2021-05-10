package com.ambow.pss.controller;

import com.ambow.pss.model.Category;
import com.ambow.pss.service.BizCategoryService;
import com.ambow.pss.service.impl.BizCategoryServiceImpl;
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
 * @date 2020/9/5 21:23
 */
@WebServlet(name = "BizCategoryServlet", urlPatterns="/category.do")
public class BizCategoryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

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
        }else if("insert".equals(action)){
            insert(request, response);
        }else if("queryAll".equals(action)){
            queryAll(request, response);
        }
    }

    private void queryAll(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("categoryList",bizCategoryService.queryAll(""));
        request.getRequestDispatcher("view/category/category_list.jsp").forward(request,response);
    }

    private void insert(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        String categoryName = request.getParameter("category_name");
        Category category = new Category(0,categoryName, new Date(),new Date(),true);
        bizCategoryService.addCategory(category);
        session.setAttribute("categoryList",bizCategoryService.queryAll(""));
        request.getRequestDispatcher("view/category/category_list.jsp").forward(request,response);

    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        Date date = new Date();
        int id = Integer.parseInt(request.getParameter("id"));
        String categoryName = request.getParameter("category_name");
        Category category = new Category(id,categoryName, null, date,true);
        bizCategoryService.update(category);
        session.setAttribute("categoryList",bizCategoryService.queryAll(""));
        request.getRequestDispatcher("view/category/category_list.jsp").forward(request,response);

    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        int  id = Integer.parseInt(request.getParameter("id"));
        bizCategoryService.delete(id);
        List<Category> categoryList = bizCategoryService.queryAll("");
        session.setAttribute("categoryList",categoryList);
        request.getRequestDispatcher("view/category/category_list.jsp").forward(request,response);

    }

    private void queryById(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        int  id = Integer.parseInt(request.getParameter("id"));
        Category bizCategory = bizCategoryService.queryById(id);
        session.setAttribute("category",bizCategory);
        request.getRequestDispatcher("view/category/category_modify.jsp").forward(request,response);

    }

    private void queryBlurry(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        String keyword = request.getParameter("keyword");
        session.setAttribute("categoryList",bizCategoryService.queryAll(keyword));
        request.getRequestDispatcher("view/category/category_list.jsp").forward(request,response);
    }

    private BizCategoryService bizCategoryService = new BizCategoryServiceImpl();
}

