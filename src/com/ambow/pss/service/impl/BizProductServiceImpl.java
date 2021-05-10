package com.ambow.pss.service.impl;

import com.ambow.pss.dao.BizProductDao;
import com.ambow.pss.init.InitServlet;
import com.ambow.pss.model.Product;
import com.ambow.pss.service.BizProductService;
import com.ambow.pss.util.JDBCUtil;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * @author JYH
 * @version 1.0
 * @date 2020/9/5 18:35
 */
public class BizProductServiceImpl implements BizProductService {
    @Override
    public List<Product> queryAll(String keyword) {
        List<Product> productList = null;
        String sql = "select p.id,p.bar_code,p.supplier_id,s.SUPPLIER_NAME,p.category_id,c.category_name,p.product_name,p.sale_price from biz_product p,biz_category c,biz_supplier s where p.category_id = c.id and p.supplier_id = s.id and p.product_name like ? and p.deleted = 1 and c.deleted = 1 and s.deleted = 1";
        Object[] params = {("%"+keyword+"%")};
        try {
            conn.setAutoCommit(false);
            productList = bizProductDao.listQuery(sql,params,Product.class);
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public <T> Product queryByIdORBarCode(T t) {
        Product product = null;
        if(t instanceof Integer){
            String sql = "select p.id,p.bar_code,p.supplier_id,s.SUPPLIER_NAME,p.category_id,c.category_name,p.product_name,p.sale_price from biz_product p,biz_category c,biz_supplier s where p.category_id = c.id and p.supplier_id = s.id and p.id = ?  and p.deleted = 1 and c.deleted = 1 and s.deleted = 1";
            Object[] params = {t};
            try {
                product = bizProductDao.queryOne(sql,params,Product.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return product;
        }else if(t instanceof String){
            String sql = "select p.id,p.bar_code,p.supplier_id,s.SUPPLIER_NAME,p.category_id,c.category_name,p.product_name,p.sale_price from biz_product p,biz_category c,biz_supplier s where p.category_id = c.id and p.supplier_id = s.id and p.bar_code = ?  and p.deleted = 1 and c.deleted = 1 and s.deleted = 1";
            Object[] params = {t};
            try {
                product = bizProductDao.queryOne(sql,params,Product.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return product;
        }
        return new Product();

    }

    @Override
    public int insertProduct(Product p) {
        int pk = 0;
        String sql = "INSERT INTO biz_product (bar_code,supplier_id,category_id,product_name,sale_price,create_time,update_time,deleted) values (?,?,?,?,?,?,?,1)";
        Object[] params = {p.getBar_code(),p.getSupplier_id(),p.getCategory_id(),p.getProduct_name(),p.getSale_price(),p.getCreate_time(),p.getUpdate_time()};
        try {
            pk = bizProductDao.addWithBackPK(sql,params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pk;
    }

    @Override
    public int update(Product p) {
        int row = 0;
        String sql = "UPDATE biz_product SET bar_code=?,supplier_id=?,category_id=?,product_name=?,sale_price=?,update_time=? WHERE id=?";
        Object[] params = {p.getBar_code(),p.getSupplier_id(),p.getCategory_id(),p.getProduct_name(),p.getSale_price(),p.getUpdate_time(),p.getId()};
        try {
            conn.setAutoCommit(false);
            row = bizProductDao.update(sql,params);
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int delete(int id) {
        int row = 0;
        String sql = "UPDATE biz_product SET deleted=0 WHERE id=?";
        Object[] params = {id};
        try {
            row = bizProductDao.update(sql,params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public BizProductServiceImpl() {
        conn = JDBCUtil.getInstance().getConnection();
        bizProductDao = new BizProductDao(conn);
    }

    private Connection conn;
    private BizProductDao bizProductDao;

    public static void main(String[] args) {
        try {
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
        BizProductService bizProductService = new BizProductServiceImpl();
      /*  List<Product> productList = bizProductService.queryAll("1");
        for (Product p : productList){
            System.out.println(p);
        }*/
        Product product = bizProductService.queryByIdORBarCode("2");
        System.out.println(product);
    }
}
