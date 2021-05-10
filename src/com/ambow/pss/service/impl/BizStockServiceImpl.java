package com.ambow.pss.service.impl;

import com.ambow.pss.dao.BizStockDao;
import com.ambow.pss.init.InitServlet;
import com.ambow.pss.model.Stock;
import com.ambow.pss.service.BizStockService;
import com.ambow.pss.util.JDBCUtil;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author JYH
 * @version 1.0
 * @date 2020/9/6 19:34
 */
public class BizStockServiceImpl implements BizStockService {
    @Override
    public List<Stock> queryAll(String keyword) {
        List<Stock> stockList = new ArrayList<>();
        String sql ="SELECT\n" +
                "biz_stock.product_bar_code,\n" +
                "biz_category.category_name,\n" +
                "biz_product.product_name,\n" +
                "biz_stock.stock_count,\n" +
                "biz_supplier.supplier_name,\n" +
                "biz_supplier.contacts_name,\n" +
                "biz_supplier.tel\n" +
                "FROM\n" +
                "(biz_stock ,\n" +
                "biz_category)\n" +
                "INNER JOIN biz_product ON biz_product.category_id = biz_category.id\n" +
                "INNER JOIN biz_supplier ON biz_product.supplier_id = biz_supplier.id\n" +
                "where biz_stock.product_bar_code = biz_product.bar_code and\n" +
                "product_name like ? and biz_stock.deleted = 1 and biz_category.deleted = 1 and biz_product.deleted = 1 and biz_supplier.deleted = 1";
        Object[] params ={("%"+keyword+"%")};
        try {
            stockList = bizStockDao.listQuery(sql,params,Stock.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stockList;
    }

    @Override
    public int stockCount(String barcode) {
        Stock s = new Stock();
        String sql = "SELECT * FROM `biz_stock` where product_bar_code = ?";
        Object[] params = {barcode};
        try {
            s = bizStockDao.queryOne(sql,params,Stock.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s.getStock_count();
    }


    @Override
    public int addStock(Stock s) {
        int pk = 0;
        String sql = "INSERT INTO biz_stock(product_bar_code,stock_count,lock_key,create_time,update_time,deleted) VALUES(?,?,?,?,?,1)";
        Object[] params = {s.getProduct_bar_code(),s.getStock_count(),s.getLock_key(),s.getCreate_time(),s.getUpdate_time()};
        try {
            pk = bizStockDao.addWithBackPK(sql,params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pk;
    }

    @Override
    public int updateStock(Stock s) {
        int row = 0;
        String sql = "UPDATE biz_stock SET stock_count=? , update_time = ? WHERE product_bar_code = ?";
        Object[] params = {s.getStock_count(),s.getUpdate_time(),s.getProduct_bar_code()};
        try {
            row = bizStockDao.update(sql,params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public BizStockServiceImpl(){
        conn = JDBCUtil.getInstance().getConnection();
        bizStockDao = new BizStockDao(conn);
    }

    private Connection conn;
    private BizStockDao bizStockDao;

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

        BizStockService bizStockService = new BizStockServiceImpl();
        Stock s = new Stock();
        s.setProduct_bar_code("1");
        s.setStock_count(150);
        s.setLock_key(null);
        s.setCreate_time(new Date());
//        bizStockService.addStock(s);
        int i = bizStockService.stockCount("3");
        System.out.println(i);
    }
}
