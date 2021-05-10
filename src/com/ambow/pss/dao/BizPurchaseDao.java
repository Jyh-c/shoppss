package com.ambow.pss.dao;

import com.ambow.pss.model.view.ViewPurchase;

import java.sql.Connection;

/**
 * @author JYH
 * @version 1.0
 * @date 2020/9/6 12:47
 */
public class BizPurchaseDao extends BasicDao<ViewPurchase>{
    public BizPurchaseDao(Connection conn) {
        super(conn);
    }
}
