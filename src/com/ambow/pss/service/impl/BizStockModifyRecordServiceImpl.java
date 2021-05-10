package com.ambow.pss.service.impl;

import com.ambow.pss.dao.BizStockModifyRecordDao;
import com.ambow.pss.init.InitServlet;
import com.ambow.pss.model.StockModifyRecord;
import com.ambow.pss.service.BizStockModifyRecordService;
import com.ambow.pss.util.JDBCUtil;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author JYH
 * @version 1.0
 * @date 2020/9/6 22:06
 */
public class BizStockModifyRecordServiceImpl implements BizStockModifyRecordService {
    @Override
    public List<StockModifyRecord> queryAll(String keyword) {
        List<StockModifyRecord> stockModifyRecordList = null;
        String sql = "SELECT\n" +
                "biz_stock_modify_record.product_bar_code,\n" +
                "biz_category.category_name,\n" +
                "biz_product.product_name,\n" +
                "biz_stock_modify_record.modify_count,\n" +
                "biz_stock_modify_record.modify_type,\n" +
                "biz_stock_modify_record.create_time\n" +
                "FROM\n" +
                "(biz_stock_modify_record ,biz_category)\n" +
                "INNER JOIN biz_product ON biz_product.category_id = biz_category.id\n" +
                "where biz_stock_modify_record.product_bar_code = biz_product.bar_code and\n" +
                "product_name like ? and biz_category.deleted = 1 and biz_product.deleted = 1 and biz_stock_modify_record.deleted = 1";
        Object[] params = {("%"+keyword+"%")};
        try {
            stockModifyRecordList = bizStockModifyRecordDao.listQuery(sql,params,StockModifyRecord.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stockModifyRecordList;
    }

    @Override
    public int addStockModifyRecord(StockModifyRecord s) {
        int pk = 0;
        String sql = "INSERT INTO biz_stock_modify_record(product_bar_code,modify_count,modify_type,create_time,update_time,deleted) VALUES(?,?,?,?,?,1)";
        Object[] params = {s.getProduct_bar_code(),s.getModify_count(),s.getModify_type(),s.getCreate_time(),s.getUpdate_time()};
        try {
            pk = bizStockModifyRecordDao.addWithBackPK(sql,params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pk;
    }

    public BizStockModifyRecordServiceImpl() {
        conn = JDBCUtil.getInstance().getConnection();
        bizStockModifyRecordDao = new BizStockModifyRecordDao(conn);
    }

    private Connection conn;
    private BizStockModifyRecordDao bizStockModifyRecordDao;

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
        BizStockModifyRecordService bizStockModifyRecordService = new BizStockModifyRecordServiceImpl();
        StockModifyRecord smr = new StockModifyRecord();
        smr.setProduct_bar_code("1");
        smr.setModify_count(150);
        smr.setModify_type("进货");
        smr.setCreate_time(new Date());
//        bizStockModifyRecordService.addStockModifyRecord(smr);
    }
}
