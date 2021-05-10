package com.ambow.pss.controller;

import com.ambow.pss.model.Sale;
import com.ambow.pss.model.Stock;
import com.ambow.pss.model.StockModifyRecord;
import com.ambow.pss.service.BizSaleService;
import com.ambow.pss.service.BizStockService;
import com.ambow.pss.service.impl.BizSaleServiceImpl;
import com.ambow.pss.service.impl.BizStockServiceImpl;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author JYH
 * @version 1.0
 * @date 2020/9/6 23:15
 */
@WebServlet(name = "BizSaleServlet",urlPatterns = "/sale.do")
public class BizSaleServlet extends HttpServlet {

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
        }else if("doSale".equals(action)){
            doSale(request, response);
        }
    }

    private void queryAll(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("saleList",bizSaleService.queryAll(""));
        request.getRequestDispatcher("view/sale/sale_list.jsp").forward(request,response);
    }

    private void queryBlurry(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        String keyword = request.getParameter("keyword");
        session.setAttribute("saleList",bizSaleService.queryAll(keyword));
        request.getRequestDispatcher("view/sale/sale_list.jsp").forward(request,response);
    }

    private void doSale(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        Date date = new Date();
        String product_bar_code = request.getParameter("product_bar_code");
        BigDecimal sale_price = new BigDecimal(request.getParameter("sale_price"));
        String sale_date = request.getParameter("sale_date");
        Integer sale_count = Integer.valueOf(request.getParameter("sale_count"));
        double amount = sale_price.doubleValue()*sale_count;

        int stockCount = bizStockService.stockCount(product_bar_code);

        if( stockCount <= 0 && (stockCount - sale_count) < 0 ){
            request.setAttribute("saleError","<script>alert('库存已不足！！')</script>");
            request.getRequestDispatcher("view/sale/do_sale.jsp").forward(request,response);
        }else{
            Stock sto = new Stock();
            sto.setProduct_bar_code(product_bar_code); sto.setUpdate_time(date); sto.setCreate_time(date);
            sto.setStock_count(-1 * sale_count);

            StockModifyRecord smr = new StockModifyRecord();
            smr.setProduct_bar_code(product_bar_code); smr.setModify_count(sale_count); smr.setModify_type("销售"); smr.setCreate_time(date);
            smr.setUpdate_time(date);

            Sale sale = new Sale(0,product_bar_code,sale_price,sale_date,sale_count,new BigDecimal(amount),null,date,date,true,"");
            bizSaleService.insertSale(sale,product_bar_code,sto,smr);

            session.setAttribute("saleList",bizSaleService.queryAll(""));
            request.getRequestDispatcher("view/sale/sale_list.jsp").forward(request,response);
        }

    }

    private BizSaleService bizSaleService = new BizSaleServiceImpl();
    private BizStockService bizStockService = new BizStockServiceImpl();
}
