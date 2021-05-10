package com.ambow.pss.service;

import com.ambow.pss.model.Product;
import com.ambow.pss.model.Purchase;
import com.ambow.pss.model.Stock;
import com.ambow.pss.model.StockModifyRecord;
import com.ambow.pss.model.view.ViewPurchase;

import java.util.List;

/**
 * @author JYH
 * @version 1.0
 * @date 2020/9/6 12:49
 */
public interface BizPurchaseService {

    /**
     * 查询所有进货记录
     * @param keyword 名称模糊查询
     * @return
     */
    List<ViewPurchase> queryAll(String keyword);

    /**
     * 插入进货记录
     * @param pro 进货记录
     * @param barcode 商品条码
     * @param s
     * @param smr
     * @return
     */
    int addPurchase(Purchase pur, String barcode, Product pro, Stock s, StockModifyRecord smr);

}
