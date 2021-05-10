package com.ambow.pss.dao;

import com.ambow.pss.model.Sale;

import java.sql.Connection;

/**
 * @author JYH
 * @version 1.0
 * @date 2020/9/6 23:18
 */
public class BizSaleDao extends BasicDao<Sale>{
    public BizSaleDao(Connection conn) {
        super(conn);
    }
}
