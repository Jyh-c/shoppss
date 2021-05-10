package com.ambow.pss.dao;

import com.ambow.pss.model.Supplier;

import java.sql.Connection;

/**
 * @author JYH
 * @version 1.0
 * @date 2020/9/5 18:02
 */
public class BizSupplierDao extends BasicDao<Supplier>{

    public BizSupplierDao(Connection conn) {
        super(conn);
    }

}
