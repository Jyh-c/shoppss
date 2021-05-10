package com.ambow.pss.service.impl;

import com.ambow.pss.dao.BizPurchaseDao;
import com.ambow.pss.init.InitServlet;
import com.ambow.pss.model.Product;
import com.ambow.pss.model.Purchase;
import com.ambow.pss.model.Stock;
import com.ambow.pss.model.StockModifyRecord;
import com.ambow.pss.model.view.ViewPurchase;
import com.ambow.pss.service.BizProductService;
import com.ambow.pss.service.BizPurchaseService;
import com.ambow.pss.service.BizStockModifyRecordService;
import com.ambow.pss.service.BizStockService;
import com.ambow.pss.util.JDBCUtil;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * @author JYH
 * @version 1.0
 * @date 2020/9/6 12:53
 */
public class BizPurchaseServiceImpl implements BizPurchaseService {
    @Override
    public List<ViewPurchase> queryAll(String keyword) {
        List<ViewPurchase> viewPurchaseList = null;
        String sql = "SELECT biz_purchase.id,biz_supplier.supplier_name,biz_product.bar_code,\n" +
                "biz_category.category_name,biz_product.product_name ,biz_purchase.purchase_price,\n" +
                "biz_purchase.count,biz_purchase.amount,biz_purchase.exp_date\n" +
                "FROM\n" +
                "(biz_purchase ,biz_supplier )\n" +
                "INNER JOIN biz_product ON biz_product.supplier_id = biz_supplier.id AND biz_purchase.product_barcode = biz_product.bar_code\n" +
                "INNER JOIN biz_category ON biz_product.category_id = biz_category.id\n" +
                "where biz_product.deleted = 1 and biz_supplier.deleted = 1 and biz_category.deleted = 1 and biz_purchase.deleted and biz_product.product_name like ?";
        Object[] params ={("%"+keyword+"%")};
        try {
            viewPurchaseList = bizPurchaseDao.listQuery(sql,params, ViewPurchase.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return viewPurchaseList;
    }

    @Override
    public int addPurchase(Purchase pur, String barcode,Product pro, Stock s, StockModifyRecord smr) {
        int pk = 0;
        String sql = "INSERT INTO biz_purchase(product_barcode,purchase_date,pro_date,exp_date,purchase_price,count,amount,create_time,update_time,deleted) VALUES(?,?,?,?,?,?,?,?,?,1)";
        Object[] params = {pur.getProduct_barcode(),pur.getPurchase_date(),pur.getPro_date(),pur.getExp_date(),pur.getPurchase_price(),pur.getCount(),pur.getAmount(),pur.getCreate_time(),pur.getUpdate_time()};
        try {
            conn.setAutoCommit(false);
            Product product = bizProductService.queryByIdORBarCode(barcode);
            if(product == null){
                bizProductService.insertProduct(pro);
                bizStockService.addStock(s);
            }else{
                int yunalai = bizStockService.stockCount(barcode);
                int bianhua = s.getStock_count();
                s.setStock_count(yunalai + bianhua);
                bizStockService.updateStock(s);
            }
            bizStockModifyRecordService.addStockModifyRecord(smr);
            pk = bizPurchaseDao.addWithBackPK(sql,params);
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }
        return pk;
    }

    public BizPurchaseServiceImpl(){
        conn = JDBCUtil.getInstance().getConnection();
        bizPurchaseDao = new BizPurchaseDao(conn);
    }

    private Connection conn;
    private BizPurchaseDao bizPurchaseDao;
    private BizProductService bizProductService = new BizProductServiceImpl();
    private BizStockService bizStockService = new BizStockServiceImpl();
    private BizStockModifyRecordService bizStockModifyRecordService = new BizStockModifyRecordServiceImpl();

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
        BizPurchaseService bizPurchaseService = new BizPurchaseServiceImpl();
        List<ViewPurchase> viewPurchaseList = bizPurchaseService.queryAll("");
        for(ViewPurchase viewPurchase : viewPurchaseList){
            System.out.println(viewPurchase);
        }
    }
}
