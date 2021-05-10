package com.ambow.pss.controller;

import com.ambow.pss.model.Supplier;
import com.ambow.pss.service.BizSupplierService;
import com.ambow.pss.service.impl.BizSupplierServiceImpl;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author JYH
 * @version 1.0
 * @date 2020/9/5 21:13
 */
@WebServlet(name = "BizSupplierServlet", urlPatterns = "/supplier.do")
public class BizSupplierServlet extends HttpServlet {

    BizSupplierService bizSupplierService = new BizSupplierServiceImpl();
    
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

    private void queryAll(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("supplierList",bizSupplierService.queryAll(""));
        request.getRequestDispatcher("view/supplier/supplier_list.jsp").forward(request,response);
    }

    private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String supplierName = request.getParameter("supplier_name");
        String address = request.getParameter("address");
        String contactsName = request.getParameter("contacts_name");
        String tel = request.getParameter("tel");
        Supplier supplier = new Supplier(0,supplierName,contactsName,tel,address, new Date(), new Date(),true);
        bizSupplierService.addSupplier(supplier);
        System.out.println(supplier);
        session.setAttribute("supplierList",bizSupplierService.queryAll(""));
        request.getRequestDispatcher("view/supplier/supplier_list.jsp").forward(request,response);

    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        String supplierName = request.getParameter("supplier_name");
        String address = request.getParameter("address");
        String contactsName = request.getParameter("contacts_name");
        String tel = request.getParameter("tel");
        Supplier supplier = new Supplier(id,supplierName,contactsName,tel,address,null, new Date(),true);
        int updateRow = bizSupplierService.update(supplier);
        System.out.println(supplier);
        session.setAttribute("supplierList",bizSupplierService.queryAll(""));
        request.getRequestDispatcher("view/supplier/supplier_list.jsp").forward(request,response);

    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        int  id = Integer.parseInt(request.getParameter("id"));
        int deleteRow = bizSupplierService.delete(id);
        List<Supplier> supplierList = bizSupplierService.queryAll("");
        session.setAttribute("supplierList",supplierList);
        request.getRequestDispatcher("view/supplier/supplier_list.jsp").forward(request,response);

    }

    private void queryById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int  id = Integer.parseInt(request.getParameter("id"));
        Supplier bizSupplier = bizSupplierService.queryById(id);
        session.setAttribute("supplier",bizSupplier);
        request.getRequestDispatcher("view/supplier/supplier_modify.jsp").forward(request,response);
    }

    private void queryBlurry(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String keyword = request.getParameter("keyword");
        session.setAttribute("supplierList",bizSupplierService.queryAll(keyword));
        request.getRequestDispatcher("view/supplier/supplier_list.jsp").forward(request,response);
    }
}
