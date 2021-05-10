package com.ambow.pss.dao;

import com.ambow.pss.model.Stock;

import java.sql.Connection;

/**
 * @author JYH
 * @version 1.0
 * @date 2020/9/6 19:16
 */

public class BizStockDao extends BasicDao<Stock>{
    public BizStockDao(Connection conn) {
        super(conn);
    }
}
