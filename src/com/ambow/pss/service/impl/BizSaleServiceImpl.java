package com.ambow.pss.service.impl;

import com.ambow.pss.dao.BizSaleDao;
import com.ambow.pss.init.InitServlet;
import com.ambow.pss.model.Sale;
import com.ambow.pss.model.Stock;
import com.ambow.pss.model.StockModifyRecord;
import com.ambow.pss.service.BizSaleService;
import com.ambow.pss.service.BizStockModifyRecordService;
import com.ambow.pss.service.BizStockService;
import com.ambow.pss.util.JDBCUtil;

import java.io.InputStream;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;

/**
 * @author JYH
 * @version 1.0
 * @date 2020/9/7 0:02
 */
public class BizSaleServiceImpl implements BizSaleService {

    @Override
    public List<Sale> queryAll(String keyword) {
        List<Sale> saleList = null;
        String sql = "SELECT\n" +
                "biz_sale.product_bar_code,\n" +
                "biz_product.product_name,\n" +
                "biz_sale.sale_price,\n" +
                "biz_sale.sale_count,\n" +
                "biz_sale.sale_amount,\n" +
                "biz_sale.create_time\n" +
                "FROM\n" +
                "biz_sale ,\n" +
                "biz_product\n" +
                "where biz_sale.product_bar_code = biz_product.bar_code and\n" +
                "biz_product.deleted = 1 and biz_sale.deleted = 1 and biz_product.product_name like ?";
        Object[] params = {("%"+keyword+"%")};
        try {
            saleList = bizSaleDao.listQuery(sql,params,Sale.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saleList;
    }

    @Override
    public int insertSale(Sale sale, String barcode, Stock sto, StockModifyRecord smr) {
        int pk = 0;
        String sql = "INSERT INTO biz_sale (product_bar_code,sale_price,sale_date,sale_count,sale_amount,create_time,update_time,deleted) values (?,?,?,?,?,?,?,1)";
        Object[] params = {sale.getProduct_bar_code(),sale.getSale_price(),sale.getSale_date(),sale.getSale_count(),sale.getSale_amount(),sale.getCreate_time(),sale.getUpdate_time()};
        try {
            int yunalai = bizStockService.stockCount(barcode);
            int bianhua = sto.getStock_count();
            sto.setStock_count(yunalai + bianhua);
            bizStockService.updateStock(sto);

            bizStockModifyRecordService.addStockModifyRecord(smr);

            pk = bizSaleDao.addWithBackPK(sql,params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pk;
    }

    public BizSaleServiceImpl(){
        conn = JDBCUtil.getInstance().getConnection();
        bizSaleDao = new BizSaleDao(conn);
    }

    private Connection conn;
    private BizSaleDao bizSaleDao;
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
        BizSaleService saleService = new BizSaleServiceImpl();
        saleService.insertSale(null,"1",null,null);
    }
}
