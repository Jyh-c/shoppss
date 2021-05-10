package com.ambow.pss.controller;

import com.ambow.pss.model.Product;
import com.ambow.pss.model.Purchase;
import com.ambow.pss.model.Stock;
import com.ambow.pss.model.StockModifyRecord;
import com.ambow.pss.service.BizPurchaseService;
import com.ambow.pss.service.impl.BizCategoryServiceImpl;
import com.ambow.pss.service.impl.BizPurchaseServiceImpl;
import com.ambow.pss.service.impl.BizSupplierServiceImpl;
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
 * @date 2020/9/6 13:47
 */
@WebServlet(name = "BizPurchaseServlet", urlPatterns = "/purchase.do")
public class BizPurchaseServlet extends HttpServlet {
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
        }else if("insert".equals(action)){
            insert(request, response);
        }else if("queryAll".equals(action)){
            queryAll(request, response);
        }else if("querySupplierAndCategory".equals(action)){
            querySupplierAndCategory(request, response);
        }
    }

    private void queryAll(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("purchaseList",bizPurchaseService.queryAll(""));
        request.getRequestDispatcher("view/purchase/purchase_list.jsp").forward(request,response);
    }

    private void queryBlurry(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        String keyword = request.getParameter("keyword");
        session.setAttribute("purchaseList",bizPurchaseService.queryAll(keyword));
        request.getRequestDispatcher("view/purchase/purchase_list.jsp").forward(request,response);
    }

    private void querySupplierAndCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("supplierList",new BizSupplierServiceImpl().queryAll(""));
        session.setAttribute("categoryList",new BizCategoryServiceImpl().queryAll(""));
        request.getRequestDispatcher("view/purchase/purchase_input.jsp").forward(request,response);
    }

    private void insert(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        Date date = new Date();
        String bar_code = request.getParameter("bar_code");
        String product_name = request.getParameter("product_name");
        BigDecimal purchase_price = new BigDecimal(request.getParameter("purchase_price"));
        String purchase_date = request.getParameter("purchase_date");
        String pro_date = request.getParameter("pro_date");
        String exp_date = request.getParameter("exp_date");
        Integer count = Integer.parseInt(request.getParameter("count"));
        BigDecimal sale_price = new BigDecimal(request.getParameter("sale_price"));
        //计算进货总价 ↓
        BigDecimal amount = new BigDecimal(purchase_price.doubleValue() * count);
        Integer supplier_id = Integer.valueOf(request.getParameter("supplier_id"));
        Integer category_id = Integer.valueOf(request.getParameter("category_id"));

        Product pro = new Product();
        pro.setBar_code(bar_code); pro.setSupplier_id(supplier_id); pro.setCategory_id(category_id);
        pro.setProduct_name(product_name);pro.setSale_price(sale_price); pro.setCreate_time(date);
        pro.setUpdate_time(date); pro.setDeleted(true); pro.setStock_count(count);

        Stock s = new Stock();
        s.setProduct_bar_code(bar_code);s.setCreate_time(date); s.setUpdate_time(date);
        s.setStock_count(count);

        StockModifyRecord smr = new StockModifyRecord();
        smr.setProduct_bar_code(bar_code); smr.setModify_count(count); smr.setModify_type("进货"); smr.setCreate_time(date);
        smr.setUpdate_time(date);

        Purchase pur = new Purchase(0,bar_code,purchase_date,pro_date,exp_date,purchase_price,count,amount,"null",date,date,true);

        bizPurchaseService.addPurchase(pur,bar_code,pro,s,smr);

        session.setAttribute("purchaseList",bizPurchaseService.queryAll(""));

        request.getRequestDispatcher("view/purchase/purchase_list.jsp").forward(request,response);

    }

    private BizPurchaseService bizPurchaseService = new BizPurchaseServiceImpl();

}
