package com.ambow.pss.service.impl;

import com.ambow.pss.dao.BizSupplierDao;
import com.ambow.pss.model.Supplier;
import com.ambow.pss.service.BizSupplierService;
import com.ambow.pss.util.JDBCUtil;

import java.sql.Connection;
import java.util.List;

/**
 * @author JYH
 * @version 1.0
 * @date 2020/9/5 18:09
 */
public class BizSupplierServiceImpl implements BizSupplierService {

    @Override
    public List<Supplier> queryAll(String keyword) {
        List<Supplier> supplierList = null;
        String sql = "SELECT s.* FROM biz_supplier s WHERE s.supplier_name LIKE ? AND s.deleted = 1";
        Object[] params = {("%"+keyword+"%")};
        try {
            supplierList = bizSupplierDao.listQuery(sql,params,Supplier.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return supplierList;
    }

    @Override
    public Supplier queryById(int id) {
        Supplier supplier = null;
        String sql = "SELECT s.* FROM biz_supplier s where deleted = 1 AND id =?";
        Object[] params = {id};
        try {
            supplier = bizSupplierDao.queryOne(sql,params,Supplier.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return supplier;
    }

    @Override
    public int addSupplier(Supplier s) {
        int pk = 0;
        String sql = "INSERT INTO biz_supplier(supplier_name,contacts_name,address,tel,create_time,update_time,deleted) VALUES(?,?,?,?,?,?,1)";
        Object[] params = {s.getSupplier_name(),s.getContacts_name(),s.getAddress(),s.getTel(),s.getCreate_time(),s.getUpdate_time()};
        try {
            pk = bizSupplierDao.addWithBackPK(sql,params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pk;
    }

    @Override
    public int update(Supplier s) {
        int row = 0;
        String sql = "UPDATE biz_supplier SET supplier_name=?,contacts_name=?,address=?,tel=?,update_time=? WHERE id=?";
        Object[] params = {s.getSupplier_name(),s.getContacts_name(),s.getAddress(),s.getTel(),s.getUpdate_time(),s.getId()};
        try {
            row = bizSupplierDao.update(sql,params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int delete(int id) {
        int row = 0;
        String sql = "UPDATE biz_supplier SET deleted = 0 WHERE id = ?";
        Object[] params = {id};
        try {
            row = bizSupplierDao.update(sql,params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public BizSupplierServiceImpl() {
        conn = JDBCUtil.getInstance().getConnection();
        bizSupplierDao = new BizSupplierDao(conn);
    }

    private Connection conn;
    private BizSupplierDao bizSupplierDao;


}
