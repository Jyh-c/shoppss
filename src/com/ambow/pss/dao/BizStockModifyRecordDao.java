package com.ambow.pss.dao;

import com.ambow.pss.model.StockModifyRecord;

import java.sql.Connection;

/**
 * @author JYH
 * @version 1.0
 * @date 2020/9/6 19:21
 */
public class BizStockModifyRecordDao extends BasicDao<StockModifyRecord> {
    public BizStockModifyRecordDao(Connection conn) {
        super(conn);
    }
}
