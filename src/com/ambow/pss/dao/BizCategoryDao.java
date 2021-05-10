package com.ambow.pss.dao;

import com.ambow.pss.model.Category;

import java.sql.Connection;

/**
 * @author JYH
 * @version 1.0
 * @date 2020/9/5 16:35
 */
public class BizCategoryDao extends BasicDao<Category>{

    public BizCategoryDao(Connection conn) {
        super(conn);
    }

}
