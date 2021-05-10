package com.ambow.pss.controller;

import com.ambow.pss.service.BizStockService;
import com.ambow.pss.service.impl.BizStockServiceImpl;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author JYH
 * @version 1.0
 * @date 2020/9/6 22:34
 */
@WebServlet(name = "BizStockServlet",urlPatterns = "/stock.do")
public class BizStockServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if("queryBlurry".equals(action)){
            queryBlurry(request, response);
        }else if("queryAll".equals(action)){
            queryAll(request, response);
        }
    }
    private void queryAll(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("stockList",bizStockService.queryAll(""));
        request.getRequestDispatcher("view/stock/stock_list.jsp").forward(request,response);
    }

    private void queryBlurry(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        String keyword = request.getParameter("keyword");
        session.setAttribute("stockList",bizStockService.queryAll(keyword));
        request.getRequestDispatcher("view/stock/stock_list.jsp").forward(request,response);
    }

    private BizStockService bizStockService = new BizStockServiceImpl();
}
