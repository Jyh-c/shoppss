package com.ambow.pss.controller;

import com.ambow.pss.model.Product;
import com.ambow.pss.service.*;
import com.ambow.pss.service.impl.*;
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
import java.util.List;

/**
 * @author JYH
 * @version 1.0
 * @date 2020/9/5 21:48
 */
@WebServlet(name = "BizProductServlet", urlPatterns = "/product.do")
public class BizProductServlet extends HttpServlet {

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
        session.setAttribute("productList",bizProductService.queryAll(""));
        request.getRequestDispatcher("view/product/product_list.jsp").forward(request,response);
    }

    private void insert(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        String productName = request.getParameter("product_name");
        Product product = new Product(/*0,productName, date, date,(byte)1*/);
        bizProductService.insertProduct(product);
        session.setAttribute("productList",bizProductService.queryAll(""));
        request.getRequestDispatcher("view/product/product_list.jsp").forward(request,response);

    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        Date date = new Date();
        int id = Integer.parseInt(request.getParameter("id"));
        String barCode = request.getParameter("bar_code");
        String productName = request.getParameter("product_name");
        BigDecimal salePrice  = new BigDecimal(request.getParameter("sale_price"));
        int supplierId  = Integer.valueOf(request.getParameter("supplier_id"));
        int categoryId  = Integer.valueOf(request.getParameter("category_id"));

        Product product = new Product(id, barCode, supplierId,null, categoryId,null,null,productName,salePrice, null, date, true, null, 0, null, null, null, null, null);
        bizProductService.update(product);
        session.setAttribute("productList", bizProductService.queryAll(""));
        request.getRequestDispatcher("view/product/product_list.jsp").forward(request,response);
        /*
        1.查询id是否存在
        2.如果已经存在，则提示
        3.不存在或者未修改则进行修改
         */
        /*        BizProduct b = bizProductService.queryByIdORBarCode(barCode);
        if(b == null){
            b = new BizProduct(0,"哈哈",0,0,"",0.0,null,null,(byte)-1);
        }
        System.out.println(b);
        List<Product> all = bizProductService.queryAll("");
        for(Product p : all){
            if("哈哈".equals(b.getBarCode())) {
                BizProduct bizProduct = new BizProduct(0, barCode, supplierId, categoryId, productName, salePrice, null, date, (byte) 1);
                bizProductService.update(bizProduct);
                session.setAttribute("productList", bizProductService.queryAll(""));
                break;
            }else if(b.getBarCode().equals(barCode)){
                BizProduct bizProduct = new BizProduct(0,barCode,supplierId,categoryId,productName,salePrice,null,date,(byte)1);
                bizProductService.update(bizProduct);
                session.setAttribute("productList",bizProductService.queryAll(""));
                break;
            }else if(p.getBarCode().equals(barCode)){
                session.setAttribute("barCodeTips","<script>alert('条码已存在，请修改！')</script>");
            }
        }*/
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        int  id = Integer.parseInt(request.getParameter("id"));
        bizProductService.delete(id);
        List<Product> productList = bizProductService.queryAll("");
        session.setAttribute("productList",productList);
        request.getRequestDispatcher("view/product/product_list.jsp").forward(request,response);

    }

    private void queryById(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        int  id = Integer.parseInt(request.getParameter("id"));

        Product product = bizProductService.queryByIdORBarCode(id);
        session.setAttribute("product",product);

        BizCategoryService bizCategoryService = new BizCategoryServiceImpl();
        session.setAttribute("categoryList",bizCategoryService.queryAll(""));
        BizSupplierService bizSupplierService = new BizSupplierServiceImpl();
        session.setAttribute("supplierList",bizSupplierService.queryAll(""));

        request.getRequestDispatcher("view/product/product_modify.jsp").forward(request,response);

    }

    private void queryBlurry(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        String keyword = request.getParameter("keyword");
        System.out.println(keyword);
        session.setAttribute("productList",bizProductService.queryAll(keyword));
        request.getRequestDispatcher("view/product/product_list.jsp").forward(request,response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private BizProductService bizProductService = new BizProductServiceImpl();
    private BizStockService bizStockService = new BizStockServiceImpl();
    private BizStockModifyRecordService bizStockModifyRecordService = new BizStockModifyRecordServiceImpl();
}

