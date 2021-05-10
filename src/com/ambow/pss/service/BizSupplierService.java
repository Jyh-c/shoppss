package com.ambow.pss.service;

import com.ambow.pss.model.Supplier;

import java.util.List;

/**
 * @author JYH
 * @version 1.0
 * @date 2020/9/5 18:06
 */
public interface BizSupplierService {
    /**
     * 查询所有供应商
     * @param keyword 供应商名称（模糊查询）
     * @return
     */
    List<Supplier> queryAll(String keyword);

    /**
     * 查询供应商
     * @param id 供应商条码
     * @return 某个供应商
     */
    Supplier queryById(int id);

    /**
     * 插入供应商
     * @param s 供应商
     * @return 
     */
    int addSupplier(Supplier s);

    /**
     * 更新供应商
     * @param s
     * @return
     */
    int update(Supplier s);

    /**
     * （逻辑）删除供应商
     * @param id
     * @return
     */
    int delete(int id);
}
