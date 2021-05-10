package com.ambow.pss.service;

import com.ambow.pss.model.Sale;
import com.ambow.pss.model.Stock;
import com.ambow.pss.model.StockModifyRecord;

import java.util.List;

/**
 * @author JYH
 * @version 1.0
 * @date 2020/9/6 23:59
 */
public interface BizSaleService {
    /**
     * 查询所有类别
     * @param keyword 名称模糊查询
     * @return
     */
    List<Sale> queryAll(String keyword);

    /**
     * 添加销售单
     * @param s
     * @return
     */
    int insertSale(Sale sale, String barcode, Stock sto, StockModifyRecord smr);
}
