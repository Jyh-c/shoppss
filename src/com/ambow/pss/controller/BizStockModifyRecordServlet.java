package com.ambow.pss.controller;

import com.ambow.pss.service.BizStockModifyRecordService;
import com.ambow.pss.service.impl.BizStockModifyRecordServiceImpl;
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
 * @date 2020/9/6 22:20
 */
@WebServlet(name = "BizStockModifyRecordServlet",urlPatterns = "/smr.do")
public class BizStockModifyRecordServlet extends HttpServlet {
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
        session.setAttribute("smrList",bizStockModifyRecordService.queryAll(""));
        request.getRequestDispatcher("view/stock/stock_recorder_list.jsp").forward(request,response);
    }

    private void queryBlurry(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        String keyword = request.getParameter("keyword");
        session.setAttribute("smrList",bizStockModifyRecordService.queryAll(keyword));
        request.getRequestDispatcher("view/stock/stock_recorder_list.jsp").forward(request,response);
    }

    private BizStockModifyRecordService bizStockModifyRecordService = new BizStockModifyRecordServiceImpl();
}
