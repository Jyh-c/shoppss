package com.ambow.pss.dao;

import com.ambow.pss.model.Product;

import java.sql.Connection;

/**
 * @author JYH
 * @version 1.0
 * @date 2020/9/5 18:31
 */
public class BizProductDao extends BasicDao<Product>{
    public BizProductDao(Connection conn) {
        super(conn);
    }
}
