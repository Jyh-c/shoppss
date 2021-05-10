package com.ambow.pss.service;

import com.ambow.pss.model.Stock;

import java.util.List;

/**
 * @author JYH
 * @version 1.0
 * @date 2020/9/6 19:20
 */
public interface BizStockService {
    /**
     * 查询所有库存信息
     * @param keyword
     * @return
     */
    List<Stock> queryAll(String keyword);

    /**
     * 根据条码查询
     * @param barcode
     * @return
     */

    int stockCount(String barcode);

    /**
     * 添加库存信息
     * @param s
     * @return
     */
    int addStock(Stock s);

    /**
     * 更新库存信息
     * @param s
     * @return
     */
    int updateStock(Stock s);
}
