package com.ambow.pss.service.impl;

import com.ambow.pss.dao.BizCategoryDao;
import com.ambow.pss.model.Category;
import com.ambow.pss.service.BizCategoryService;
import com.ambow.pss.util.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author JYH
 * @version 1.0
 * @date 2020/9/5 16:42
 */
public class BizCategoryServiceImpl implements BizCategoryService {

    @Override
    public List<Category> queryAll(String keyword) {
        List<Category> categoryList = null;
        String sql ="select c.* from biz_category c where c.category_name like ? and c.deleted=1";
        Object[] params ={("%"+keyword+"%")};
        try {
            categoryList = bizCategoryDao.listQuery(sql,params,Category.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    @Override
    public Category queryById(int id) {
        Category category = null;
        String sql = "select c.* from biz_category c where c.id = ? and c.deleted=1";
        Object[] params ={id};
        try {
            category = bizCategoryDao.queryOne(sql,params,Category.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public int addCategory(Category c) {
        int pk = 0;
        String sql = "INSERT INTO biz_category(category_name,create_time,update_time,deleted) VALUES(?,?,?,1)";
        Object[] params = {c.getCategory_name(),c.getCreate_time(),c.getUpdate_time()};
        try {
            pk = bizCategoryDao.addWithBackPK(sql,params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pk;
    }

    @Override
    public int update(Category c) {
        int row = 0;
        String sql = "UPDATE biz_category SET category_name=? , update_time = ? WHERE id = ?";
        Object[] params = {c.getCategory_name(),c.getUpdate_time(),c.getId()};
        try {
            conn.setAutoCommit(false);
            row = bizCategoryDao.update(sql,params);
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int delete(int id) {
        int row = 0;
        String sql = "UPDATE biz_category SET deleted = 0 WHERE id=?";
        Object[] params = {id};
        try {
            row = bizCategoryDao.update(sql,params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public BizCategoryServiceImpl() {
        conn = JDBCUtil.getInstance().getConnection();
        bizCategoryDao = new BizCategoryDao(conn);
    }

    private Connection conn;
    private BizCategoryDao bizCategoryDao;
}
