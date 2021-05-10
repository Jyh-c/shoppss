package com.ambow.pss.service;

import com.ambow.pss.model.Product;

import java.util.List;

/**
 * @author JYH
 * @version 1.0
 * @date 2020/9/5 18:31
 */
public interface BizProductService {

    /**
     * 查询所有商品
     * @param keyword 商品名称（模糊查询）
     * @return
     */
    List<Product> queryAll(String keyword);

    /**
     * 查询商品(id或者商品条码)
     * @param t 商品条码或者id
     * @return 某个商品
     */
    <T> Product queryByIdORBarCode(T t);

    /**
     * 插入商品
     * @param p 商品
     * @return 
     *
     */
    int insertProduct(Product p);

    /**
     * 更新商品
     * @param p
     * @return
     */
    int update(Product p);

    /**
     * （逻辑）删除商品
     * @param id
     * @return
     */
    int delete(int id);
    
}
